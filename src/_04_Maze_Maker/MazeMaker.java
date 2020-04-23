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
			Cell c = un.get(randGen.nextInt(un.size()));
			//C2. push it to the stack
			uncheckedCells.push(c);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, c);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = c;
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
		int x1 = -1, x2 = maze.cells.length, y1 = , y2;
		if
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(!maze.getCell(c.getX() + i, c.getY() + j).hasBeenVisited()) {
					un.add(maze.getCell(c.getX() + i, c.getY() + j));
				}
			}
		}
		return un;
	}
}
