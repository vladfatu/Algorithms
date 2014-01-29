package com.algorithms.sorting;

import java.util.Arrays;

import com.algorithms.utils.Utils;


public class MergeSort implements Sort<Integer>{
	
	public static final void main(String[] args)
	{
		Integer[] test = {1, 5, 7, 3, 4, 2, 1, 10, 11};
		MergeSort mergeSort = new MergeSort();
		Utils.printIntArray(mergeSort.sort(test));
	}

	@Override
	public Integer[] sort(Integer[] unsortedList)
	{
		if (unsortedList.length <= 1)
		{
			return unsortedList;
		}
		else if (unsortedList.length == 2)
		{
			if (unsortedList[0] > unsortedList[1])
			{
				int x = unsortedList[0];
				unsortedList[0] = unsortedList[1];
				unsortedList[1] = x;
			}
			return unsortedList;
		}
		else
		{
			Integer[] leftList = Arrays.copyOfRange(unsortedList, 0, unsortedList.length/2);
			Integer[] rightList = Arrays.copyOfRange(unsortedList, unsortedList.length/2, unsortedList.length);
			
			return merge(sort(leftList), sort(rightList));
		}
	}
	
	private Integer[] merge(Integer[] leftList, Integer[] rightList)
	{
		int i=0;
		int j=0;
		Integer[] mergedList = new Integer[leftList.length + rightList.length];
		for (int k=0; k < leftList.length + rightList.length; k++)
		{
			if (i < leftList.length && (j >= rightList.length || leftList[i] < rightList[j]))
			{
				mergedList[k] = leftList[i];
				i++;
			}
			else
			{	
				mergedList[k] = rightList[j];
				j++;
			}
		}
		return mergedList;
	}

}
