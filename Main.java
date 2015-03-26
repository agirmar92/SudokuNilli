package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		int[][] board = new int[9][9]; 
		// Today's Sudoku 26.mar.2015:
		// row 1:
		board[0][0] = 8;
		board[0][1] = 7;
		board[0][4] = 1;
		board[0][5] = 3;
		// row 2:
		board[1][3] = 9;
		board[1][6] = 6;
		// row 3:
		board[2][1] = 1;
		board[2][2] = 9;
		// row 4:
		board[3][3] = 5;
		board[3][8] = 4;
		// row 5:
		board[4][0] = 1;
		board[4][1] = 4;
		board[4][4] = 6;
		board[4][7] = 3;
		board[4][8] = 8;
		// row 6:
		board[5][0] = 5;
		board[5][5] = 2;
		// row 7:
		board[6][6] = 8;
		board[6][7] = 7;
		// row 8:
		board[7][2] = 3;
		board[7][5] = 1;
		// row 9:
		board[8][3] = 4;
		board[8][4] = 2;
		board[8][7] = 6;
		board[8][8] = 1;
		
		Board b = new Board(board);
		//b.print();
		b.fancyPrint();
		/*System.out.println(b.put(0, 0, 3));
		b.fancyPrint();
		System.out.println(b.put(1, 1, 5));
		b.fancyPrint();
		System.out.println(b.put(2, 0, 2));
		b.fancyPrint();
		System.out.println(b.put(0, 3, 9));
		b.fancyPrint();
		b.put(0, 0, 1);
		b.put(0, 1, 2);
		b.put(0, 2, 3);
		b.put(0, 3, 4);
		b.put(0, 4, 5);
		b.put(0, 5, 6);
		b.put(0, 6, 7);
		b.put(0, 7, 8);
		b.put(0, 8, 9);*/
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());
		
		b.fancyPrint();
		
	}
}