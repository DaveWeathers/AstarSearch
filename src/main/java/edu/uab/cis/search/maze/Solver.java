package edu.uab.cis.search.maze;

import java.util.List;
import java.util.Set;

/**
 * Solves a maze using A* search with an L1 heuristic.
 * 
 * Specifically, squares are explored with the following strategy:
 * <ul>
 * <li>Squares are ordered for exploration using the score f(x) = g(x) + h(x),
 * with the smallest f(x) squares being explored first</li>
 * <li>g(x) is the length of the path so far, from the start square to the
 * current square, including the steps necessary to avoid obstacles</li>
 * <li>h(x) is the L1 estimate of the path to the goal, that is, the Manhattan
 * distance to the goal, ignoring potential obstacles</li>
 * <li>Squares with the same f(x) score are ordered by the h(x) score, with
 * smaller h(x) scores first</li>
 * <li>Squares with the same f(x) and h(x) scores are ordered by row, with
 * smaller rows first</li>
 * <li>Squares with the same f(x), h(x) and row should be ordered by column,
 * with smaller columns first</li>
 * </ul>
 */
public class Solver {

  private Set<Square> explored;

  private List<Square> path;

  /**
   * Solves the given maze, determining the path to the goal.
   * 
   * @param maze
   *          The maze to be solved.
   */
  public Solver(Maze maze) {
    // TODO
  }

  /**
   * @return The squares along the path from the start to the goal,
   *         including both the start square and the goal square.
   */
  public List<Square> getPathFromStartToGoal() {
    return this.path;
  }

  /**
   * @return All squares that were explored during the search process. This is
   *         always a superset of the squares returned by
   *         {@link #getPathFromStartToGoal()}.
   */
  public Set<Square> getExploredSquares() {
    return this.explored;
  }

}
