//import java.lang.reflect.Constructor;
import java.util.*;

import aima.core.util.datastructure.Pair;

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
	
	public static VarBoard getRandomBoard(int dim) {
		VarBoard board = new VarBoard(dim);
		VarBoard cpy;
		Random rand = new Random();
		List<Pair<Pair<Integer,Integer>,Integer>> invalids = new ArrayList<Pair<Pair<Integer,Integer>,Integer>>();
		// Fill an empty board with 20 random numbers in random positions.
		int n = 0;
		if (dim == 3) n = 25;
		if (dim == 4) n = 100;
		if (dim == 5) n = 250;
		for (int i = 0; i < n; i++) {
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
							boolean solved = false;
							Stopwatch time = new Stopwatch();
							solved = cpy.newSolve();
							if (solved) solvable = true;
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

    static int [][][] board3 = {{{0, 0, 0, 0, 0, 2, 0, 0, 6}, 
		{0, 4, 0, 0, 9, 0, 0, 0, 8}, 
		{0, 0, 0, 5, 0, 0, 4, 3, 0}, 
		{6, 0, 7, 0, 0, 0, 0, 8, 2}, 
		{0, 0, 0, 0, 7, 0, 0, 0, 0}, 
		{3, 9, 0, 0, 0, 0, 7, 0, 5}, 
		{0, 2, 5, 0, 0, 7, 0, 0, 0}, 
		{1, 0, 0, 0, 3, 0, 0, 6, 0}, 
		{9, 0, 0, 2, 0, 0, 0, 0, 0}},
		{{0, 0, 0, 0, 2, 0, 3, 0, 6}, 
		{0, 6, 4, 7, 0, 0, 8, 0, 0}, 
		{2, 0, 0, 5, 0, 0, 0, 7, 4}, 
		{0, 2, 0, 8, 0, 0, 5, 0, 0}, 
		{6, 0, 0, 0, 0, 0, 0, 0, 9}, 
		{0, 0, 5, 0, 0, 7, 0, 6, 0}, 
		{8, 7, 0, 0, 0, 5, 0, 0, 3}, 
		{0, 0, 2, 0, 0, 8, 9, 5, 0}, 
		{5, 0, 1, 0, 7, 0, 0, 0, 0}},
		{{7, 0, 0, 8, 9, 0, 0, 6, 0}, 
		{0, 0, 0, 0, 0, 0, 0, 7, 0}, 
		{6, 0, 0, 0, 0, 7, 4, 0, 8}, 
		{0, 0, 0, 9, 0, 0, 3, 0, 0}, 
		{9, 0, 0, 4, 3, 2, 0, 0, 5}, 
		{0, 0, 4, 0, 0, 1, 0, 0, 0}, 
		{4, 0, 5, 2, 0, 0, 0, 0, 9}, 
		{0, 9, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 8, 0, 0, 1, 9, 0, 0, 3}},
		{{2, 0, 0, 0, 0, 7, 0, 8, 0}, 
		{0, 0, 0, 0, 0, 0, 7, 6, 0}, 
		{0, 0, 0, 1, 0, 0, 0, 3, 5}, 
		{8, 5, 0, 4, 0, 6, 0, 0, 0}, 
		{0, 0, 7, 0, 3, 0, 5, 0, 0}, 
		{0, 0, 3, 0, 9, 0, 4, 0, 8}, 
		{5, 1, 0, 0, 0, 3, 0, 0, 0}, 
		{0, 9, 6, 0, 0, 0, 0, 0, 0}, 
		{0, 8, 0, 5, 0, 0, 0, 0, 9}},
		{{6, 4, 3, 0, 0, 0, 0, 0, 0}, 
		{0, 8, 0, 6, 9, 0, 0, 0, 0}, 
		{0, 2, 0, 0, 0, 0, 5, 0, 0}, 
		{0, 6, 0, 0, 2, 0, 1, 0, 0}, 
		{0, 0, 5, 3, 0, 1, 4, 0, 0}, 
		{0, 0, 8, 0, 4, 0, 0, 3, 0}, 
		{0, 0, 6, 0, 0, 0, 0, 1, 0}, 
		{0, 0, 0, 0, 7, 3, 0, 2, 0}, 
		{0, 0, 0, 0, 0, 0, 3, 8, 9}},
		{{0, 5, 1, 0, 0, 0, 9, 0, 0}, 
		{2, 0, 0, 0, 3, 0, 0, 6, 0}, 
		{0, 0, 7, 5, 1, 0, 0, 4, 0}, 
		{0, 0, 0, 0, 0, 4, 0, 5, 6}, 
		{0, 0, 0, 0, 7, 0, 0, 0, 0}, 
		{5, 2, 0, 3, 0, 0, 0, 0, 0}, 
		{0, 4, 0, 0, 5, 3, 6, 0, 0}, 
		{0, 7, 0, 0, 8, 0, 0, 0, 4}, 
		{0, 0, 8, 0, 0, 0, 3, 2, 0}},
		{{2, 0, 0, 0, 1, 0, 7, 0, 0}, 
		{0, 0, 0, 2, 0, 8, 0, 1, 0}, 
		{1, 0, 3, 0, 0, 0, 9, 0, 0}, 
		{0, 0, 0, 0, 5, 0, 0, 2, 0}, 
		{0, 0, 0, 1, 0, 6, 0, 0, 0}, 
		{0, 6, 0, 0, 4, 0, 0, 0, 0}, 
		{0, 0, 4, 0, 0, 0, 2, 0, 8}, 
		{0, 3, 0, 5, 0, 9, 0, 0, 0}, 
		{0, 0, 6, 0, 3, 0, 0, 0, 1}},
		{{0, 7, 0, 0, 0, 4, 2, 0, 0}, 
		{0, 0, 0, 9, 7, 0, 4, 3, 0}, 
		{0, 0, 0, 0, 2, 0, 0, 0, 6}, 
		{0, 2, 0, 0, 0, 6, 5, 0, 0}, 
		{3, 0, 0, 0, 0, 0, 0, 0, 9}, 
		{0, 0, 1, 5, 0, 0, 0, 4, 0}, 
		{4, 0, 0, 0, 8, 0, 0, 0, 0}, 
		{0, 1, 6, 0, 5, 9, 0, 0, 0}, 
		{0, 0, 5, 2, 0, 0, 0, 8, 0}},
		{{8, 7, 0, 0, 1, 3, 0, 0, 0}, 
		{0, 0, 0, 9, 0, 0, 6, 0, 0}, 
		{0, 1, 9, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 5, 0, 0, 0, 0, 4}, 
		{1, 4, 0, 0, 6, 0, 0, 3, 8}, 
		{5, 0, 0, 0, 0, 2, 0, 0, 0}, 
		{0, 0, 0, 0, 0, 0, 8, 7, 0}, 
		{0, 0, 3, 0, 0, 1, 0, 0, 0}, 
		{0, 0, 0, 4, 2, 0, 0, 6, 1}}, 
		{{0, 0, 0, 0, 4, 0, 6, 0, 0}, 
		{0, 0, 0, 5, 0, 9, 0, 4, 0}, 
		{0, 5, 9, 3, 0, 0, 2, 0, 0}, 
		{7, 0, 6, 0, 0, 0, 9, 0, 5}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{1, 0, 5, 0, 0, 0, 4, 0, 6}, 
		{0, 0, 3, 0, 0, 2, 7, 9, 0}, 
		{0, 8, 0, 7, 0, 5, 0, 0, 0}, 
		{0, 0, 1, 0, 8, 0, 0, 0, 0}}
		};
	static int[][][] board4 = {{{5, 0, 3, 0, 6, 2, 0, 0, 1, 7, 0, 16, 10, 0, 0, 0 },
		{14, 0, 0, 16, 0, 10, 0, 0, 2, 5, 0, 0, 1, 0, 0, 0 },
		{0, 10, 15, 0, 0, 0, 16, 13, 4, 0, 0, 0, 0, 11, 12, 0 },
		{8, 0, 6, 0, 0, 1, 4, 5, 0, 0, 0, 14, 0, 0, 0, 3 },
		{0, 6, 0, 0, 0, 0, 11, 0, 0, 15, 0, 0, 8, 10, 4, 0 },
		{16, 0, 0, 0, 1, 0, 0, 2, 0, 0, 7, 11, 15, 5, 6, 0 },
		{0, 11, 1, 0, 0, 15, 0, 3, 0, 0, 0, 0, 0, 0, 7, 12 },
		{0, 0, 8, 0, 4, 0, 13, 0, 12, 2, 0, 1, 11, 0, 3, 0 },
		{0, 16, 0, 2, 3, 0, 15, 8, 0, 6, 0, 7, 0, 4, 0, 0 },
		{4, 15, 0, 0, 0, 0, 0, 0, 9, 0, 2, 0, 0, 7, 14, 0 },
		{0, 9, 11, 5, 16, 4, 0, 0, 3, 0, 0, 15, 0, 0, 0, 6 },
		{0, 8, 7, 10, 0, 0, 2, 0, 0, 11, 0, 0, 0, 0, 5, 0 },
		{15, 0, 0, 0, 11, 0, 0, 0, 7, 12, 9, 0, 0, 3, 0, 5 },
		{0, 12, 5, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 9, 10, 0},
		{0, 0, 0, 7, 0, 0, 5, 16, 0, 0, 15, 0, 6, 0, 0, 13 },
		{0, 0, 0, 3, 13, 0, 8, 9, 0, 0, 1, 5, 0, 16, 0, 2 }
		}, 
		
		{{14, 0, 2, 5, 0, 0, 0, 0, 13, 16, 0, 0, 1, 10, 0, 0}, 
			{11, 0, 0, 13, 0, 1, 0, 0, 6, 2, 0, 0, 0, 3, 14, 0}, 
			{7, 6, 15, 0, 3, 0, 0, 13, 14, 0, 0, 0, 11, 0, 0, 0}, 
			{1, 0, 10, 0, 0, 0, 0, 0, 7, 0, 0, 9, 0, 0, 0, 0}, 
			{12, 0, 0, 0, 11, 0, 9, 3, 0, 0, 0, 4, 10, 13, 0, 5}, 
			{10, 0, 0, 0, 1, 0, 6, 0, 0, 8, 0, 12, 0, 0, 0, 4}, 
			{0, 0, 16, 3, 0, 10, 14, 5, 0, 9, 1, 0, 0, 8, 6, 0}, 
			{0, 0, 0, 0, 16, 0, 0, 0, 0, 7, 14, 11, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 6, 4, 10, 0, 0, 0, 0, 15, 0, 0, 0, 0}, 
			{0, 7, 11, 0, 0, 16, 12, 0, 1, 13, 8, 0, 9, 4, 0, 0}, 
			{15, 0, 0, 0, 2, 0, 11, 0, 0, 6, 0, 14, 0, 0, 0, 10}, 
			{9, 0, 8, 1, 7, 0, 0, 0, 11, 4, 0, 2, 0, 0, 0, 15}, 
			{0, 0, 0, 0, 9, 0, 0, 10, 0, 0, 0, 0, 0, 15, 0, 2}, 
			{0, 0, 0, 14, 0, 0, 0, 7, 15, 0, 0, 3, 0, 6, 10, 9}, 
			{0, 2, 5, 0, 0, 0, 16, 6, 0, 0, 9, 0, 4, 0, 0, 11}, 
			{0, 0, 3, 9, 0, 0, 2, 11, 0, 0, 0, 0, 14, 1, 0, 8}}, 
			
			{{12, 2, 0, 7, 0, 0, 0, 16, 6, 15, 0, 0, 0, 3, 0, 14}, 
				{0, 0, 0, 9, 12, 0, 2, 0, 0, 0, 0, 0, 16, 1, 0, 6}, 
				{0, 11, 0, 13, 0, 0, 7, 0, 4, 0, 8, 0, 0, 0, 15, 0}, 
				{0, 8, 0, 3, 0, 0, 1, 0, 0, 0, 16, 13, 11, 0, 4, 2}, 
				{0, 0, 15, 0, 0, 0, 0, 0, 1, 6, 11, 0, 0, 0, 3, 0}, 
				{3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 16, 8, 0, 6, 12}, 
				{0, 0, 0, 14, 2, 0, 0, 0, 0, 12, 0, 0, 7, 4, 0, 0}, 
				{0, 12, 8, 4, 10, 0, 0, 14, 7, 0, 9, 3, 15, 0, 0, 0}, 
				{0, 0, 0, 16, 3, 7, 0, 4, 5, 0, 0, 9, 10, 14, 13, 0}, 
				{0, 0, 13, 8, 0, 0, 11, 0, 0, 0, 0, 12, 4, 0, 0, 0}, 
				{4, 9, 0, 5, 15, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 11}, 
				{0, 14, 0, 0, 0, 6, 13, 1, 0, 0, 0, 0, 0, 7, 0, 0}, 
				{9, 1, 0, 15, 7, 4, 0, 0, 0, 16, 0, 0, 3, 0, 14, 0}, 
				{0, 3, 0, 0, 0, 9, 0, 2, 0, 1, 0, 0, 5, 0, 10, 0}, 
				{16, 0, 4, 12, 0, 0, 0, 0, 0, 3, 0, 14, 1, 0, 0, 0}, 
				{5, 0, 14, 0, 0, 0, 16, 15, 9, 0, 0, 0, 2, 0, 12, 4}},
				
				{
					 {4, 0, 0, 0, 0, 3, 11, 9, 2, 0, 5, 0, 8, 0, 12, 10},
					 {2, 0, 15, 0, 0, 6, 12, 13, 0, 14, 0, 4, 0, 0, 1, 3},
					 {0, 14, 3, 9, 15, 8, 4, 0, 7, 1, 0, 0, 11, 0, 6, 5},
					 {0, 0, 11, 6, 10, 0, 0, 0, 0, 8, 0, 0, 0, 4, 0, 15},

					 {14, 0, 9, 13, 0, 5, 6, 0, 0, 4, 1, 2, 3, 7, 0, 0},
					 {0, 0, 6, 0, 4, 0, 0, 15, 11, 3, 0, 0, 5, 2, 16, 13},
					 {8, 4, 2, 0, 0, 11, 0, 1, 0, 16, 6, 0, 10, 0, 14, 0},
					 {11, 15, 0, 7, 14, 16, 0, 0, 8, 0, 10, 12, 1, 6, 4, 0},

					 {0, 3, 10, 15, 8, 1, 0, 5, 0, 0, 2, 7, 6, 0, 9, 16},
					 {0, 6, 0, 1, 0, 10, 15, 0, 13, 0, 8, 0, 0, 12, 5, 2},
					 {9, 11, 8, 2, 0, 0, 3, 12, 1, 0, 0, 5, 0, 10, 0, 0},
					 {0, 0, 12, 4, 2, 13, 9, 0, 0, 11, 15, 0, 14, 1, 0, 8},

					 {3, 0, 4, 0, 0, 0, 5, 0, 0, 0, 0, 15, 16, 8, 0, 0},
					 {15, 10, 0, 5, 0, 0, 16, 8, 0, 2, 4, 1, 13, 3, 11, 0},
					 {1, 8, 0, 0, 3, 0, 10, 0, 6, 5, 16, 0, 0, 9, 0, 7},
					 {6, 2, 0, 11, 0, 4, 0, 7, 14, 10, 3, 0, 0, 0, 0, 1}
				},

				{   {0, 4, 15, 0, 5, 1, 0, 12, 0, 0, 8, 0, 6, 13, 7, 0},
					{10, 0, 0, 0, 15, 6, 3, 7, 0, 5, 4, 9, 0, 0, 16, 11},
					{3, 8, 0, 1, 9, 4, 11, 10, 0, 0, 0, 0, 0, 5, 14, 2},
					{0, 0, 6, 7, 0, 0, 0, 0, 0, 15, 2, 1, 9, 4, 12, 10},
					{0, 0, 0, 8, 0, 15, 12, 0, 6, 7, 0, 13, 5, 2, 11, 0},
					{0, 12, 7, 0, 8, 0, 14, 0, 4, 1, 3, 0, 16, 15, 10, 13},
					{0, 9, 0, 0, 11, 13, 0, 0, 0, 0, 0, 2, 14, 0, 3, 6},
					{2, 3, 11, 13, 6, 16, 0, 4, 5, 0, 0, 0, 0, 7, 9, 0},
					{0, 1, 13, 0, 0, 0, 0, 3, 11, 0, 5, 10, 12, 8, 2, 14},
					{15, 16, 0, 11, 10, 0, 0, 0, 0, 0, 1, 3, 0, 0, 4, 0},
					{7, 10, 12, 5, 0, 11, 6, 2, 0, 16, 0, 8, 0, 3, 1, 0},
					{0, 2, 8, 3, 14, 0, 1, 13, 0, 4, 15, 0, 10, 0, 0, 0},
					{12, 11, 3, 2, 1, 8, 9, 0, 0, 0, 0, 0, 4, 14, 0, 0},
					{13, 15, 9, 0, 0, 0, 0, 0, 8, 2, 7, 14, 3, 0, 6, 1},
					{14, 7, 0, 0, 2, 10, 16, 0, 13, 3, 6, 12, 0, 0, 0, 5},
					{0, 6, 5, 10, 0, 3, 0, 0, 1, 0, 11, 4, 0, 16, 15, 0}},
					
				{{15, 12, 1, 0, 5, 11, 10, 0, 13, 8, 0, 0, 0, 6, 2, 3},
					{0, 0, 6, 14, 8, 15, 0, 13, 0, 0, 12, 0, 11, 0, 0, 9},
					{0, 11, 5, 13, 0, 14, 9, 0, 2, 0, 0, 0, 1, 0, 16, 0},
					{0, 8, 9, 10, 0, 0, 3, 0, 0, 15, 11, 14, 0, 4, 0, 12},
					{0, 2, 0, 0, 13, 0, 0, 4, 6, 0, 15, 11, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 6, 7, 12, 0, 9, 0, 8, 13, 3, 0},
					{12, 5, 7, 0, 2, 0, 0, 14, 0, 0, 0, 16, 0, 9, 0, 0},
					{0, 0, 0, 0, 0, 0, 15, 3, 10, 7, 0, 0, 0, 0, 0, 11},
					{1, 0, 0, 0, 0, 0, 7, 5, 8, 16, 0, 0, 0, 0, 0, 0},
					{0, 0, 13, 0, 4, 0, 0, 0, 9, 0, 0, 12, 0, 3, 15, 16},
					{0, 10, 4, 11, 0, 16, 0, 9, 15, 1, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 11, 3, 0, 8, 7, 0, 0, 13, 0, 0, 9, 0},
					{6, 0, 2, 0, 14, 4, 1, 0, 0, 13, 0, 0, 9, 16, 12, 0},
					{0, 14, 0, 8, 0, 0, 0, 10, 0, 12, 6, 0, 3, 15, 13, 0},
					{4, 0, 0, 5, 0, 12, 0, 0, 11, 0, 16, 7, 2, 14, 0, 0},
					{16, 13, 12, 0, 0, 0, 8, 6, 0, 4, 3, 10, 0, 5, 11, 1}},
					
					//{0, 6, 5, 10, 0, 3, 0, 0, 1, 0, 11, 4, 0, 16, 15, 0}},
					
					
					{
						{9, 16, 1, 0, 3, 0, 0, 7, 0, 10, 4, 8, 0, 0, 11, 0},
						{0, 0, 0, 0, 0, 9, 0, 0, 3, 0, 5, 14, 0, 0, 15, 1},
						{0, 6, 11, 0, 0, 0, 2, 12, 0, 1, 7, 16, 3, 0, 0, 0},
						{0, 0, 0, 0, 10, 4, 5, 1, 0, 13, 11, 0, 2, 12, 16, 0},

						{1, 12, 0, 0, 6, 8, 0, 0, 0, 7, 16, 13, 15, 4, 0, 0},
						{0, 0, 6, 13, 0, 0, 4, 0, 10, 0, 15, 0, 0, 9, 0, 11},
						{5, 0, 0, 15, 0, 14, 0, 13, 0, 0, 0, 0, 12, 16, 6, 3},
						{0, 4, 0, 9, 0, 0, 10, 11, 14, 6, 3, 1, 0, 13, 7, 8},

						{8, 2, 15, 0, 13, 7, 11, 4, 6, 5, 0, 0, 16, 0, 1, 0},
						{13, 11, 5, 3, 0, 0, 0, 0, 1, 0, 10, 0, 7, 0, 0, 6},
						{6, 0, 4, 0, 0, 16, 0, 10, 0, 11, 0, 0, 13, 3, 0, 0},
						{0, 0, 9, 16, 8, 3, 1, 0, 0, 0, 12, 7, 0, 0, 4, 5},

						{0, 9, 7, 8, 0, 6, 13, 0, 2, 3, 1, 15, 0, 0, 0, 0},
						{0, 0, 0, 10, 2, 1, 3, 0, 7, 12, 0, 0, 0, 15, 13, 0},
						{3, 15, 0, 0, 7, 11, 0, 5, 0, 0, 13, 0, 0, 0, 0, 0},
						{0, 13, 0, 0, 12, 10, 15, 0, 16, 0, 0, 5, 0, 6, 3, 7}
					}
	};

	static public int[][] get(int size, int index)
	{
		if (size == 3) return board3[index];
		if (size == 4) return board4[index];
		return null;
	}
}
