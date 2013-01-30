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
		if (unsortedList.length > 2)
		{
			Integer[] leftList = Arrays.copyOfRange(unsortedList, 0, unsortedList.length/2);
			Integer[] rightList = Arrays.copyOfRange(unsortedList, unsortedList.length/2, unsortedList.length);
			
			leftList = sort(leftList);
			rightList = sort(rightList);
			
			return merge(leftList, rightList);
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
				}
				return unsortedList;
			}
			else
			{
				return unsortedList;
			}
		}
	}
	
	private Integer[] merge(Integer[] leftList, Integer[] rightList)
	{
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
				newList[i] = rightList[r];
				r++;
			}
		}
		return newList;
	}

}
