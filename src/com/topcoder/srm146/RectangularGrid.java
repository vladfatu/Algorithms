package com.topcoder.srm146;

public class RectangularGrid {
	
	public static void main(String[] args)
	{
		System.out.println(countRectangles(592, 964));
	}
	
	public static long countRectangles(int width, int height)
	{
		long count = 0;
		if (height < width)
		{
			int aux = height;
			height = width;
			width = aux;
		}
		for (int a = 1; a <= width; a++)
		{
			for (int b = a + 1; b <= height;b++)
			{
				int x = (height - a + 1) * (width - b + 1);
				int y = (width - a + 1) * (height - b + 1);
				if (x>0)
				{
					count += x;
				}
				if (y>0)
				{
					count += y;
				}
			}
		}
		return count;
	}

}
