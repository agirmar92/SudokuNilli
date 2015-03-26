package Sudoku;

import java.util.Random;

public class Generator {
	static int[][] board;
	int dim;
	
	static public int[][] getBoard(int dim)
	{
		board = new int[dim*dim][dim*dim];
		if (dim == 3) {
			// Today's Sudoku 26.mar.2015:
			board[0][0] = 8; board[0][1] = 7; board[0][4] = 1; board[0][5] = 3;
			board[1][3] = 9; board[1][6] = 6;
			board[2][1] = 1; board[2][2] = 9;
			board[3][3] = 5; board[3][8] = 4;
			board[4][0] = 1; board[4][1] = 4; board[4][4] = 6; board[4][7] = 3; board[4][8] = 8;
			board[5][0] = 5; board[5][5] = 2;
			board[6][6] = 8; board[6][7] = 7;
			board[7][2] = 3; board[7][5] = 1;
			board[8][3] = 4; board[8][4] = 2; board[8][7] = 6; board[8][8] = 1;
		} else if (dim == 4) {
			board[0][0] = 4; board[0][2] = 2; board[0][4] = 5; board[0][5] = 1; board[0][6] = 6; board[0][8] = 15; board[0][9] = 9;
			board[1][0] = 13; board[1][3] = 15; board[1][5] = 9; board[1][8] = 1; board[1][9] = 4; board[1][12] = 0;
			board[2][1] = 9; board[2][2] = 14; board[2][6] = 15; board[2][7] = 12; board[2][8] = 3; board[2][13] = 10; board[2][14] = 11;
			board[3][0] = 7; board[3][2] = 5; board[3][5] = 0; board[3][6] = 3; board[3][7] = 4; board[3][11] = 13; board[3][15] = 2;
			board[4][1] = 5; board[4][6] = 10; board[4][9] = 14; board[4][12] = 7; board[4][13] = 9; board[4][14] = 3;
			board[5][0] = 15; board[5][4] = 0; board[5][7] = 1; board[5][10] = 6; board[5][11] = 10; board[5][11] = 14; board[5][12] = 4; board[5][13] = 5;
			board[6][1] = 10; board[6][2] = 0; board[6][5] = 14; board[6][7] = 2; board[6][14] = 6; board[6][15] = 11;
			board[7][2] = 7; board[7][4] = 3; board[7][6] = 12; board[7][8] = 11; board[7][9] = 1; board[7][11] = 0; board[7][12] = 10; board[7][14] = 2;
			board[8][1] = 15; board[8][3] = 1; board[8][4] = 2; board[8][6] = 14; board[8][7] = 7; board[8][9] = 5; board[8][11] = 6; board[8][13] = 3;
			board[9][0] = 3; board[9][1] = 14; board[9][8] = 8; board[9][10] = 1; board[9][13] = 6; board[9][14] = 13;
			board[10][1] = 8; board[10][2] = 10; board[10][3] = 4; board[10][4] = 15; board[10][5] = 3; board[10][8] = 2; board[10][11] = 14; board[10][15] = 5;
			board[11][1] = 7; board[11][2] = 6; board[11][3] = 9; board[11][6] = 1; board[11][9] = 10; board[11][14] = 4;
			board[12][0] = 14; board[12][4] = 10; board[12][8] = 6; board[12][9] = 11; board[12][10] = 8; board[12][13] = 2; board[12][15] = 4;
			board[13][1] = 11; board[13][2] = 4; board[13][1] = 11;
		}
		
		return board;
	}
	
	public static int[][] getHardBoard() {
		Random randNum = new Random();
		HardBoard hb = new HardBoard();
		return hb.getBoard(randNum.nextInt(10) + 1);
	}

}
