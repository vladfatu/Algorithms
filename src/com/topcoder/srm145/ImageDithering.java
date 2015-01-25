package com.topcoder.srm145;

public class ImageDithering {
	
	public static void main(String[] args)
	{
		System.out.println(getPercentages("00:19:16"));
	}
	
	public static int getPercentages(String time)
	{
		int totalTime = 0;
		totalTime += Integer.parseInt(time.split(":")[0]) * 3600;
		totalTime += Integer.parseInt(time.split(":")[1]) * 60;
		totalTime += Integer.parseInt(time.split(":")[2]);
		
		if (totalTime % 100 == 0)
		{
			return 99;
		}
		else if (totalTime % 50 == 0)
		{
			return 49;
		}
		else if (totalTime % 25 == 0)
		{
			return 24;
		}
		else if (totalTime % 4 == 0)
		{
			return 3;
		}
		else if (totalTime % 20 == 0)
		{
			return 19;
		}
		else if (totalTime % 10 == 0)
		{
			return 9;
		}
		else if (totalTime % 5 == 0)
		{
			return 4;
		}
		else if (totalTime % 2 == 0)
		{
			return 1;
		}
		
		return 0;
	}

}
