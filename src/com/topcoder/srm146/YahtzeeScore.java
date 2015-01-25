package com.topcoder.srm146;

public class YahtzeeScore {
	
	public static void main(String[] args)
	{
		int[] toss = { 5, 3, 5, 3, 3 };
		System.out.println(maxPoints(toss));
	}

	public static int maxPoints(int[] toss)
	{
		int[] values = new int[7];
		for (int i=0;i<toss.length;i++)
		{
			values[toss[i]] += toss[i];
		}
		
		int max = 0;
		
		for (int i=0;i<7;i++)
		{
			if (values[i] > max)
			{
				max = values[i];
			}
		}
		return max;
	}
	
}
