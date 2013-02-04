package com.topcoder.srm424;

public class BestRoads
{
	public int[] numberOfRoads(String[] roads, int M)
	{
		int n = roads.length;
		int[][] a = new int[n][n];
		int[] result = new int[n];
		for (int i=0; i<n; i++)
		{
			for (int j=i+1; j<n; j++)
			{
				if (roads[i].charAt(j) == 'Y')
				{
					if (M > 0)
					{
						M--;
						a[i][j] = 1;
						a[j][i] = 1;
					}
				}
			}
		}
		boolean ok;
		for (int i=0; i<n;i++)
		{
			ok = false;
			for (int j=0; j<n; j++)
			{
				if (a[i][j] == 1)
				{
					result[i] +=1;
					ok = true;
				}
			}
			if (!ok)
			{
				return new int[0];
			}
		}
		if (M == 0)
		{
			return result;
		}
		else
		{
			return new int[0];
		}
	}
}
