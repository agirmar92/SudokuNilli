package Sudoku;

public class Board
{
	Variable[][] board;
	int dim;	
	int counter = 0;
	
	public Board(int d)
	{
		dim = d;
		//board = new int[dim*dim][dim*dim];
		board = new Variable[dim*dim][dim*dim];
	}
	
	public Board(Variable[][] boardToSolve, int d)
	{
		board = boardToSolve;
		dim = d;
	}
	
	public boolean put(int x, int y, int val)
	{
		if (x >= dim*dim || y >= dim*dim || x < 0 || y < 0 || val > dim*dim) return false;
		if (isValid(x, y)) {
			board[x][y] = new Variable(dim, val, x, y);
			return true;
		}
		return false;
	}
	
	public boolean full()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) if (board[i][j] == null) return false;
		}
		return true;
	}
	
	public void print()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) {
				if (board[i][j] == null)
					System.out.print(0 + " ");
				else
					System.out.print(board[i][j].val + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void fancyPrint()
	{
		int extraEdge = 7;
		if (dim == 4) extraEdge = 25;
		for (int i = 0; i < dim*dim; i++)
		{
			if (i % dim == 0) printEdge(2 * dim * dim + extraEdge);
			for (int j = 0; j < dim*dim; j++)
			{
				if (j % dim == 0) System.out.print(" | ");
				else System.out.print(" ");
				if (dim == 4) if (0 <= board[i][j].val && board[i][j].val < 10) System.out.print(" "); 
				if (board[i][j] != null)
					System.out.print(board[i][j].val);
			}
			System.out.print(" |");
			System.out.println();
			
		}
		printEdge(2 * dim * dim + extraEdge);
		System.out.println();
	}
	
	public boolean isValid(int x, int y)
	{
		DistinctSet test = new DistinctSet(dim*dim);
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[x][i] != null)
				if (board[x][i].val > 0) if (test.insert(board[x][i].val)) return false;
		}
		test.clear();
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[i][y] != null)
				if (board[i][y].val > 0) if (test.insert(board[i][y].val)) return false;
		}
		test.clear();
		int box_x = x / dim;
		int box_y = y / dim;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				if (board[dim * box_x+i][dim * box_y+j] != null)
					if (board[dim * box_x+i][dim * box_y+j].val > 0) if (test.insert(board[dim * box_x + i][dim * box_y + j].val)) return false;
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
			for (j = 0; j < dim*dim; j++) if (board[i][j] == null) break search;
		}
		for (int n = 1; n <= dim*dim; n++)
		{
			put(i, j, n);
			if (isValid(i, j))
			{
				if(solve()) return true;
			}
		}
		put(i, j, 0);
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
		System.out.print(" ");
		for (int i = 0; i < n; i++) System.out.print("-");
		System.out.println();
	}
	
	public void removeVariable(int x, int y) {
		board[x][y] = null;
	}
}