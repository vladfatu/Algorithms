package com.topcoder.tco14r1c;

public class FizzBuzzTurbo {

	public static void main(String[] args)
	{
		print(counts(1, 100));
	}
	
	public static long[] counts(long A, long B)
	{
		long fizz = 0;
		long buzz = 0;
		long fizzBuzz = 0;
		fizzBuzz = B/15 - (A-1)/15;
		fizz = B/3 - (A-1)/3 - fizzBuzz;
		buzz = B/5 - (A-1)/5 - fizzBuzz;
		
		long[] result = new long[3];
		result[0] = fizz;
		result[1] = buzz;
		result[2] = fizzBuzz;
		return result;
	}
	
	public static void print(long[] x)
	{
		for (int i=0;i<x.length;i++)
		{
			System.out.print(x[i] + " ");
		}
	}
	
}
