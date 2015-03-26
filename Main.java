package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		int[][] board2 = Generator.getBoard(4); 
		Board b2 = new Board(board2, 4);
		
		b2.fancyPrint();
		
		Stopwatch timer2 = new Stopwatch();
		b2.solve();
		System.out.println(timer2.elapsedTime());

		int[][] board = Generator.getHardBoard(); 
		Board b = new Board(board, 3);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());
		
	}
}