import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class EightQueens {
	private char[][] board;
	
	private EightQueens(int boardSize) {
		board = new char[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[i][j] = '-';
			}
		}
	}
	
	private void place(int row, int col) {
		if(board[row - 1][col - 1] == '-') 
			board[row - 1][col - 1] = 'Q'; 
	}
	
	private void remove(int row, int col) {
		if(board[row - 1][col - 1] == 'Q')
			board[row - 1][col - 1] = '-';
	}
	
	private boolean isSafe(int row, int col) {
		if(row <= 0 || col <= 0)
			return false;
			
		if(row > board.length || col > board.length)
			return false;
		
		for(int i = 0; i < board.length; i++) 
			if(board[row - 1][i] == 'Q')
				return false;

		for(int i = 0; i < board.length; i++) 
			if(board[i][col - 1] == 'Q')
				return false;
		
		int min = Math.min(row, col);
		for(int i = row - min, j = col - min; i < board.length && j < board.length; i++, j++)
			if(board[i][j] == 'Q')
				return false;
		
		for(int i = row - 1, j = col - 1; i <= board.length - 1 && j >= 0 ; i++, j--) 
			if(board[i][j] == 'Q')
				return false;
		
		for(int i = row - 1, j = col - 1; i >= 0 && j <= board.length - 1 ; i--, j++) 
			if(board[i][j] == 'Q')
				return false;
		
		return true;
	}
	
	private void displayBoard() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void solveHelper(int col) {
		if(col > board.length)
			this.displayBoard();
		else {
			for(int i = 1; i <= board.length; i++) {
				if(this.isSafe(i, col)) {
					this.place(i, col);
				
					this.solveHelper(col + 1);
				
					this.remove(i, col);
				}
			}
		}
	}
	
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
