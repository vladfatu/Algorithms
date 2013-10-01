package com.topcoder.srm315;

public class DigitsSum {
	
	public int lastDigit(int n)
	{
		while (n > 9)
		{
			n = sum(n);
		}
		return n;
	}
	
	public int sum(int n)
	{
		int sum = 0;
		int d = n/10;
		while (d > 9)
		{
			sum += n % 10;
			n = d;
			d = n/10;
		}
		sum += n % 10  + d;
		return sum;
	}
	
	public static void main(String[] args)
	{
		DigitsSum sum = new DigitsSum();
		System.out.println(sum.lastDigit(12345));
	}

}
