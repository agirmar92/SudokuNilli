package Sudoku;

import java.util.Random;

public class Generator {
	static int[][] board;
	int dim;
	
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

}
