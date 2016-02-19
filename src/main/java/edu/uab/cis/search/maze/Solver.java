package edu.uab.cis.search.maze;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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

    private Set<Square> explored = new HashSet<Square>();
	  private HashMap<Square, Integer> map = new HashMap<Square, Integer>();
	  private HashMap<Square, Square> pChild = new HashMap<Square, Square>();
	 
	  private List<Square> path = new ArrayList<Square>();
  Maze maze;
  /**
   * Solves the given maze, determining the path to the goal.
   * 
   * @param maze
   *          The maze to be solved.
   */
  public Solver(Maze maze) {
    this.maze = maze;
    map.put(maze.getStart(), 0);
    neighbors.add(maze.getStart());    
    Square current = maze.getStart();
    outer: while(!neighbors.isEmpty()){
    	current = neighbors.poll();
    	if(current.equals(maze.getGoal())){
    		buildPath(current);
    		explored.add(current);
    		break outer;
    	}
    	exploreNeighbors(current);
    }
    
  }
  
  /**
   * @return The squares along the path from the start to the goal,
   *         including both the start square and the goal square.
   */
  public List<Square> getPathFromStartToGoal() {
    return this.path;
  }
  private void exploreNeighbors(Square square){
	  explored.add(square);
	  Square[] nesw = {
			  new Square(square.getRow()-1, square.getColumn()),
			  new Square(square.getRow(), square.getColumn()+1),
			  new Square(square.getRow()+1, square.getColumn()),
			  new Square(square.getRow(), square.getColumn()-1)};
	  	for(int i = 0; i < 4; i++){
	  		if(!maze.isBlocked(nesw[i]) && !explored.contains(nesw[i]) && !neighbors.contains(nesw[i])){
	  			map.put(nesw[i], map.get(square)+1);
	  			pChild.put(nesw[i], square);
	  			neighbors.add(nesw[i]);
	  				}
	  			}
	  		}
	  		
  /**
   * @return All squares that were explored during the search process. This is
   *         always a superset of the squares returned by
   *         {@link #getPathFromStartToGoal()}.
   */
  public Set<Square> getExploredSquares() {
    return this.explored;
  }

  private int hX(Square square){
	   return (Math.abs(maze.getGoal().getRow()-square.getRow()) + Math.abs(maze.getGoal().getColumn()-square.getColumn()));
	   }
  
  
  private void buildPath(Square square){
	  Square current = square;
	  List<Square> temp = new ArrayList<Square>();
	  temp.add(current);
	  while(!current.equals(maze.getStart())){
		  temp.add(pChild.get(current));
		  current = pChild.get(current);
	  }
	  for(int i = temp.size()-1; i >= 0; i--){
		  path.add(temp.get(i));
	  }
	  
  }
  /**
   * 
   *  Creates PriorityQueue following given rules. 
  */
  private PriorityQueue<Square> neighbors = new PriorityQueue<Square>(1, new Comparator<Square>(){
	  public int compare(Square s1, Square s2){
		  if(map.get(s1) + hX(s1) > map.get(s2) + hX(s2)){
			  return 1;
		  }else if(map.get(s1) + hX(s1) < map.get(s2) + hX(s2)){
			  return -1;
		  }else{
			  if(hX(s1) > hX(s2)){
				  return 1;
			  }else if(hX(s1) < hX(s2)){
				  return -1;
			  }else {
				  if(s1.getRow() > s2.getRow()){
					  return 1;
				  }
				  if(s1.getRow()< s2.getRow()){
					  return -1;
				  }else{
					  if(s1.getColumn() > s2.getColumn()){
						  return 1;
					  }else if(s1.getColumn() < s2.getColumn()){
						  return -1;
					  }else { return 0;}
				  }
			  }
		  }
	  }
  });
}


