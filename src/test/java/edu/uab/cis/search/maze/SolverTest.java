package edu.uab.cis.search.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class SolverTest {

  @Test
  public void testNoObstacles() {
    // @formatter:off
    String mazeString = 
      "###############\n" +
      "#S           G#\n" +
      "#             #\n" +
      "###############\n";
    // @formatter:on
    Maze maze = new Maze(2, 13, new Square(0, 0), new Square(0, 12), Sets.<Square> newHashSet());
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should just go left to right
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath = new ArrayList<>();
    for (int column = 1; column < 12; ++column) {
      expectedPath.add(new Square(0, column));
    }
    Assert.assertEquals(expectedPath, path);

    // no additional squares should be explored
    Set<Square> explored = solver.getExploredSquares();
    Assert.assertEquals(Sets.newHashSet(expectedPath), explored);
  }

  @Test
  public void testObstacles() {
    // @formatter:off
    String mazeString = 
      "#######\n" +
      "#     #\n" +
      "#  # G#\n" +
      "# S # #\n" +
      "#######\n";
    // @formatter:on
    Set<Square> obstacles = Sets.newHashSet(new Square(1, 2), new Square(2, 3));
    Maze maze = new Maze(3, 5, new Square(2, 1), new Square(1, 4), obstacles);
    Assert.assertEquals(mazeString, maze.toString());

    // solve the maze
    Solver solver = new Solver(maze);

    // the solution should go up around the obstacles
    List<Square> path = solver.getPathFromStartToGoal();
    List<Square> expectedPath =
        Lists.newArrayList(
            new Square(1, 1),
            new Square(0, 1),
            new Square(0, 2),
            new Square(0, 3),
            new Square(0, 4));
    Assert.assertEquals(expectedPath, path);

    // the square to the right of the start should also be explored
    Set<Square> explored = solver.getExploredSquares();
    Set<Square> expectedExplored = Sets.newHashSet(new Square(2, 2));
    expectedExplored.addAll(path);
    Assert.assertEquals(expectedExplored, explored);
  }
}