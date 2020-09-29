package uk.ac.cam.pn330.prejava.ex3;

public class ArrayLife{
	
	public static boolean getCell(boolean[][] world, int col, int row){
		if (row < 0 || row > world.length - 1) return false;
		if (col < 0 || col > world[row].length - 1) return false;
		return world[row][col];
	}
	
	
	public static void setCell(boolean[][] world, int col, int row, boolean value){
		if (col < 0 || col > world.length - 1){
		}
		if (row < 0 || row > world[row].length - 1){
		}
		else{
		world[row][col] = value;
		}
	}
	
	
	public static int countNeighbours(boolean[][] world, int col, int row){
		int count = 0;
		int start_row = 0;
		int end_row = world.length;
		int start_col = 0;
		int end_col = world[row].length;
		
		if (row > 0){
			start_row = row - 1;
		}
		
		if (row < world.length - 1){
			end_row = row + 2;
		}
		
		if (col > 0){
			start_col = col - 1;
		}
		
		if (col < world[row].length - 1){
			end_col = col + 2;
		}
		
		
		for (int i = start_row; i < end_row; i++){
			for (int j = start_col; j < end_col; j++){
				if (i == row && j == col){
				}
				else if (getCell(world, j, i)){
					count++;
				}
			}
		}
		return count;
	}
	
	
	public static void print(boolean[][] world){
		System.out.println("-");
		for (int row = 0; row < world.length; row ++){
			for (int col = 0; col < world[row].length; col++){
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}
	
	
	public static boolean computeCell(boolean[][] world, int col, int row){
		boolean liveCell = getCell(world, col, row);
		int neighbours = countNeighbours(world, col, row);
		boolean nextCell = false;
		
		if (neighbours < 2){
		}
		else if (neighbours == 2){
			if (liveCell){
				nextCell = true;
			}
		}
		else if (neighbours == 3){
			nextCell = true;
		}
		return nextCell;
	}
	
	
	public static boolean[][] nextGeneration(boolean[][] world){
		boolean[][] new_world = new boolean[world.length][world[0].length];
		for(int i = 0; i < world[0].length; i++){
			for(int j = 0; j < world.length; j++){
				boolean change = computeCell(world, i, j);
				setCell(new_world, i, j, change);
			}
		}
		return new_world;
	}
	
	
	public static void play(boolean[][] world) throws java.io.IOException{
		int userResponse = 0;
		while (userResponse != 'q'){
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
	
	
	public static boolean getFromPackedLong(long packed, int position){
		return ((packed >>> position) & 1) == 1;
	}
	
	
	public static void main(String[] args) throws java.io.IOException{
		int size = Integer.parseInt(args[0]);
		long initial = Long.decode(args[1]);
		boolean[][] world = new boolean[size][size];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				world[i + size/2 -4][j + size/2 -4] = getFromPackedLong(initial, i*8 + j);
			}
		}
		play(world);
	}
	
	
}