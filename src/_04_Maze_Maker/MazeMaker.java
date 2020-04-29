package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		int x = randGen.nextInt(maze.cells.length);
		int y = randGen.nextInt(maze.cells[x].length);
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[x][y]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> un = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(un.size() > 0) {
			//C1. select one at random.
			int r = randGen.nextInt(un.size());
			//C2. push it to the stack
			uncheckedCells.push(un.get(r));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, un.get(r));
			//C4. make the new cell the current cell and mark it as visited
			currentCell = un.get(r);
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		//D. if all neighbors are visited
		if(un.size() == 0) {
			//D1. if the stack is not empty
			if(uncheckedCells.size() > 0) {
				// D1a. pop a cell from the stack
				Cell c = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = c;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() - c2.getX() == 0) {
			if(c1.getY() - c2.getY() == -1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			if(c1.getY() - c2.getY() == 1) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			}
		}
		if(c1.getY() - c2.getY() == 0) {
			if(c1.getX() - c2.getX() == -1) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
			if(c1.getX() - c2.getX() == 1) {
				c2.setEastWall(false);
				c1.setWestWall(false);
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> un = new ArrayList<Cell>();
		int x1 = -1, x2 = 1, y1 = -1, y2 = 1;
		if(c.getX() == 0) {
			x1 = 0;
		}
		else if(c.getX() == maze.cells.length - 1) {
			x2 = 0;
		}
		if(c.getY() == 0) {
			y1 = 0;
		}
		else if(c.getY() == maze.cells[c.getX()].length - 1) {
			y2 = 0;
		}
		for(int i = x1; i <= x2; i++) {
			for(int j = y1; j <= y2; j++) {
				if(!maze.getCell(c.getX() + i, c.getY() + j).hasBeenVisited()) {
					if(i == 0 || j == 0){
						un.add(maze.getCell(c.getX() + i, c.getY() + j));
					}
				}
			}
		}
		return un;
	}
}
