package Sudoku;

import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		for(int i = 0; i < 7; i++){
		
		System.out.println();
		System.out.println("BOARD NUMBER " + i);
		System.out.println();
		VarBoard b = new VarBoard(Generator.get(4, i), 4);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		//b.solve();
		//System.out.println("Solved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		
		b = new VarBoard(Generator.get(4, i), 4);
		b.counter = 0;
		timer = new Stopwatch();
		b.findSolution();
		System.out.println("newSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		
		b = new VarBoard(Generator.get(4, i), 4);
		b.counter = 0;
		timer = new Stopwatch();
		b.initialRestrict();
		b.newNewSolve();
		System.out.println("newNewSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		}
	}

}
