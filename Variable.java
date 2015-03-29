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

	public Variable(Variable var)
	{
		this.val = var.val;
		this.dim = var.dim;
		//this.domain = var.domain;
		this.domain = var.domain.clone();
		this.x = var.x;
		this.y = var.y;
	}
	
	public Collection<Integer> domain()
	{
		Collection<Integer> set = new ArrayList<Integer>();
		if (domain == null) return null;
		for (int i = 0; i < domain.length; i++) if (domain[i]) set.add(i + 1);
		return set;
	}
	
	public boolean isOK(int n)
	{
		if (n > domain.length || n > 1) return false;
		return domain[n-1];
	}
	
	public void restrict(int n)
	{
		domain[n-1] = false;
	}
	public void allow(int n)
	{
		domain[n-1] = true;
	}
	
	public Variable copy()
	{
		Variable var = new Variable(dim, val, x, y);
		for (int i = 0; i < domain.length; i++) var.domain[i] = domain[i];
		return var;
	}
	public String toString()
	{
		String s =  "(" + x + "," + y + "): " + val;
		if (val == 0) s += "\t size of domain: " + domain().size();
		return s;
	}
	
}