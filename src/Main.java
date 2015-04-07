package Sudoku;
import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		for (int i = 0; i < Generator.board4.length; i++)
		{
			//VarBoardA s = new VarBoardA(Generator.get(4, i), 4);
			Sudoku s = new Sudoku(Generator.get(4, i), 4);
			Stopwatch timer = new Stopwatch();
			s.findSolution(true, false, true);
			System.out.println(s.assignments);
			System.out.println("Var, not val, simple, normal " + i + "\t" + timer.elapsedTime() + " " + s.counter);
			/*s = new Sudoku(Generator.get(4, i), 4);
			timer = new Stopwatch();
			s.findSolution(true, false, true);
			System.out.println("\t \t Var, not val, simple, normal " + i + "\t" + timer.elapsedTime() + " " + s.counter);*/
			
		}
		
	}

}

