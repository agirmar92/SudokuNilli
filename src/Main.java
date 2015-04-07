import java.util.Collection;
import java.util.*;

public class Main
{
    static void printAllStatus(){
        Sudoku board = new Sudoku(Generator.get(3, 0), 3);
        System.out.println("Size 9x9:");
        System.out.println("\tID\tGen\tStatus\tM1-time\tM1-states\tStatus\tM2-time\tM2-states");
        for (int i = 0; i < Generator.board3.length; i++){
            System.out.print("\t" + i);
            Stopwatch timer = new Stopwatch();
            board = new Sudoku(Generator.get(3, i), 3);
            System.out.print( "\t" + timer.elapsedTime() + "\t");

            timer = new Stopwatch();
            board.findSolution(false, true, true, true);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\t");

            board = new Sudoku(Generator.get(3, i), 3);
            timer = new Stopwatch();
            board.findSolution(false, true, true, false);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\n");
        }
        System.out.println("Size 16x16:");
        for (int i = 0; i < Generator.board4.length; i++){
            //if( i == 7 || i == 8) continue;
            System.out.print("\t" + i);
            Stopwatch timer = new Stopwatch();
            board = new Sudoku(Generator.get(4, i), 4);
            System.out.print( "\t" + timer.elapsedTime() + "\t");

            timer = new Stopwatch();
            board.findSolution(false, true, true, true);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\t");

            board = new Sudoku(Generator.get(4, i), 4);
            timer = new Stopwatch();
            board.findSolution(false, true, true, false);
            System.out.print("\t" + timer.elapsedTime() + "\t" + board.counter + "\t\n");
        }
    }



	static public void main(String[] args)
	{

		for (int i = 0; i < Generator.board4.length; i++)
		{
			System.out.println();
			System.out.println("BOARD " + i);
			Sudoku s;
			Stopwatch timer;
			//if (!(i == 7 || i == 8)) {
				s = new Sudoku(Generator.get(4, i), 4);
				timer = new Stopwatch();
				s.findSolution(true, false, true, true);
				System.out.println("Var, not Val, Simple, Extended  " + i + "\t" + timer.elapsedTime() + " " + s.counter);				
			//}
			s = new Sudoku(Generator.get(4, i), 4);
			timer = new Stopwatch();
			s.findSolution(true, true, true, true);
			System.out.println("Var, Val, Simple, Extended      " + i + "\t" + timer.elapsedTime() + " " + s.counter);
		}
	}
    

}

