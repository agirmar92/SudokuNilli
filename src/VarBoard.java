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
	ArrayList<ArrayList<Variable>> ordering;
	
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
		ordering = new ArrayList<ArrayList<Variable>>(dim * dim);
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
		queue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
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
		
		for (int i = 0; i < dim*dim; i++) for (int j = 0; j < dim*dim; j++) if (board[i][j].domain != null) queue.add(board[i][j]);
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
		
		/*queue = null;
		queue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
=======
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
								put(i, j, num);
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
		
		/*
		PriorityQueue<Variable> nqueue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {
>>>>>>> a2caf2be87ec0ea0de4163690602583521c6a2b4
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
		//System.out.println("adding to queue");
		for (int i = 0; i < dim  *  dim; i++) for (int j = 0; j < dim *  dim; j++)
		{
			Variable var = board[i][j];
			if (var.val == 0) 
			{
				//System.out.println(var.toString());
				queue.add(var);
			}
		}*/
		//System.out.println("queue done");
		
	}
	/*
	 * New version of solve, uses priority queue, initialRestrict() should be called before
	 * this function
	 */
	public boolean newSolve()
	{
		depth++;
		counter++;
		//if (counter % 100000 == 0) fancyPrint();
		//printAll();
		//in.next();
		if (full())
		{
			//System.out.println("SOLVED");
			//System.out.println("assignments: " + assignments + " " + depth);
			//fancyPrint();
			return true;
		}
		//fancyPrint();
		//in.next();
		
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
		//Variable next = queue.remove();
		//System.out.println(next.toString());

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
			/*if (!isValid(curr.x, curr.y)) 
			{
				System.out.println("NOT VALID !!! \t depth " + depth + " " + counter);
				System.out.println(curr.toString());
				fancyPrint();
				in.next();
			}*/
			//restrict
			Collection<Variable> neighbours = freeNeighbours(curr);
			for (Variable v : neighbours) v.restrict(n);
			if(newSolve()) return true;
			// unrestrict
			for (Variable v : neighbours) v.allow(n);
		}
		// unput
		curr.val = 0;
		assignments--;
		//index--;
		//System.out.println("add this variable to queue: " + curr.toString());
		//queue.add(curr);
		//System.out.println("backtrack");
		depth--;
		return false;
	}
	
	
	public void findSolution(int type)
	{
		if (type == 1) {
			initialRestrict();
			//System.out.println(ordering.toString());
			board = simpleInference();
			newSolve();
			if (!isSolution()) System.out.println("NOT A SOLUTION !!!");
			else System.out.println("OK SOLUTION !!!");}
		else 
		{
			initialRestrict();
			board = simpleInference();
			ordering = new ArrayList<ArrayList<Variable>>(dim * dim + 1);
			for (int i = 0; i < dim * dim + 1; i++)
			{
				ArrayList<Variable> inner = new ArrayList<Variable>();
				ordering.add(inner);
			}
			for (int i = 0; i < dim * dim; i++) for (int j = 0; j < dim *  dim; j++) if (board[i][j].val == 0)
			{
				ordering.get(board[i][j].domain().size()).add(board[i][j]);
			}
			System.out.println(ordering.toString());
		if (type == 1) {initialRestrict();
		board = simpleInference();
		Stopwatch time = new Stopwatch();
		newSolve();
		if (!isSolution()) System.out.println("NOT A SOLUTION !!!");
		else System.out.println("OK SOLUTION !!!");}
		else 
		{
			initialRestrict();
			//board = simpleInference();
			newNewSolve();
		}}
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
		if (full())
		{
			System.out.println("SOLVED");
			System.out.println("assignments: " + assignments + " " + depth);
			//fancyPrint();
			return true;
		}
		// Method 4
		Variable curr = null;
		for (int i = 0; i < dim * dim + 1; i++)
		{
			if (!ordering.get(i).isEmpty())
			{
				curr = ordering.get(i).get(0);
				ordering.get(i).remove(0);
				break;
			}
		}
		//System.out.println("variable chosen: " + curr);
		/*int best = dim * dim + 1;
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
		Variable curr = board[h][k];*/
		for (int n : curr.domain())
		{
			curr.val = n;
			
			//restrict
			Collection<Variable> neighbours = freeNeighbours(curr);
			for (Variable v : neighbours) 
			{
				//System.out.println("variable begin moved: " + v);
				ordering.get(v.domain().size()).remove(v);
				v.restrict(n);
				ordering.get(v.domain().size()).add(v);
			}
			if(newNewSolve()) return true;
			// unrestrict
			for (Variable v : neighbours) 
			{
				ordering.get(v.domain().size()).remove(v);
				v.allow(n);
				ordering.get(v.domain().size()).add(v);
			}
		}
		// unput
		curr.val = 0;
		ordering.get(curr.domain().size()).add(curr);
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
		in.next();
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
}



