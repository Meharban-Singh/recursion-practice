// Prints all possible chess boards so that if 8 queens are placed on a board, then no 2 quees threat each other. 


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class EightQueens {
	private char[][] board;
	
	/**
	 * Constructs an EightQueen instance, creates a n*n array, representing 
	 * the n*n chess board and fills each spot with an '-' 
	 */
	private EightQueens(int boardSize) {
		board = new char[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[i][j] = '-'; // each cell is empty to begin with
			}
		}
	}
	
	/**
	 * Places a Queen on the cell represented by a row and col.
	 * Row and col numbers start with a 1.  
	 * 
	 * @param row int row number of the cell
	 * @param col int col number of the cell
	 */
	private void place(int row, int col) {
		if(board[row - 1][col - 1] == '-') 
			board[row - 1][col - 1] = 'Q'; 
	}
	
	/**
	 * Removes a Queen from a cell represented by a row and col
	 * Row and col numbers start with a 1.  
	 * 
	 * Used for backtracking
	 * 
	 * @param row int row number of the cell
	 * @param col int col number of the cell
	 */
	private void remove(int row, int col) {
		if(board[row - 1][col - 1] == 'Q')
			board[row - 1][col - 1] = '-';
	}
	
	/**
	 * Checks if a queen can be safely placed in a cell.
	 * A Queen can be safely placed if no other Queen can threaten it. 
	 * 
	 * @param row int row number of the cell
	 * @param col int col number of the cell
	 * 
	 * @return boolean 
	 */
	private boolean isSafe(int row, int col) {
		// Check for invalid row and col number
		if(row <= 0 || col <= 0)
			return false;
			
		if(row > board.length || col > board.length)
			return false;
		
		// Check if a queen is in the same row
		for(int i = 0; i < board.length; i++) 
			if(board[row - 1][i] == 'Q')
				return false;

		// Check if a queen is in the same col
		for(int i = 0; i < board.length; i++) 
			if(board[i][col - 1] == 'Q')
				return false;
		
		// Check if a queen is in the same diagonal
		int min = Math.min(row, col);
		// Opposite diagonal
		for(int i = row - min, j = col - min; i < board.length && j < board.length; i++, j++)
			if(board[i][j] == 'Q')
				return false;

		// lower left diagonal 
		for(int i = row - 1, j = col - 1; i <= board.length - 1 && j >= 0 ; i++, j--) 
			if(board[i][j] == 'Q')
				return false;
		
		// Upper right diagonal
		for(int i = row - 1, j = col - 1; i >= 0 && j <= board.length - 1 ; i--, j++) 
			if(board[i][j] == 'Q')
				return false;
		
		return true;
	}
	
	/**
	 * Prints out a board
	 */
	private void displayBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Recursive solveHelper - tries to place a Queen in each row of the col from param
	 * 
	 * @param col int col number
	 */
	private void solveHelper(int col) {
		// If the entire column is tried, print the board
		if(col > board.length)
			this.displayBoard();
		else {
			// Goes through each row of the column 
			for(int i = 1; i <= board.length; i++) {
				/* 
				 * If its safe to put a Queen in the current cell, 
				 * place it and recursively solve the next column.
				 * Once the next col is solved, remove the Queen from the current cell, 
				 * so that we can place it in the next safe cell in the column 
				 */ 
				if(this.isSafe(i, col)) {
					this.place(i, col);
				
					this.solveHelper(col + 1); // Recursive call
				
					this.remove(i, col); // Backtracking
				}
			}
		}
	}
	
	/**
	 * Solves the puzzle. Runs the solveHelper method with first col. 
	 */
	private void solve() {
		solveHelper(1);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//PrintStream fps = new PrintStream(new FileOutputStream(new File("8Queens.txt")));
		EightQueens e = new EightQueens(8);
		//PrintStream ps = System.out;
		//System.setOut(fps);
		e.solve();
	}
}
