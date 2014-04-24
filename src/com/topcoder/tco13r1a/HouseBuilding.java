package com.topcoder.tco13r1a;

public class HouseBuilding {
	
	public static void main(String[] args)
	{
		String[] area = {"5781252",
				 "2471255",
				 "0000291",
				 "1212489"};
		System.out.println(getMinimum(area));
	}
	
	public static int getMinimum(String[] area)
	{
		int sum = 0;
		for (int i=0;i<area.length;i++)
		{
			for (int j=0; j<area[i].length();j++)
			{
				sum+= area[i].charAt(j) - '0';
			}
		}
		int avg = sum / (area.length * area[0].length());
		System.out.println(avg);
		int count1 = 0;
		
		for (int i=0;i<area.length;i++)
		{
			for (int j=0; j<area[i].length();j++)
			{
				int x = area[i].charAt(j) - '0';
				if (avg > x)
				{
					count1+= avg-x;
				}
				else if (x > avg + 1)
				{
					count1+= x - avg - 1;
				}
			}
		}
		System.out.println(count1);
		
		int count2 = 0;
		
		for (int i=0;i<area.length;i++)
		{
			for (int j=0; j<area[i].length();j++)
			{
				int x = area[i].charAt(j) - '0';
				if (avg > x + 1)
				{
					count2+= avg - x - 1;
				}
				else if (x > avg)
				{
					count2+= x - avg;
				}
			}
		}
		System.out.println(count2);
		
		if (count1 < count2)
		{
			return count1;
		}
		else
		{
			return count2;
		}
	}

}
