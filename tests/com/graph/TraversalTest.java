package com.graph;

import org.junit.Test;
import static org.junit.Assert.*;

import com.algorithms.sorting.MergeSort;

public class TraversalTest {

	@Test
	public void mergeSortTest()
	{
		Integer[] test = {1, 5, 7, 3, 4, 2, 1, 10, 11};
		MergeSort mergeSort = new MergeSort();
		Integer[] testResult = {1, 1, 2, 3, 4, 5, 7, 10, 11};
		assertArrayEquals(testResult, mergeSort.sort(test));
	}
	
	@Test
	public void mergeSortAlreadySortedTest()
	{
		Integer[] test = {1, 1, 2, 3, 4, 5, 7, 10, 11};
		MergeSort mergeSort = new MergeSort();
		Integer[] testResult = {1, 1, 2, 3, 4, 5, 7, 10, 11};
		assertArrayEquals(testResult, mergeSort.sort(test));
	}
	
	@Test
	public void mergeSortDescSortedTest()
	{
		Integer[] test = {11, 10, 7, 5, 4, 3, 2, 1, 1};
		MergeSort mergeSort = new MergeSort();
		Integer[] testResult = {1, 1, 2, 3, 4, 5, 7, 10, 11};
		assertArrayEquals(testResult, mergeSort.sort(test));
	}

}
