import java.util.Collection;
import java.util.*;

public class Main
{
	static public void main(String[] args)
	{
		for (int i = 3; i < Generator.board5.length; i++) {
			//if (i == 8 || i == 7) continue;
			System.out.println();
			System.out.println("BOARD " + i);
			VarBoardA board = new VarBoardA(Generator.get(5, i), 5);
			//board.fancyPrint();
			Stopwatch timer = new Stopwatch();
			board.findSolution(1);
			System.out.println("Method 1 \t time: " + timer.elapsedTime() + " states: " + board.counter);
			//for (int j = 3; j < 30; j++){
			board = new VarBoardA(Generator.get(5, i), 5);
			//board.depthLimit = j;
			//board.fancyPrint();
			//System.out.println("method 2: " + j);
			timer = new Stopwatch();
			board.findSolution(2);
			System.out.println("Method 2 \t time: " + timer.elapsedTime() + " states: " + board.counter);
			//for (int j = 0; j < 16; j++) System.out.print(board.numbers[j] + " ");
			//board.fancyPrint();
			//}
		}
		
	}

}

