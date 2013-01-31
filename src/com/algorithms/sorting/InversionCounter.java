package com.algorithms.sorting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class InversionCounter { //2407905288
	
	public static final void main(String[] args)
	{
		Integer[] test = new Integer[100000];
		InversionCounter inversionCount = new InversionCounter();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("inversionsTest.txt"));
			String line;
			line = br.readLine();
			int i = 0;
			while (line != null)
			{
				test[i] = Integer.parseInt(line);
			    line = br.readLine();
				i++;
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Utils.printIntArray(test);
		
		System.out.println(inversionCount.count(test).inversions);
	}

	public ArrayWithInversions count(Integer[] unsortedList)
	{
		if (unsortedList.length > 2)
		{
			Integer[] leftList = Arrays.copyOfRange(unsortedList, 0, unsortedList.length/2);
			Integer[] rightList = Arrays.copyOfRange(unsortedList, unsortedList.length/2, unsortedList.length);
			
			ArrayWithInversions leftArray = count(leftList);
			ArrayWithInversions rightArray = count(rightList);
			
			ArrayWithInversions newArray = countSplitInversions(leftArray.array, rightArray.array);
			newArray.inversions += leftArray.inversions + rightArray.inversions;
			
			return newArray;
		}
		else
		{
			if (unsortedList.length == 2)
			{
				if (unsortedList[0] > unsortedList[1])
				{
					int x = unsortedList[0];
					unsortedList[0] = unsortedList[1];
					unsortedList[1] = x;
					return new ArrayWithInversions(unsortedList, 1);
				}
				return new ArrayWithInversions(unsortedList, 0);
			}
			else
			{
				return new ArrayWithInversions(unsortedList, 0);
			}
		}
	}
	
	private ArrayWithInversions countSplitInversions(Integer[] leftList, Integer[] rightList)
	{
		long inversions = 0;
		Integer[] newList = new Integer[leftList.length + rightList.length];
		for (int i=0, l=0, r=0; i<newList.length; i++)
		{
			if (l < leftList.length && (r >= rightList.length || leftList[l] < rightList[r]))
			{
				newList[i] = leftList[l];
				l++;
			}
			else
			{
				if (l<leftList.length && r<rightList.length && leftList[l] > rightList[r])
				{
					inversions += leftList.length - l;
				}
				newList[i] = rightList[r];
				r++;
			}
		}
		return new ArrayWithInversions(newList, inversions);
	}
	
private class ArrayWithInversions{
	
	private Integer[] array;
	private long inversions;
	
	ArrayWithInversions(Integer[] array, long inversions)
	{
		this.array = array;
		this.inversions = inversions;
	}
	
}

}
