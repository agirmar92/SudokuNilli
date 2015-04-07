package Sudoku;
//import Sudoku.Board.DistinctSet;
import java.util.*;

public class VarBoardA
{
	Variable[][] board;
	int depthLimit = 10;
	int dim;	
	int counter = 0;
	PriorityQueue<Variable> queue;
	ArrayList<Variable> vars;
	Scanner in = new Scanner(System.in);
	int index = 0;
	int depth = 0;
	int numbers[];
	Stack<Variable> stack = new Stack<Variable>();
	NumberVar numberVariables;
	
	public VarBoardA(int[][] boardToSolve, int d)
	{
		dim = d;
		board = new Variable[dim*dim][dim*dim];
		numbers = new int[dim * dim];
		numberVariables = new NumberVar(boardToSolve, dim);
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
					numbers[board[i][j].val - 1]++;
				}
			}
		}
	}
	
	public VarBoardA(int d)
	{
		dim = d;
		board = new Variable[dim*dim][dim*dim];
		numbers = new int[dim * dim];
		for (int i = 0; i < dim*dim; i++) {
			for (int j = 0; j < dim*dim; j++) {
				board[i][j] = new Variable(dim, 0, i, j);
			}
		}
	}
	public boolean full()
	{
		for (int i = 0; i < dim*dim; i++)
		{
			for (int j = 0; j < dim*dim; j++) if (board[i][j].val == 0) return false;
		}
		return true;
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
		/*queue = null;
		queue = new PriorityQueue<Variable>(dim*dim*dim*dim, new Comparator<Variable>() {*/
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
	public boolean solve()
	{
		depth++;
		counter++;
		//if (counter % 1000 == 0) numberVariables.print();
		if (full())
		{
			//fancyPrint();
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
		
		for (int n : curr.domain())
		{
			numbers[n - 1]++;
			curr.val = n;
			numberVariables.board[n - 1][curr.x][curr.y]--;
			stack.push(curr);
			//restrict
			Collection<Variable> neighbours = freeNeighbours(curr);
			for (Variable v : neighbours) v.restrict(n);
			if(solve()) return true;
			// unrestrict
			for (Variable v : neighbours) v.allow(n);
			numbers[n - 1]--;
			numberVariables.board[n - 1][curr.x][curr.y]++;
			stack.pop();
		}
		// unput
		curr.val = 0;
		//assignments--;
		depth--;
		return false;
	}
	
	public boolean newSolve()
	{
		depth++;
		counter++;

		/*fancyPrint();
		System.out.println(stack);
		in.next();*/
		
		if (depth == depthLimit)
		{
			//System.out.println("reset");
			int[][] temp = new int[dim * dim][dim * dim];
			for (int i = 0; i < dim * dim; i++) for (int j = 0; j < dim * dim; j++) temp[i][j] = board[i][j].val;
			VarBoardA newBoard = new VarBoardA(temp, dim);
			newBoard.initialRestrict(false);
			newBoard.board = simpleInference();
			if (newBoard.newSolve()) {board = newBoard.board; counter += newBoard.counter; return true;}
			return false;
		}
		
		if (full())
		{
			//fancyPrint();
			//numberVariables.print();
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
		
		// Value heuristic:
		/*PriorityQueue<Integer> queue = new PriorityQueue<Integer>(16, new Comparator<Integer>() {
	        public int compare(Integer i, Integer j) {
	            //System.out.println(i + " " + j);
	        	//if (numbers[i - 1] > numbers[j - 1]) return 1;
	        	//if (numbers[i - 1] < numbers[j - 1]) return -1;
	        	if (value_heuristic(curr, i) > value_heuristic(curr, j)) return 1;
	        	if (value_heuristic(curr, i) < value_heuristic(curr, j)) return -1;
	        	//if (numberVariables.domain(i).size() > numberVariables.domain(j).size()) return 1;
	        	//if (numberVariables.domain(i).size() < numberVariables.domain(j).size()) return -1;
	        	//System.out.println(numberVariables.min(i, curr) + " " + numberVariables.min(j, curr));
	        	//if (numberVariables.min(i, curr) > numberVariables.min(j, curr)) return 1;
	        	//if (numberVariables.min(i, curr) < numberVariables.min(j, curr)) return -1;
	        	return 0;
	        }
	    });*/
		for (int n : curr.domain()) {/*queue.add(n);}
		while(!queue.isEmpty())
		{*/
			//int n = queue.remove();
			numbers[n - 1]++;
			curr.val = n;
			numberVariables.assign(n, curr.x, curr.y);
			stack.push(curr);
			//restrict
			Collection<Variable> neighbours = freeNeighbours(curr);
			for (Variable v : neighbours) v.restrict(n);
			if(newSolve()) return true;
			// unrestrict
			for (Variable v : neighbours) v.allow(n);
			numbers[n - 1]--;
			numberVariables.deassign(n, curr.x, curr.y);
			stack.pop();
		}
		// unput
		curr.val = 0;
		//assignments--;
		depth--;
		return false;
	}
	
	public int value_heuristic(Variable curr, int val)
	{
		// look at curr's free neighbours, count how often val is an option
		Collection<Variable> n = freeNeighbours(curr);
		int counter = 0;
		for (Variable var : n) if (var.isOK(val)) counter++;
		return counter;
	}
	
	public boolean findSolution(int type)
	{
		if (type == 1) {
			initialRestrict(true);
			board = simpleInference();
			solve();
			if (!isSolution()) {System.out.println("NOT A SOLUTION !!!"); return false;}
			else {System.out.println("OK SOLUTION !!!"); return true;}
			}
		else 
		{
			initialRestrict(true);
			board = simpleInference();
			newSolve();
			if (!isSolution()) {System.out.println("NOT A SOLUTION !!!"); return false;}
			else {/*System.out.println("OK SOLUTION !!!");*/ return true;}
			
			/*for (int i = 0; i < dim * dim; i++) for (int j = 0; j < dim * dim; j++) for (int k = 0; k < dim * dim; k++)
			{
				int val = numberVariables.board[i][j][k];
				if (val > 0)
				{
					// should be in solution
					if (board[j][k].val != i + 1) 
					{
						System.out.println("not OK 1");
						System.out.println((i+1) + " " + j + " " + k);
						fancyPrint();
						for (int r = 0; r < dim * dim; r++)
						{
							for (int s = 0; s < dim * dim; s++)
							{
								System.out.print(numberVariables.board[i][r][s] + " ");
							}
							System.out.println();
						}
					}
				}
				else
				{
					if (board[j][k].val == i + 1) 
					{
						System.out.println("not OK 2");
						fancyPrint();
						System.out.println(board[j][k].toString() + " " + (i+1));
						for (int r = 0; r < dim * dim; r++)
						{
							for (int s = 0; s < dim * dim; s++)
							{
								System.out.print(numberVariables.board[i][r][s] + " ");
							}
							System.out.println();
						}
					}
				}
			}*/
		}
	}
	
	private Variable[][] simpleInference()
	{
		VarBoardA nboard = new VarBoardA(dim);
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
						numberVariables.assign(value, var.x, var.y);
						numbers[value - 1]++;
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
	
	/*public boolean newNewSolve()
	{
		//fancyPrint();
		depth++;
		counter++;
		if (full())
		{
			System.out.println("SOLVED");
			//System.out.println("assignments: " + assignments + " " + depth);
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
		Variable curr = board[h][k];
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
	}*/
	
	
	
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
	
	static class NumberVar
	{
		public int[][][] board;
		int dim;
		
		public NumberVar(int[][] start, int dim)
		{
			this.dim = dim;
			board = new int[dim * dim][dim * dim][dim * dim];
			for (int i = 0; i < start.length; i++)
			{
				for (int j = 0; j < start.length; j++)
				{
					if (start[i][j] > 0)
					{
						// this number is there and nowhere else in the neighbourhood
						int number = start[i][j];
						board[number - 1][i][j] = 100;
						for (int h = 0; h < start.length; h++)
						{
							if (h != j) board[number - 1][i][h] = -100;
							if (h != i) board[number - 1][h][j] = -100;
						} 
						int x = i / dim;
						int y = j / dim;
						x *= dim;
						y *= dim;
						for (int h = 0; h < dim; h++)
						{
							for (int k = 0; k < dim; k++)
							{
								if (x + h != i && y + k != j)
								{
									board[number - 1][x + h][y + k] = -100;
								}
							}
						}
							
					}
				}
			}
		}
		
		public void assign(int val, int i, int j)
		{
			int counter = 0;
			int number = val;
			board[number - 1][i][j] = 100;
			for (int h = 0; h < board.length; h++)
			{
				if (h != j) {board[number - 1][i][h]--; counter++;}
				if (h != i) {board[number - 1][h][j]--; counter++;}
			} 
			//System.out.println("should be 30 " + counter);
			for (int h = 0; h < dim; h++)
			{
				for (int k = 0; k < dim; k++)
				{
					int x = i / dim;
					int y = j / dim;
					x *= dim;
					y *= dim;
					//System.out.println("dim is " + dim);
					//System.out.println("coords: " + i + " " + j + " now: " + (x + h) + " " + (y+k));
					if (x + h != i && y + k != j)
					{
						//System.out.println("OK");
						board[number - 1][x + h][y + k]--;
						counter++;
					}
				}
				//System.out.println(counter);
			}
			//System.out.println(counter);
		}
		public void deassign(int val, int i, int j)
		{
			int counter = 0;
			int number = val;
			board[number - 1][i][j] = 0;
			for (int h = 0; h < board.length; h++)
			{
				if (h != j) {board[number - 1][i][h]++; counter++;}
				if (h != i) {board[number - 1][h][j]++; counter++;}
			} 
			//System.out.println("should be 30 " + counter);
			//System.out.println(dim);
			for (int h = 0; h < dim; h++)
			{
				for (int k = 0; k < dim; k++)
				{
					int x = i / dim;
					int y = j / dim;
					x *= dim;
					y *= dim;
					//System.out.println("coords: " + i + " " + j + " now: " + (x+h) + " " + (y+k));
					if (x + h != i && y + k != j)
					{
						board[number - 1][x + h][y + k]++;
						counter++;
					}
				}
			}
			//System.out.println(counter);
		}
		
		public void print()
		{
			for (int i = 0; i < board.length; i++)
			{
				System.out.println("value: " + (i+1));
				for (int j = 0; j < board.length; j++)
				{
					for (int k = 0; k < board.length; k++)
					{
						System.out.print(board[i][j][k] + " ");
					}
					System.out.println();
				}
			}
		}
		
		public Collection<Pair> domain(int val)
		{
			Collection<Pair> domain = new ArrayList<Pair>();
			for (int i = 0; i < dim * dim; i++)
			{
				for (int j = 0; j < dim * dim; j++)
				{
					if(board[val - 1][i][j] == 0) domain.add(new Pair(i, j));
				}
			}
			return domain;
		}
		
		public Collection<Pair> domain(int val, int type, int index)
		{
			Collection<Pair> domain = domain(val);
			Collection<Pair> result = new ArrayList<Pair>();
			// type 0 : row
			if (type == 0) for (Pair p : domain) if (p.x == index) result.add(p);
			// type 1 : column
			else if (type == 1) for (Pair q : domain) if (q.y == index) result.add(q);
			// type 2 : box
			else for (Pair r : domain) if (r.x / dim == index / dim && r.y % dim == index % dim) result.add(r);
			return result;
			
		}
		
		public int min(int val, Variable var)
		{
			int[][] values = board[val - 1];
			/*for (int i = 0; i < dim * dim; i++) 
			{
				for (int j = 0; j < dim * dim; j++) System.out.print(values[i][j] + " ");
				System.out.println();
			}*/
			int min = 16;
			//for (int i = 0; i < dim * dim; i++) 
			//{
				int count = 0;
				for (int j = 0; j < dim *  dim; j++)
				{
					if (values[var.x][j] == 0) count++;
				}
				if (count < min) min = count;
			//}
			//System.out.println(min);
			//for (int i = 0; i < dim * dim; i++) 
			//{
				count = 0;
				for (int j = 0; j < dim *  dim; j++)
				{
					if (values[j][var.y] == 0) count++;
				}
				if (count < min) min = count;
			//}
			//System.out.println(min);
			//for (int i = 0; i < dim * dim; i++)
			//{
				count = 0;
				int x = var.x / dim;
				int y = var.y / dim;
				x *= dim;
				y *= dim;
				for (int j = 0; j < dim * dim; j++)
				{
					if (values[x + j / dim][y + j % dim] == 0) count++;
				}
				if (count < min) min = count;
			//}
			//System.out.println(min);
			//Scanner in2 = new Scanner(System.in);
			//System.out.println("wating ...");
			//in2.next();
			return min;
		}
	}
	
	static public class Pair
	{
		int x;
		int y;
		public Pair(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}



