import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		int i = 6;
		
		VarBoard b = new VarBoard(Generator.get(4, i), 4);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.solve();
		System.out.println("Solved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		
		b = new VarBoard(Generator.get(4, i), 4);
		b.counter = 0;
		timer = new Stopwatch();
		b.initialRestrict();
		b.newSolve();
		System.out.println("newSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		
	}

}
