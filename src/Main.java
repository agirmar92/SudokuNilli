package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		/*int[][] board4 = Generator.getBoard4(); 
		Board b4 = new Board(board4, 4);
		
		b4.fancyPrint();
		
		Stopwatch timer2 = new Stopwatch();
		b4.solve();
		System.out.println(timer2.elapsedTime());
		b4.fancyPrint();*/

		//int[][] board = Generator.getHardBoard(); 
		//Board b = new Board(board, 3);
		//b.fancyPrint();
		//Stopwatch timer = new Stopwatch();
		//b.solve();
		//System.out.println(timer.elapsedTime());
		//Board b = new Board(Generator.getBoard4(), 4);
		//b.fancyPrint();
		//b.solve();
		//b.fancyPrint();
		
/*		VarBoard v = new VarBoard(Generator.getBoard4(), 4);
		v.fancyPrint();
		Stopwatch timer = new Stopwatch();
		v.initialRestrict();
		v.newSolve();
		System.out.println(timer.elapsedTime());
*/		/*System.out.println(timer2.elapsedTime());*/
		

		/*Variable[][] board = Generator.getHardBoard(); 
		Board b = new Board(board, 3);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());*/
		
		Generator gen = new Generator(3);
		VarBoard randomBoard = gen.getRandomBoard();
		randomBoard.fancyPrint();
		
		Stopwatch timer = new Stopwatch();
		randomBoard.solve();
		System.out.println(timer.elapsedTime());
	}
}