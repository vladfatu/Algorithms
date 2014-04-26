package com.test;

public class Solution {

	public int solution(int[] A)
	{
		long sum = 0;
		for (int i = 0; i < A.length; i++)
		{
			sum+=A[i];
		}
		long sumLeft = 0;
		long sumRight = 0;
		for (int i=0;i<A.length;i++)
		{
			sumRight = sum - sumLeft - A[i];
			if (sumLeft == sumRight)
			{
				return i;
			}
			sumLeft += A[i];
		}
		
		return -1;
	}

}