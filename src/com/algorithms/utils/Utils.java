package com.algorithms.utils;

public class Utils {

	public static void printIntArray(Integer[] list)
	{
		for (int i=0;i<list.length;i++)
		{
			System.out.print(list[i] + " ");
		}
	}

    public static void printIntMatrix(Integer[][] list)
    {
        for (int i=0;i<list.length;i++)
        {
            for (int j=0;j<list.length;j++)
            {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printIntArray(int[] list)
    {
        for (int i=0;i<list.length;i++)
        {
            System.out.print(list[i] + " ");
        }
    }

    public static void printIntMatrix(int[][] list)
    {
        for (int i=0;i<list.length;i++)
        {
            for (int j=0;j<list.length;j++)
            {
                System.out.print(list[i][j] + " ");
                if (list[i][j] < 10)
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
	
}
