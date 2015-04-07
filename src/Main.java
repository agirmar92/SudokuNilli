package Sudoku;
import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		for (int i = 0; i < Generator.board4.length; i++)
		{
			if (i == 8 || i == 7) continue;
			VarBoardA board = new VarBoardA(Generator.get(4, i), 4);
			//board.fancyPrint();
			Stopwatch timer = new Stopwatch();
			board.findSolution(1);
			System.out.println("Method 1 \t time: " + timer.elapsedTime() + " states: " + board.counter);
			board = new VarBoardA(Generator.get(4, i), 4);
			//board.fancyPrint();
			timer = new Stopwatch();
			board.findSolution(2);
			System.out.println("\t \t \t Method 2 \t time: " + timer.elapsedTime() + " states: " + board.counter);
			//for (int j = 0; j < 16; j++) System.out.print(board.numbers[j] + " ");
			//board.fancyPrint();
		}
		
	}
}
