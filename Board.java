package Sudoku;

public class Board
{
	int[][] board;
	int dim = 5;
	
	int counter = 0;
	
	public Board()
	{
		board = new int[dim*dim][dim*dim];
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
			if (i % dim == 0) printEdge(2 * dim * dim + 1);
			for (int j = 0; j < dim*dim; j++)
			{
				if (j % dim == 0) System.out.print("|");
				else System.out.print(" ");
				System.out.print(board[i][j]);
			}
			System.out.print("|");
			System.out.println();
			
		}
		printEdge(2 * dim * dim + 1);
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
		int box_x = x / dim;
		int box_y = y / dim;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				if (board[dim * box_x+i][dim * box_y+j] > 0) if (test.insert(board[dim * box_x + i][dim * box_y + j])) return false;
			}
		}
		test.clear();
		return true;
	}
	
	
	// in progress
	public boolean solve()
	{
		if (++counter % 50000 == 0) fancyPrint();
		//System.out.println("solve");
		if (full()) 
		{
			System.out.println("SOLVED");
			fancyPrint();
			return true;
		}
		int i = 0, j = 0;
		search:
		for (i = 0; i < dim*dim; i++)
		{
			for (j = 0; j < dim*dim; j++) if (board[i][j] == 0) break search;
		}
		for (int n = 1; n <= dim*dim; n++)
		{
			//System.out.println("consider " + i + " " + j + " val " + n);
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
	private void printEdge(int n)
	{
		for (int i = 0; i < n; i++) System.out.print("-");
		System.out.println();
	}
}