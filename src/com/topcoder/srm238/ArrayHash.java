package com.topcoder.srm238;

public class ArrayHash {
	
	public static final void main(String[] args)
	{
		String[] test = {"CBA", "DDD"};
		System.out.println(getHash(test));
	}
	
	public static int getHash(String[] input)
	{
		int hash=0;
		for (int i=0;i<input.length;i++)
		{
			for (int j=0;j<input[i].length();j++)
			{
				hash += i + j + input[i].charAt(j) - 65;
			}
		}
		return hash;
	}

}
