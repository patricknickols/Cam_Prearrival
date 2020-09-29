package uk.ac.cam.pn330.prejava.ex2;

public class TinyLife{
	public static boolean getCell(long world, int col, int row){
		int square = 8 * row + col;
		return PackedLong.get(world, square);
	}
	
	
	public static long setCell(long world, int col, int row, boolean newval){
		int square = 8 * row + col;
		return PackedLong.set(world, square, newval);
	}
	
	public static int countNeighbours(long world, int col, int row) {
		
		int count = 0;
		
		
		int start_row = 0;
		if (row > 0){
			start_row = row - 1;
		}
		
		
		int end_row = 7;
		if (row < 7){
			end_row = row + 1;
		}
		
		int start_col = 0;
		if (col > 0){
			start_col = col - 1;
		}
		
		int end_col = 7;
		if (col < 7){
			end_col = col + 1;
		}
		
		for (int i = start_row; i <= end_row; i++) {
			for (int j = start_col; j <= end_col; j++) {
				if (i == row && j == col){
				}
				else if (getCell(world, j, i)) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void print(long world) {
		System.out.println("-");
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}
	
	
	public static boolean computeCell(long world, int col, int row){
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
	
	public static long nextGeneration(long world) {
		long new_world = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j<8; j++){
				boolean change = computeCell(world, i, j);
				new_world = setCell(new_world, i, j, change);
			}
		}
		return new_world;
	}
	
	public static void play(long world) throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
	
	
	public static void main(String[] args) throws java.io.IOException {
		play(Long.decode(args[0]));
	}
	
	
}