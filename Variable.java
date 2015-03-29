package Sudoku;

import java.util.*;

public class Variable
{
	int val;
	int dim;
	boolean[] domain;
	int x, y;
	
	public Variable(int dim, int val, int x, int y)
	{
		this.dim = dim;
		this.val = val;
		if (val == 0) 
		{
			domain = new boolean[dim*dim];
			for (int i = 0; i < domain.length; i++) domain[i] = true;
		}
		else domain = null;
		this.x = x;
		this.y = y;
	}
	
	public Collection<Integer> domain()
	{
		Collection<Integer> set = new ArrayList<Integer>();
		if (domain == null) return null;
		for (int i = 0; i < domain.length; i++) if (domain[i]) set.add(i + 1);
		return set;
	}
	
	public void restrict(int n)
	{
		domain[n] = false;
	}
	public void allow(int n)
	{
		domain[n] = true;
	}
	
	public Variable copy()
	{
		Variable var = new Variable(dim, val, x, y);
		for (int i = 0; i < domain.length; i++) var.domain[i] = domain[i];
		return var;
	}
	
}