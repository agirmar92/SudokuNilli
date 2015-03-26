package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		int[][] board = Generator.getHardBoard(); 
		Board b = new Board(board);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());
		
		b.fancyPrint();
	}
}