package com.topcoder.srm379;

public class FillBox
{
	
	public final static void main(String[] args)
	{
		FillBox fillBox = new FillBox();
		int[] cubes = {143821,14382,1438,143,14,1};
		System.out.print(fillBox.minCubes(37,  42,  59, cubes));
	}
	
	public int minCubes(int length, int width, int height, int[] cubes)
	{
		int n = cubes.length;
		int result = 0;
		for (int i=1;i<n;i++)
		{	
			if (length % powerOf2(i) != 0)
			{
				int distResult = distribuite((width * height)/powerOf2((i-1)*2), cubes, i-1);
				if (distResult == -1)
				{
					return -1;
				}
				result += distResult;
				length -= length % powerOf2(i);
			}
				if (width % powerOf2(i) != 0)
			{
				int distResult = distribuite((length * height)/powerOf2((i-1)*2), cubes, i-1);
				if (distResult == -1)
				{
					return -1;
				}
				result += distResult;
				width -= width % powerOf2(i);
			}
				if (height % powerOf2(i) != 0)
			{
				int distResult = distribuite((length * width)/powerOf2((i-1)*2), cubes, i-1);
				if (distResult == -1)
				{
					return -1;
				}
				result += distResult;
				height -= height % powerOf2(i);
			}
		}
		int distResult = distribuite((length*width*height)/powerOf2((n-1)*3), cubes, n-1);
		if (distResult == -1)
		{
			return -1;
		}
		result += distResult;
		return result;
	}
	
	public int distribuite(int nrOfCubes, int[] cubes, int powerOf2)
	{	
		int result = 0;
		for (int i=powerOf2;i>=0;i--)
		{	
			if (cubes[i]>=nrOfCubes)
			{
				cubes[i] = cubes[i] - nrOfCubes;
				result += nrOfCubes;
				return result;
			}
			else
			{
				nrOfCubes = (nrOfCubes - cubes[i]) * 8;
				result += cubes[i];
				cubes[i] = 0;
			}
		}
		return -1;
	}
	
	public int powerOf2(int n)
	{
		int result = 1;
		for (int i=0; i<n;i++)
		{	
			result *= 2;
		}
		return result;
	}
}
