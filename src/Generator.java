import java.lang.reflect.Constructor;
import java.util.*;

//import aima.core.util.datastructure.Pair;


public class Generator {
	static int[][] board;
	static int dim;
	
	public Generator(int d) {
		dim = d;
	}
	
	
	public static int[][] getHardBoard() {
		Random randNum = new Random();
		return HardBoard.getBoard(3, randNum.nextInt(10) + 1);
	}

    public static int[][] getHardBoard(int dim, int id){
        return HardBoard.getBoard(dim, id);
    }
	
	/*public VarBoard getRandomBoard() {
		VarBoard board = new VarBoard(dim);
		VarBoard cpy;
		Random rand = new Random();
		List<Pair<Pair<Integer,Integer>,Integer>> invalids = new ArrayList<Pair<Pair<Integer,Integer>,Integer>>();
		// Fill an empty board with 20 random numbers in random positions.
		for (int i = 0; i < 25; i++) {
			System.out.println("i: " + i);
			int x, y, value;
			boolean solvable;
			do {
				solvable = false;
				x = rand.nextInt(dim*dim); 
				y = rand.nextInt(dim*dim);
				Pair<Integer,Integer> pos = new Pair<Integer,Integer>(x,y);
				value = rand.nextInt(dim*dim) + 1;
				Pair<Pair<Integer,Integer>,Integer> trio = new Pair<Pair<Integer,Integer>,Integer>(pos,value);
				if (!invalids.contains(trio)) {
					if (board.board[x][y].val == 0) {
						if (board.put(x, y, value)) {
							board.fancyPrint();
							cpy = new VarBoard(board, dim);
							if (cpy.solve()) solvable = true;
							else {
								board.put(x, y, 0);
								invalids.add(trio);
							}
						}
						else board.put(x,  y,  0);
					}
				}
			} while (!solvable);
		}
		return board;
	}

	*/
}
