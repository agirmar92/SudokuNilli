package Sudoku;

public class HardBoard {
	public int[][] getBoard(int num) {
		switch (num) {
			case 1: return get1();
			case 2: return get2();
			case 3: return get3();
			case 4: return get4();
			case 5: return get5();
			case 6: return get6();
			case 7: return get7();
			case 8: return get8();
			case 9: return get9();
			case 10: return get10();
		}
		
		return null;
	}
	
	private int [][] get1() {
		int [][] board = new int[9][9];

		// row1:
		board[0][5] = 2;
		board[0][8] = 6;
		// row2:
		board[1][1] = 4;
		board[1][4] = 9;
		board[1][8] = 8;
		// row3:
		board[2][3] = 5;
		board[2][6] = 4;
		board[2][7] = 3;
		// row4:
		board[3][0] = 6;
		board[3][2] = 7;
		board[3][7] = 8;
		board[3][8] = 2;
		// row5:
		board[4][4] = 7;
		// row6:
		board[5][0] = 3;
		board[5][1] = 9;
		board[5][6] = 7;
		board[5][8] = 5;
		// row7:
		board[6][1] = 2;
		board[6][2] = 5;
		board[6][5] = 7;
		// row8:
		board[7][0] = 1;
		board[7][4] = 3;
		board[7][7] = 6;
		// row9:
		board[8][0] = 9;
		board[8][3] = 2;

		return board;
	}
	private int [][] get2() {
		int [][] board = new int[9][9];

		// row1:
		board[0][4] = 2;
		board[0][6] = 3;
		board[0][8] = 6;
		// row2:
		board[1][1] = 6;
		board[1][2] = 4;
		board[1][3] = 7;
		board[1][6] = 8;
		// row3:
		board[2][0] = 2;
		board[2][3] = 5;
		board[2][7] = 7;
		board[2][8] = 4;
		// row4:
		board[3][1] = 2;
		board[3][3] = 8;
		board[3][6] = 5;
		// row5:
		board[4][0] = 6;
		board[4][8] = 9;
		// row6:
		board[5][2] = 5;
		board[5][5] = 7;
		board[5][7] = 6;
		// row7:
		board[6][0] = 8;
		board[6][1] = 7;
		board[6][5] = 5;
		board[6][8] = 3;
		// row8:
		board[7][2] = 2;
		board[7][5] = 8;
		board[7][6] = 9;
		board[7][7] = 5;
		// row9:
		board[8][0] = 5;
		board[8][2] = 1;
		board[8][4] = 7;

		return board;
	}
	private int [][] get3() {
		int [][] board = new int[9][9];

		// row1:
		board[0][0] = 7;
		board[0][3] = 8;
		board[0][4] = 9;
		board[0][7] = 6;
		// row2:
		board[1][7] = 7;
		// row3:
		board[2][0] = 6;
		board[2][5] = 7;
		board[2][6] = 4;
		board[2][8] = 8;
		// row4:
		board[3][3] = 9;
		board[3][6] = 3;
		// row5:
		board[4][0] = 9;
		board[4][3] = 4;
		board[4][4] = 3;
		board[4][5] = 2;
		board[4][8] = 5;
		// row6:
		board[5][2] = 4;
		board[5][5] = 1;
		// row7:
		board[6][0] = 4;
		board[6][2] = 5;
		board[6][3] = 2;
		board[6][8] = 9;
		// row8:
		board[7][1] = 9;
		// row9:
		board[8][1] = 8;
		board[8][4] = 1;
		board[8][5] = 9;
		board[8][8] = 3;

		return board;
	}
	private int [][] get4() {
		int [][] board = new int[9][9];

		// row1:
		board[0][0] = 2;
		board[0][5] = 7;
		board[0][7] = 8;
		// row2:
		board[1][6] = 7;
		board[1][7] = 6;
		// row3:
		board[2][3] = 1;
		board[2][7] = 3;
		board[2][8] = 5;
		// row4:
		board[3][0] = 8;
		board[3][1] = 5;
		board[3][3] = 4;
		board[3][5] = 6;
		// row5:
		board[4][2] = 7;
		board[4][4] = 3;
		board[4][6] = 5;
		// row6:
		board[5][2] = 3;
		board[5][4] = 9;
		board[5][6] = 4;
		board[5][8] = 8;
		// row7:
		board[6][0] = 5;
		board[6][1] = 1;
		board[6][5] = 3;
		// row8:
		board[7][1] = 9;
		board[7][2] = 6;
		// row9:
		board[8][1] = 8;
		board[8][3] = 5;
		board[8][8] = 9;

		return board;
	}
	private int [][] get5() {
		int [][] board = new int[9][9];

		// row1:
		board[0][0] = 6;
		board[0][1] = 4;
		board[0][2] = 3;
		// row2:
		board[1][1] = 8;
		board[1][3] = 6;
		board[1][4] = 9;
		// row3:
		board[2][1] = 2;
		board[2][6] = 5;
		// row4:
		board[3][1] = 6;
		board[3][4] = 2;
		board[3][6] = 1;
		// row5:
		board[4][2] = 5;
		board[4][3] = 3;
		board[4][5] = 1;
		board[4][6] = 4;
		// row6:
		board[5][2] = 8;
		board[5][4] = 4;
		board[5][7] = 3;
		// row7:
		board[6][2] = 6;
		board[6][7] = 1;
		// row8:
		board[7][4] = 7;
		board[7][5] = 3;
		board[7][7] = 2;
		// row9:
		board[8][6] = 3;
		board[8][7] = 8;
		board[8][8] = 9;

		return board;
	}
	private int [][] get6() {
		int [][] board = new int[9][9];

		// row1:
		board[0][1] = 5;
		board[0][2] = 1;
		board[0][6] = 9;
		// row2:
		board[1][0] = 2;
		board[1][4] = 3;
		board[1][7] = 6;
		// row3:
		board[2][2] = 7;
		board[2][3] = 5;
		board[2][4] = 1;
		board[2][7] = 4;
		// row4:
		board[3][5] = 4;
		board[3][7] = 5;
		board[3][8] = 6;
		// row5:
		board[4][4] = 7;
		// row6:
		board[5][0] = 5;
		board[5][1] = 2;
		board[5][3] = 3;
		// row7:
		board[6][1] = 4;
		board[6][4] = 5;
		board[6][5] = 3;
		board[6][6] = 6;
		// row8:
		board[7][1] = 7;
		board[7][4] = 8;
		board[7][8] = 4;
		// row9:
		board[8][2] = 8;
		board[8][6] = 3;
		board[8][7] = 2;

		return board;
	}
	private int [][] get7() {
		int [][] board = new int[9][9];

		// row1:
		board[0][0] = 2;
		board[0][4] = 1;
		board[0][6] = 7;
		// row2:
		board[1][3] = 2;
		board[1][5] = 8;
		board[1][7] = 1;
		// row3:
		board[2][0] = 1;
		board[2][2] = 3;
		board[2][6] = 9;
		// row4:
		board[3][4] = 5;
		board[3][7] = 2;
		// row5:
		board[4][3] = 1;
		board[4][5] = 6;
		// row6:
		board[5][1] = 6;
		board[5][4] = 4;
		// row7:
		board[6][2] = 4;
		board[6][6] = 2;
		board[6][8] = 8;
		// row8:
		board[7][1] = 3;
		board[7][3] = 5;
		board[7][5] = 9;
		// row9:
		board[8][2] = 6;
		board[8][4] = 3;
		board[8][8] = 1;

		return board;
	}
	private int [][] get8() {
		int [][] board = new int[9][9];

		// row1:
		board[0][1] = 7;
		board[0][5] = 4;
		board[0][6] = 2;
		// row2:
		board[1][3] = 9;
		board[1][4] = 7;
		board[1][6] = 4;
		board[1][7] = 3;
		// row3:
		board[2][4] = 2;
		board[2][8] = 6;
		// row4:
		board[3][1] = 2;
		board[3][5] = 6;
		board[3][6] = 5;
		// row5:
		board[4][0] = 3;
		board[4][8] = 9;
		// row6:
		board[5][2] = 1;
		board[5][3] = 5;
		board[5][7] = 4;
		// row7:
		board[6][0] = 4;
		board[6][4] = 8;
		// row8:
		board[7][1] = 1;
		board[7][2] = 6;
		board[7][4] = 5;
		board[7][5] = 9;
		// row9:
		board[8][2] = 5;
		board[8][3] = 2;
		board[8][7] = 8;

		return board;
	}
	private int [][] get9() {
		int [][] board = new int[9][9];

		// row1:
		board[0][0] = 8;
		board[0][1] = 7;
		board[0][4] = 1;
		board[0][5] = 3;
		// row2:
		board[1][3] = 9;
		board[1][6] = 6;
		// row3:
		board[2][1] = 1;
		board[2][2] = 9;
		// row4:
		board[3][3] = 5;
		board[3][8] = 4;
		// row5:
		board[4][0] = 1;
		board[4][1] = 4;
		board[4][4] = 6;
		board[4][7] = 3;
		board[4][8] = 8;
		// row6:
		board[5][0] = 5;
		board[5][5] = 2;
		// row7:
		board[6][6] = 8;
		board[6][7] = 7;
		// row8:
		board[7][2] = 3;
		board[7][5] = 1;
		// row9:
		board[8][3] = 4;
		board[8][4] = 2;
		board[8][7] = 6;
		board[8][8] = 1;

		return board;
	}
	private int [][] get10() {
		int [][] board = new int[9][9];
		
		// row1:
		board[0][4] = 4;
		board[0][6] = 6;
		// row2:
		board[1][3] = 5;
		board[1][5] = 9;
		board[1][7] = 4;
		// row3:
		board[2][1] = 5;
		board[2][2] = 9;
		board[2][3] = 3;
		board[2][6] = 2;
		// row4:
		board[3][0] = 7;
		board[3][2] = 6;
		board[3][6] = 9;
		board[3][8] = 5;
		// row5: 
		// empty
		// row6:
		board[5][0] = 1;
		board[5][2] = 5;
		board[5][6] = 4;
		board[5][8] = 6;
		// row7:
		board[6][2] = 3;
		board[6][5] = 2;
		board[6][6] = 7;
		board[6][7] = 9;
		// row8:
		board[7][1] = 8;
		board[7][3] = 7;
		board[7][5] = 5;
		// row9:
		board[8][2] = 1;
		board[8][4] = 8;
		
		return board;
	}
	
}
