package com.topcoder.tco14r1c;

public class Ex3 {
	
	public static void main(String[] args)
	{
		
	}
	
	public static void print(String[] x)
	{
		for (int i=0;i<x.length;i++)
		{
			System.out.print(x[i] + " ");
		}
	}
	
	public static void print(int[] x)
	{
		for (int i=0;i<x.length;i++)
		{
			System.out.print(x[i] + " ");
		}
	}
	
	public static void print(int[][] x)
	{
		for (int i=0;i<x.length;i++)
		{
			for (int j=0;j<x.length;j++)
			{
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
	}

}
