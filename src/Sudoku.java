package Sudoku;
import java.util.*;

public class Sudoku
{
	Variable[][] board;
	int depthLimit = 10;
	int dim;	
	int counter = 0;
	int depth = 0;
	Stack<Variable> stack = new Stack<Variable>();
	
	
	/*
	 * Constructors
	 */
	public Sudoku(int[][] boardToSolve, int d)
	{
		dim = d;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++)
			{
				if (boardToSolve[i][j] == 0) 
				{
					board[i][j] = new Variable(dim, 0, i, j);
				}
				else  
				{
					board[i][j] = new Variable(dim, boardToSolve[i][j], i, j);
				}
			}
		}
	}
	
	public Sudoku(int d)
	{
		dim = d;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim*dim; i++) {
			for (int j = 0; j < dim*dim; j++) {
				board[i][j] = new Variable(dim, 0, i, j);
			}
		}
	}
	
	/*
	 * Solution functions
	 */
	
	public boolean findSolution(boolean varHeuristic, boolean val, boolean simple)
	{
		initialRestrict(true);
		if (simple) board = simpleInference();
		solve(varHeuristic, val);
		if (!isSolution()) {System.out.println("NOT A SOLUTION !!!"); return false;}
		else {/*System.out.println("OK SOLUTION !!!");*/ return true;}
	}
	
	public boolean solve(boolean varHeuristic, boolean valHeuristic)
	{
		//fancyPrint();
		depth++;
		counter++;
		//if (counter % 1000 == 0) numberVariables.print();
		if (full())
		{
			//fancyPrint();
			return true;
		}
		// Variable heuristic:
		int best = dim * dim + 1;
		int h = -1 ;
		int k = -1;;
		if (varHeuristic)
		{
			for(int i = 0; i < dim*dim; i++) 
			{
				for(int j = 0; j < dim*dim; j++)
				{
					Variable var = board[i][j];
					if (var.val == 0 && var.domain().size() < best) 
					{
						best = var.domain().size();
						h = i; 
						k = j;
					}
				}
			}
		}
		// not variable heuristic
		else
		{
			search:
			for (h = 0; h < dim*dim; h++)
			{
				for (k = 0; k < dim*dim; k++) if (board[h][k].val == 0) break search;
			}
		}
		
		Variable curr = board[h][k];

		if (valHeuristic && curr.domain().size() > 1)
		{
			PriorityQueue<Integer> q = new PriorityQueue<Integer>(16, new Comparator<Integer>() {
				public int compare(Integer i, Integer j)
				{
					int a = valHeuristic(curr, i);
					int b = valHeuristic(curr, j);
					if (a > b) return 1;
					if (a < b) return -1;
					return 0;
				}
				
			});
			for (Integer n : curr.domain()) q.add(n);
			while(!q.isEmpty())
			{
				int n = q.remove();
				//System.out.println(valHeuristic(curr,  n) + "\t \t " + depth);
				curr.val = n;
				stack.push(curr);
				//restrict
				Collection<Variable> neighbours = freeNeighbours(curr);
				for (Variable v : neighbours) v.restrict(curr.val);
				if(solve(varHeuristic, valHeuristic)) return true;
				// unrestrict
				for (Variable v : neighbours) v.allow(curr.val);
				stack.pop();
			}
		}
		else{
		for (int n : curr.domain())
		{
			curr.val = n;
			stack.push(curr);
			//restrict
			Collection<Variable> neighbours = freeNeighbours(curr);
			for (Variable v : neighbours) v.restrict(n);
			if(solve(varHeuristic, valHeuristic)) return true;
			// unrestrict
			for (Variable v : neighbours) v.allow(n);
			stack.pop();
		}}
		// de-assign
		curr.val = 0;
		depth--;
		return false;
	}
	
	
	/*
	 * Helper functions 
	 */
	public boolean full()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) if (board[i][j].val == 0) return false;
		}
		return true;
	}
	
	public int valHeuristic(Variable curr, int i)
	{

		for (Variable neighbour : freeNeighbours(curr))
		{
			if (neighbour.isOK(i)) counter++;
		}
		return counter;
	}

	public boolean isValid(int x, int y)
	{
		DistinctSet test = new DistinctSet(dim*dim);
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[x][i].val > 0) 
			{
				if (test.insert(board[x][i].val)) {return false;}
			}
		}
		test.clear();
		for (int i = 0; i < dim*dim; i++)
		{
			if (board[i][y].val > 0) if (test.insert(board[i][y].val)) {return false;}
		}
		test.clear();
		int box_x = x / dim;
		int box_y = y / dim;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				if (board[dim * box_x+i][dim * box_y+j].val > 0) if (test.insert(board[dim * box_x + i][dim * box_y + j].val)) return false;
			}
		}
		test.clear();
		return true;
	}
	
	public Collection<Variable> neighbours(Variable v)
	{
		Collection<Variable> set = new ArrayList<Variable>();
		int x = v.x;
		int y = v.y;
		for (int i = 0; i < dim*dim; i++) if (i != y) set.add(board[x][i]);
		for (int j = 0; j < dim*dim; j++) if (j != x) set.add(board[j][y]);
		int box_x = x / dim;
		int box_y = y / dim;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				Variable temp = board[dim * box_x+i][dim * box_y+j];
				if (temp != v && !set.contains(temp)) set.add(temp);
			}
		}
		
		return set;
	}
	
	public Collection<Variable> freeNeighbours(Variable v)
	{
		Collection<Variable> set = new ArrayList<Variable>();
		int x = v.x;
		int y = v.y;
		for (int i = 0; i < dim*dim; i++) if (i != y) if (board[x][i].val == 0) set.add(board[x][i]);
		for (int j = 0; j < dim*dim; j++) if (j != x) if (board[j][y].val == 0) set.add(board[j][y]);
		int box_x = x / dim;
		int box_y = y / dim;
		for (int i = 0; i < dim; i++)
		{
			for (int j = 0; j < dim; j++)
			{
				Variable temp = board[dim * box_x+i][dim * box_y+j];
				if (temp != v && !set.contains(temp) && temp.val == 0) set.add(temp);
			}
		}
		
		return set;
	}
	
	public boolean isSolution()
	{

		for (int i = 0; i < dim * dim; i++)
		{
			for (int j = 0; j < dim * dim; j++)
			{
				Variable var1 = board[i][j];
				for (int h = 0; h < dim * dim; h++)
				{
					for (int k = 0; k < dim * dim; k++)
					{
						Variable var2 = board[h][k];
						if (var1 != var2)
						{
							if (var1.x == var2.x || var1.y == var2.y || (var1.x / dim == var2.x / dim && var1.y / dim == var2.y / dim))
							{
								if (var1.val == var2.val) 
								{
									System.out.println(var1.toString());
									System.out.println(var2.toString());
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	/*
	 * Inference functions 
	 */
	public void initialRestrict(boolean more)
	{
		//int count = 0;
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++)
			{
				if (board[i][j].val > 0)
				{
					Collection<Variable> set = neighbours(board[i][j]);
					//System.out.println("\t" + set.size());
					for (Variable var : set)
					{
						if (var.val == 0)
						{
							var.restrict(board[i][j].val);
						}
					}
				}
			}
		}
		if (!more) return;
		// iterate through the free spots and try to restrict the board more
		boolean changes = true;
		while (changes) {
			changes = false;
			boolean place = true;
			for (int i = 0; i < dim*dim; i++) {
				for (int j = 0; j < dim*dim; j++) {
					Variable v = board[i][j];
					if (v.val == 0) {
						for (int num : v.domain()) {
							// check row
							for (int k = 0; k < dim*dim; k++) {
								if (board[i][k].val == 0) {
									if (board[i][k].domain().contains(num) && j != k) {
										place = false;
										break;
									}
								}
							}
							// check column
							if (!place) {
								place = true;
								for (int k = 0; k < dim*dim; k++) {
									if (board[k][j].val == 0) {
										if (board[k][j].domain().contains(num) && i != k) {
											place = false;
											break;
										}
									}
								}
							}
							// check section
							if (!place) {
								place = true;
								int box_x = i / dim;
								int box_y = j / dim;
								for (int k = 0; k < dim; k++)
								{
									for (int h = 0; h < dim; h++)
									{
										Variable temp = board[dim * box_x + k][dim * box_y + h];
										if (temp.val == 0 && temp != v) {
											if (temp.domain().contains(num)) {
												place = false;
												break;
											}
										}
									}
								}
							}
							// place it
							if (place) {
								//put(i, j, num);
								board[i][j].val = num;
								// restrict
								Collection<Variable> set = neighbours(board[i][j]);
								for (Variable var : set)
								{
									if (var.val == 0)
									{
										var.restrict(num);
									}
								}
								v.domain = null;
								changes = true;
								break;
							}
						}		
					}
				}
			}			
		}
	}
	
	private Variable[][] simpleInference()
	{
		Sudoku nboard = new Sudoku(dim);
		for (int i = 0; i < dim * dim; i++) for (int j = 0; j < dim * dim; j++) nboard.board[i][j] = board[i][j].copy();
		
		boolean changed = true;
		while (changed)
		{
			changed = false;
			search:
			for (int i = 0; i < dim * dim; i++)
			{
				for(int j = 0; j < dim * dim; j++)
				{
					// check if domain is 1, then assign and restrict
					Variable var = nboard.board[i][j];
					if (var.domain() != null && var.val == 0 && var.domain().size() == 1)
					{
						//assignments++;
						//System.out.println("restrict " + var.toString());
						int value = 0;
						for (int r : var.domain()) value = r;						
						var.val = value;
						Collection<Variable> neighbours = nboard.freeNeighbours(var);
						for (Variable v : neighbours) v.restrict(value);
						changed = true;
						break search;
					}
				}
			}
		}
		return nboard.board;
	}
	
	/*
	 * Print functions 
	 */
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
				System.out.print(board[i][j].val);
			}
			System.out.print(" |");
			System.out.println();
			
		}
		printEdge(2 * dim * dim + extraEdge);
		System.out.println();
	}
	
	private void printEdge(int n)
	{
		System.out.print(" ");
		for (int i = 0; i < n; i++) System.out.print("-");
		System.out.println();
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