import java.util.Collection;
import java.util.*;

public class Main
{
    static void printAllStatus(){
        VarBoardA board = new VarBoardA(Generator.get(3, 0), 3);
        System.out.println("Size 9x9:");
        System.out.println("\tID\tGen\tStatus\tM1-time\tM1-states\tStatus\tM2-time\tM2-states");
        for (int i = 0; i < Generator.board3.length; i++){
            System.out.print("\t" + i);
            Stopwatch timer = new Stopwatch();
            board = new VarBoardA(Generator.get(3, i), 3);
            System.out.print( "\t" + timer.elapsedTime() + "\t");

            timer = new Stopwatch();
            board.findSolution(1);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\t");

            board = new VarBoardA(Generator.get(3, i), 3);
            timer = new Stopwatch();
            board.findSolution(2);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\n");
        }
        System.out.println("Size 16x16:");
        for (int i = 0; i < Generator.board4.length; i++){
            //if( i == 7 || i == 8) continue;
            System.out.print("\t" + i);
            Stopwatch timer = new Stopwatch();
            board = new VarBoardA(Generator.get(4, i), 4);
            System.out.print( "\t" + timer.elapsedTime() + "\t");

            timer = new Stopwatch();
            board.findSolution(1);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\t");

            board = new VarBoardA(Generator.get(4, i), 4);
            timer = new Stopwatch();
            board.findSolution(2);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\n");
        }
    }



	static public void main(String[] args)
	{
        printAllStatus();
        /*
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
	    */	
	}
    

}

