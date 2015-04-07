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

    static void printInfo(int dim, boolean variable, boolean value, boolean inference, boolean extended){
        int size;
        if(dim == 3) size = Generator.board3.length;
        else if(dim == 4) size = Generator.board4.length;
        else if(dim == 5) size = Generator.board5.length;
        else return;

        String svar = variable ? "T" : "F";
        String sval = value ? "T" : "F";
        String sinf = inference ? "T" : "F";
        String sext = extended ? "T" : "F";


        for (int i = 0; i < size; i++)
		{
			System.out.println();
			System.out.println("BOARD " + i);
			Sudoku s;
			Stopwatch timer;
            s = new Sudoku(Generator.get(dim, i), dim);
            timer = new Stopwatch();
            s.findSolution(variable, value, inference, extended);
            System.out.println("Var, Val, Simple, not Extended  " + i + "\t" + timer.elapsedTime() + " " + s.counter);				
		}
    }

    static void printBrute(int dim){
        int size;
        if(dim == 3) size = Generator.board3.length;
        else if(dim == 4) size = Generator.board4.length;
        else if(dim == 5) size = Generator.board5.length;
        else return;

        for (int i = 0; i < size; i++)
        {
            Sudoku s;
            Stopwatch timer;
            //if (!(i == 7 || i == 8)) {
            s = new Sudoku(Generator.get(dim, i), dim);
            timer = new Stopwatch();
            s.bruteSolve();
            System.out.println("BOARD  " + i + "\t" + timer.elapsedTime() + " " + s.counter);				
            //}
        }
    }


        


	static public void main(String[] args)
	{
        // printAllStatus();
        printInfo(3, true, true, true, false);
        // printBrute(3);
	
	}
    

}

