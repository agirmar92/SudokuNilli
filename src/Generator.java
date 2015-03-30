package Sudoku;

import java.lang.reflect.Constructor;
import java.util.*;

//import aima.core.util.datastructure.Pair;


public class Generator {
	static int[][] board;
	static int dim;
	
	public Generator(int d) {
		dim = d;
	}
	
	
	static public int[][] getBoard4()
	{
		board = new int[16][16];
		
		board[0][0] = 5; board[0][2] = 3; board[0][4] = 6; board[0][5] = 2; board[0][8] = 1; board[0][9] = 7; board[0][11] = 16; board[0][12] = 10;
		board[1][0] = 14; board[1][3] = 16; board[1][5] = 10; board[1][8] = 2; board[1][9] = 5; board[1][12] = 1;
		board[2][1] = 10; board[2][2] = 15; board[2][6] = 16; board[2][7] = 13; board[2][8] = 4; board[2][13] = 11; board[2][14] = 12;
		board[3][0] = 8; board[3][2] = 6; board[3][5] = 1; board[3][6] = 4; board[3][7] = 5; board[3][11] = 14; board[3][15] = 3;
		board[4][1] = 6; board[4][6] = 11; board[4][9] = 15; board[4][12] = 8; board[4][13] = 10; board[4][14] = 4;
		board[5][0] = 16; board[5][4] = 1; board[5][7] = 2; board[5][10] = 7; board[5][11] = 11; board[5][12] = 15; board[5][13] = 5; board[5][14] = 6;
		board[6][1] = 11; board[6][2] = 1; board[6][5] = 15; board[6][7] = 3; board[6][14] = 7; board[6][15] = 12;
		board[7][2] = 8; board[7][4] = 4; board[7][6] = 13; board[7][8] = 12; board[7][9] = 2; board[7][11] = 1; board[7][12] = 11; board[7][14] = 3;
		board[8][1] = 16; board[8][3] = 2; board[8][4] = 3; board[8][6] = 15; board[8][7] = 8; board[8][9] = 6; board[8][11] = 7; board[8][13] = 4;
		board[9][0] = 4; board[9][1] = 15; board[9][8] = 9; board[9][10] = 2; board[9][13] = 7; board[9][14] = 14;
		board[10][1] = 9; board[10][2] = 11; board[10][3] = 5; board[10][4] = 16; board[10][5] = 4; board[10][8] = 3; board[10][11] = 15; board[10][15] = 6;
		board[11][1] = 8; board[11][2] = 7; board[11][3] = 10; board[11][6] = 2; board[11][9] = 11; board[11][14] = 5;
		board[12][0] = 15; board[12][4] = 11; board[12][8] = 7; board[12][9] = 12; board[12][10] = 9; board[12][13] = 3; board[12][15] = 5;
		board[13][1] = 12; board[13][2] = 5; board[13][7] = 1; board[13][8] = 6; board[13][9] = 14; board[13][13] = 9; board[13][14] = 10;
		board[14][3] = 7; board[14][6] = 5; board[14][7] = 16; board[14][10] = 15; board[14][12] = 6; board[14][15] = 13;
		board[15][3] = 3; board[15][4] = 13; board[15][6] = 8; board[15][7] = 9; board[15][10] = 1; board[15][11] = 5; board[15][13] = 16; board[15][15] = 2;
		
		return board;
	}
	
	public static int[][] getHardBoard() {
		Random randNum = new Random();
		HardBoard hb = new HardBoard();
		return hb.getBoard(randNum.nextInt(10) + 1);
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
				}};

	static public int[][] get(int size, int index)
	{
		if (size == 3) return board3[index];
		if (size == 4) return board4[index];
		return null;
	}
}
