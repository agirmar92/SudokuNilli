package Sudoku;

public class Board
{
	int[][] board;
	int dim = 3;
	
	int counter = 0;
	
	public Board()
	{
		board = new int[dim*dim][dim*dim];
	}
	
	public Board(int[][] boardToSolve)
	{
		board = boardToSolve;
	}
	
	public boolean put(int x, int y, int val)
	{
		if (x >= dim*dim || y >= dim*dim || x < 0 || y < 0 || val > dim*dim) return false;
		board[x][y] = val;
		return isValid(x, y);
	}
	
	public boolean full()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) if (board[i][j] == 0) return false;
		}
		return true;
	}
	
	public void print()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) System.out.print(board[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public void fancyPrint()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			if (i % 3 == 0) System.out.println(" -------------------------");
			for (int j = 0; j < dim*dim; j++)
			{
				if (j % 3 == 0) System.out.print(" | ");
				else System.out.print(" ");
				System.out.print(board[i][j]);
			}
			System.out.print(" |");
			System.out.println();
			
		}
		System.out.println(" -------------------------");
		System.out.println();
	}
	
	public boolean isValid(int x, int y)
	{
		// line
		DistinctSet test = new DistinctSet(dim*dim);
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[x][i] > 0) if (test.insert(board[x][i])) return false;
		}
		test.clear();
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[i][y] > 0) if (test.insert(board[i][y])) return false;
		}
		test.clear();
		int box_x = x / 3;
		int box_y = y / 3;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				if (board[box_x+i][box_y+j] > 0) if (test.insert(board[box_x + i][box_y + j])) return false;
			}
		}
		test.clear();
		return true;
	}
	
	
	// in progress
	public boolean solve()
	{
		//if (++counter % 5000 == 0) fancyPrint();
		//System.out.println("solve");
		if (full()) 
		{
			fancyPrint();
			return true;
		}
		int i = 0, j = 0;
		search:
		for (i = 0; i < dim*dim; i++)
		{
			for (j = 0; j < dim*dim; j++) if (board[i][j] == 0) break search;
		}
		if (i > 3) System.out.println(i + " " + j);
		for (int n = 1; n <= 9; n++)
		{
			//System.out.println("val " + n);
			put(i, j, n);
			if (isValid(i, j))
			{
				if(solve()) return true;
			}
			put(i, j, 0);
		}
		return false;
	}
	
	
	
	
	static public class DistinctSet
	{
		boolean[] set;
		public DistinctSet(int size)
		{
			set = new boolean[size];
		}
		public boolean insert(int n)
		{
			n = n - 1;
			if (n < set.length)
			{
				if (set[n]) return true;
				else 
				{
					set[n] = true;
					return false;
				}
			}
			return true;
		}
		public void clear()
		{
			for (int i = 0; i < set.length; i++) set[i] = false;
		}
	}
}