package com.topcoder.srm238;

public class PrintScheduler {
	
	public static final void main(String[] args)
	{
		String[] threads = {"AB","CD"};
		
		String[] slices = {"0 1","1 1","0 1","1 2"} ;
		
		System.out.println(getOutput(threads, slices));
	}
	
	public static String getOutput(String[] threads, String[] slices)
	{
		int[] temp = new int[threads.length];
		String finalString = "";
		for (int i=0;i<slices.length;i++)
		{
			int threadNumber = Integer.parseInt(slices[i].split(" ")[0]);
			int threadValue = Integer.parseInt(slices[i].split(" ")[1]);
			
			if (temp[threadNumber] + threadValue < threads[threadNumber].length())
			{
				finalString += threads[threadNumber].substring(temp[threadNumber], temp[threadNumber] + threadValue);
			}
			else
			{
				finalString += threads[threadNumber].substring(temp[threadNumber]);
				finalString += threads[threadNumber].substring(0, -threads[threadNumber].length() + temp[threadNumber] + threadValue);
			}
			
			temp[threadNumber] += threadValue;
			if (temp[threadNumber] >= threads[threadNumber].length())
			{
				temp[threadNumber] = temp[threadNumber] % threads[threadNumber].length();
			}
					
		}
		return finalString;
	}

}
