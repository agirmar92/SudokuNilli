package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		int[][] board = Generator.getBoard(4); 
		Board b = new Board(board, 4);
		
		b.fancyPrint();
		
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());
	}
}