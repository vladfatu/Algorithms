package com.topcoder.srm518;

public class LargestSubsequence {

	public String getLargest(String s)
	{
		String temp = "" + s.charAt(s.length() - 1);
		for (int i=s.length()-2; i>=0; i--)
		{
			if (s.charAt(i) >= temp.charAt(0))
			{
				temp = s.charAt(i) + temp;
			}
		}
		return temp;
	}
	
}
