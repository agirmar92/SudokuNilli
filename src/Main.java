package Sudoku;

import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		/*for (int i = 0; i < 4 ; i++)
		{
			if (i == 2) continue;
			VarBoard b = new VarBoard(Generator.get(4, i), 4);
			Stopwatch timer = new Stopwatch();
			b.solve();
			System.out.println("Brute force: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
			b = new VarBoard(Generator.get(4, i), 4);
			//b.fancyPrint();
			b.counter = 0;
			timer = new Stopwatch();
			b.initialRestrict();
			b.newSolve();
			System.out.println("Queue: \t time: " + timer.elapsedTime() + " \t states: " + b.counter);
		}*/

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
		v.solve();
		System.out.println(timer.elapsedTime());
*/		/*System.out.println(timer2.elapsedTime());*/
		

		/*Variable[][] board = Generator.getHardBoard(); 
		Board b = new Board(board, 3);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println(timer.elapsedTime());*/
		
		/*Generator gen = new Generator(4);
		VarBoard randomBoard = gen.getRandomBoard();
		randomBoard.fancyPrint();*/
		
		VarBoard b = new VarBoard(Generator.get(4, 3), 4);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.initialRestrict();
		System.out.println("newSolve");
		b.newSolve();
		System.out.println("time: \t" + timer.elapsedTime() + "\t states: \t" + b.counter);
		b = new VarBoard(Generator.get(4, 3), 4);
		//b.fancyPrint();
		b.counter = 0;
		timer = new Stopwatch();
		//b.initialRestrict();
		b.solve();
		System.out.println("solve");
		System.out.println("time: \t" + timer.elapsedTime() + "\t states: \t" + b.counter);
	}
}