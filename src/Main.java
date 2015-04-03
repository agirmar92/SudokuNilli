import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
/*		for(int i = 0; i < 7; i++){
			System.out.println();
			System.out.println("BOARD NUMBER " + i);
			VarBoard b = new VarBoard(Generator.get(4, i), 4);
			Stopwatch timer = new Stopwatch();
			b.findSolution(1);
			System.out.println("newSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
			
			b = new VarBoard(Generator.get(4, i), 4);
			b.counter = 0;
			timer = new Stopwatch();
			b.findSolution(2);
			System.out.println("newNewSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		}
*/
		// testing 25x25 board
		VarBoard b = new VarBoard(Generator.get(5, 4), 5);
		b.fancyPrint();
		Stopwatch timer = new Stopwatch();
		b.findSolution(1);
		System.out.println("newSolved: \t time: " + timer.elapsedTime() + "\t states: " + b.counter);
		b.fancyPrint();
	}
}
