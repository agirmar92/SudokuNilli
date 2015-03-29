package Sudoku;

import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		for (int i = 0; i < 3 ; i++)
		{
			VarBoard b = new VarBoard(Generator.get(4, i), 4);
			Stopwatch timer = new Stopwatch();
			b.solve();
			System.out.println("Brute force: \t" + timer.elapsedTime() + " time: \t" + b.counter);
			b = new VarBoard(Generator.get(4, i), 4);
			b.fancyPrint();
			b.counter = 0;
			timer = new Stopwatch();
			b.initialRestrict();
			b.newSolve();
			System.out.println("Queue: \t" + timer.elapsedTime() + " time: \t" + b.counter);
		}
	}
}