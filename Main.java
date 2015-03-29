package Sudoku;

import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		int[][] smallBoard = {{0, 1, 3, 0}, {2, 0, 0, 0}, {0, 0, 0, 3}, {0, 2, 1, 0}};
		VarBoard b = new VarBoard(Generator.get(3, 0), 3);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println("Brute force: \t" + timer.elapsedTime() + " counter: \t" + b.counter);
		b.counter = 0;
		b = new VarBoard(Generator.get(3, 0), 3);
		timer = new Stopwatch();
		b.initialRestrict();
		
		b.newSolve();
		System.out.println("Queue solve: \t" + timer.elapsedTime() + " counter: \t" + b.counter);
		
	}
}