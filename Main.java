package Sudoku;

public class Main
{
	static public void main(String[] args)
	{
		Board b = new Board();
		//b.print();
		b.fancyPrint();
		/*System.out.println(b.put(0, 0, 3));
		b.fancyPrint();
		System.out.println(b.put(1, 1, 5));
		b.fancyPrint();
		System.out.println(b.put(2, 0, 2));
		b.fancyPrint();
		System.out.println(b.put(0, 3, 9));
		b.fancyPrint();*/
		b.put(0, 0, 1);
		b.put(0, 1, 2);
		b.put(0, 2, 3);
		b.put(0, 3, 4);
		//b.put(0, 4, 5);
		//b.put(0, 5, 6);
		//b.put(0, 6, 7);
		//b.put(0, 7, 8);
		//b.put(0, 8, 9);
		
		b.solve();
		b.fancyPrint();
		
	}
}