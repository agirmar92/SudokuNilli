package Sudoku;
//import Sudoku.Board.DistinctSet;
import java.util.*;

public class VarBoard
{
	Variable[][] board;
	int dim;	
	int counter = 0;
	PriorityQueue<Variable> queue;
	ArrayList<Variable> vars;
	Scanner in = new Scanner(System.in);
	int index = 0;
	int depth = 0;
	int assignments;
	
	public VarBoard(int d)
	{
		dim = d;
		assignments = 0;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim*dim; i++) {
			for (int j = 0; j < dim*dim; j++) {
				board[i][j] = new Variable(dim, 0, i, j);
			}
		}
	}
	
	public VarBoard(int[][] boardToSolve, int d)
	{
		dim = d;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++)
			{
				if (boardToSolve[i][j] == 0) board[i][j] = new Variable(dim, 0, i, j);
				else  {board[i][j] = new Variable(dim, boardToSolve[i][j], i, j); assignments++;}
			}
		}
		/*queue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
	        public int compare(Variable var1, Variable var2) 
	        {
	        	Collection<Integer> d1 = var1.domain();
	        	Collection<Integer> d2 = var2.domain();
	        	
	        	if (d1 == null && d2 == null) return 0;
	        	if (d1 == null) return 1;
	        	if (d2 == null) return -1;
	        	
	        	if (d1.size() > d2.size()) return 1;
	        	if (d1.size() < d2.size()) return -1;
	        	return 0;
	        }
		});
		
		for (int i = 0; i < dim*dim; i++) for (int j = 0; j < dim*dim; j++) queue.add(board[i][j]);*/
	}
	
	// copy constructor
	public VarBoard(VarBoard boardToSolve, int d)
	{
		dim = d;
		assignments = boardToSolve.assignments;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++)
			{
				if (boardToSolve.board[i][j].val == 0) board[i][j] = new Variable(dim, 0, i, j);
				else board[i][j] = new Variable(dim, boardToSolve.board[i][j].val, i, j);
			}
		}
		/*queue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
	        public int compare(Variable var1, Variable var2) 
	        {
	        	Collection<Integer> d1 = var1.domain();
	        	Collection<Integer> d2 = var2.domain();
	        	
	        	if (d1 == null && d2 == null) return 0;
	        	if (d1 == null) return 1;
	        	if (d2 == null) return -1;
	        	
	        	if (d1.size() > d2.size()) return 1;
	        	if (d1.size() < d2.size()) return -1;
	        	return 0;
	        }
		});
		
		for (int i = 0; i < dim*dim; i++) for (int j = 0; j < dim*dim; j++) if (board[i][j].domain != null) queue.add(board[i][j]);*/
	}
	
	public VarBoard (VarBoard old)
	{
		counter = old.counter;
		dim = old.dim;
		depth = old.depth;
		assignments = old.assignments;
		board = new Variable[dim*dim][dim*dim];
		for (int i = 0; i < dim * dim; i++)
		{
			for (int j = 0; j < dim * dim; j++)
			{
				board[i][j] = new Variable(old.board[i][j]);
			}
		}
	}
	
	public boolean put(int x, int y, int val)
	{
		if (x >= dim*dim || y >= dim*dim || x < 0 || y < 0 || val > dim*dim) return false;
		board[x][y].val = val;
		return isValid(x, y);
	}
	
	public boolean full()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) if (board[i][j].val == 0) return false;
		}
		return true;
	}
	
	public void print()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) System.out.print(board[i][j].val + " ");
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
	
	
	// in progress
	public boolean solve()
	{
		//fancyPrint();
		counter++;
		if (full()) 
		{
			System.out.println("SOLVED");
			System.out.println("assignments: " + assignments);
			fancyPrint();
			return true;
		}
		int i = 0, j = 0;
		search:
		for (i = 0; i < dim*dim; i++)
		{
			for (j = 0; j < dim*dim; j++) if (board[i][j].val == 0) break search;
		}
		int n;
		assignments++;
		for (n = 1; n <= dim*dim; n++)
		{
			put(i, j, n);
			if (isValid(i, j))
			{
				if(solve()) return true;
			}
		}
		put(i, j, 0);
		assignments--;
		return false;
	}
	
	private void printEdge(int n)
	{
		System.out.print(" ");
		for (int i = 0; i < n; i++) System.out.print("-");
		System.out.println();
	}
	
	/*
	 * Returns a collection with all the variables in the same row, column and box, 
	 * not including the variable
	 */
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
	/*
	 * This function looks at the hardcoded numbers and restricts the domains
	 * of the other numbers so that they are compatible with the hardcoded ones
	 */
	public void initialRestrict()
	{
		//int count = 0;
		System.out.println("initial assignments " + assignments);
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
		/*
		PriorityQueue<Variable> nqueue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
	        public int compare(Variable var1, Variable var2) 
	        {
	        	Collection<Integer> d1 = var1.domain();
	        	Collection<Integer> d2 = var2.domain();
	        	
	        	if (d1 == null && d2 == null) return 0;
	        	if (d1 == null) return 1;
	        	if (d2 == null) return -1;
	        	
	        	if (d1.size() > d2.size()) return 1;
	        	if (d1.size() < d2.size()) return -1;
	        	return 0;
	        }
		});
		vars = new ArrayList<Variable>();
		while(!queue.isEmpty()) 
		{
			Variable temp = queue.remove();
			nqueue.add(temp);
			vars.add(temp);
		}
		queue = nqueue;*/
		
	}
	/*
	 * New version of solve, uses priority queue, initialRestrict() should be called before
	 * this function
	 */
	public boolean newSolve()
	{
		depth++;
		counter++;
		//printAll();
		//in.next();
		if (full())
		{
			System.out.println("SOLVED");
			System.out.println("assignments: " + assignments + " " + depth);
			fancyPrint();
			return true;
		}
		
		// Method 1: random order
		/*int i = -1, j = -1;
		search:
		for (i = 0; i < dim*dim; i++)
		{
			for (j = 0; j < dim * dim; j++) 
			{
				Variable var = board[i][j];
				if (var.val == 0) break search;
			}
		}
		Variable curr = board[i][j];*/

		// Method 2: use queue
		//Variable curr = queue.remove();

		// Method 3: use initial order:
		//Variable curr = vars.get(index++);
		
		// Method 4
		int best = dim * dim + 1;
		int h = -1 ;
		int k = -1;;
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
		/*int i = 0, j = 0;
		search:
		for (i = 0; i < dim*dim; i++)
		{
			for (j = 0; j < dim * dim; j++) 
			{
				Variable var = board[i][j];
				if (var.val == 0) break search;
			}
		}*/
		assignments++;
		Variable curr = board[h][k];
		for (int n : curr.domain())
		{
			curr.val = n;
			//if (depth == 20) {System.out.println(depth); System.out.println(curr.toString());}
			if (!isValid(curr.x, curr.y)) 
			{
				System.out.println("NOT VALID !!! \t depth " + depth + " " + counter);
				System.out.println(curr.toString());
				fancyPrint();
				in.next();
			}
			if (isValid(curr.x, curr.y))
			{
				// restrict
				Collection<Variable> neighbours = freeNeighbours(curr);
				for (Variable v : neighbours) v.restrict(n);
				if(newSolve()) return true;
				// unrestrict
				for (Variable v : neighbours) v.allow(n);
			}
		}
		// unput
		curr.val = 0;
		assignments--;
		//index--;
		//queue.remove(curr);
		//System.out.println("backtrack");
		depth--;
		return false;
	}
	
	
	public void findSolution()
	{
		initialRestrict();
		board = simpleInference();
		newSolve();
		if (!isSolution()) System.out.println("NOT A SOLUTION !!!");
		else System.out.println("OK SOLUTION !!!");
	}
	
	private Variable[][] simpleInference()
	{
		VarBoard nboard = new VarBoard(dim);
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
						assignments++;
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
	
	public boolean newNewSolve()
	{
		//fancyPrint();
		depth++;
		counter++;
		//System.out.println("newNewSolve");
		//if (counter % 1000 == 0) {System.out.println("Counter: \t" + counter); /*fancyPrint();*/}
		if (full())
		{
			fancyPrint();
			System.out.println("SOLVED \t counter: " + counter);
			System.out.println("assignments: " + assignments + " " + depth);
			return true;
		}

		// Method 4
		int best = dim * dim + 1;
		int h = -1 ;
		int k = -1;;
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
		Variable curr = board[h][k];
		assignments++;
		for (int n : curr.domain())
		{
			curr.val = n;
			if(!isValid(curr.x, curr.y)) {System.out.println("NOT VALID !!!");}
			if (isValid(curr.x, curr.y))
			{
				// restrict
				Collection<Variable> neighbours = freeNeighbours(curr);
				for (Variable v : neighbours) v.restrict(n);
				
				if (depth % 20 == 0)
				{
					assignments--;
					VarBoard newBoard = new VarBoard(this);
					newBoard.assignments = this.assignments;
					newBoard.board = newBoard.simpleInference();
					if (newBoard.newNewSolve()) return true;
					assignments++;
				}
				else{
				if(newNewSolve()) return true;
				// unrestrict
				for (Variable v : neighbours) v.allow(n);}
			}
		}
		// unput
		curr.val = 0;
		assignments--;
		depth--;
		return false;
	}
	
	
	
	/*
	 * Used for checking if there are duplicates in a row, column, or box
	 * the insert function adds a number to the set and returns true if
	 * it already exists in the set, otherwise false
	 */
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
	
	public void printAll()
	{
		fancyPrint();
		for (int i = 0; i < dim * dim; i++) for (int j = 0; j < dim * dim; j++) System.out.println(board[i][j].toString());
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
								if (var1.val == var2.val) return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}



