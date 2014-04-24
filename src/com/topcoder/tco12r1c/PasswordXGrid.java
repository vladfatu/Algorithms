package com.topcoder.tco12r1c;

public class PasswordXGrid {
	
	public static void main(String[] args)
	{
		System.out.println();
		String[] h = {"8888585","5888585"};
		String[] v = {"58585858"};
		System.out.println(minSum(h, v));
	}
	
	public static int minSum(String[] horizontal, String[] vertical)
	{
		int[][] h = new int[horizontal.length][];
		for (int i=0; i<horizontal.length;i++)
		{
			h[i] = new int[horizontal[i].length()];
			for (int j=0; j<horizontal[i].length(); j++)
			{
				h[i][j] = horizontal[i].charAt(j) - '0';
			}
		}
		
		int[][] v = new int[vertical.length][];
		for (int i=0; i<vertical.length;i++)
		{
			v[i] = new int[vertical[i].length()];
			for (int j=0; j<vertical[i].length(); j++)
			{
				v[i][j] = vertical[i].charAt(j) - '0';
			}
		}
		
		
		for (int i=0;i<h.length - 1;i++)
		{
			for (int j=0; j< h[0].length;j++)
			{
				int x = h[i][j] + v[i][j+1];
				int y = h[i+1][j] + v[i][j];
				
				if (x > y)
				{
					h[i+1][j] = h[i+1][j] + x - y;
				}
				else if (x < y)
				{
					v[i][j+1] = v[i][j+1] + y - x;
				}
			}
		}
		
		int sum = 0;
		for (int i=0;i<h[h.length-1].length;i++)
		{
			sum += h[h.length-1][i];
		}
		
		for (int i=0;i<v.length;i++)
		{
			sum += v[i][0];
		}
		
		print(h);
		print(v);
		
		return sum;
	}
	
	public static void print(int[][] x)
	{
		for (int i=0; i<x.length;i++)
		{
			for (int j=0;j<x[i].length;j++)
			{
				System.out.print(x[i][j]);
			}
			System.out.print("  ");
		}
		System.out.println();
	}

}
