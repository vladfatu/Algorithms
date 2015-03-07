package com.coins;

import java.util.Arrays;

public class CoinDenomination{

	public static void main(String[] args){

		int value = 110;
		int k = 1;
		int[] coinTypes = new int[7];
		coinTypes[0] = 1;
		coinTypes[1] = 2;
		coinTypes[2] = 5;
		coinTypes[3] = 10;
		coinTypes[4] = 25;
		coinTypes[5] = 50;
		coinTypes[6] = 100;

		System.out.println(loop(coinTypes, value, k));


		int values[] = {10,20,50,90};
		int n = values.length;
		int target = 130;
		int min = minCoins(values,target);
		System.out.println(min);
	}

	private static int minCoins(int values[], int target) {
		int intermediateCredit = target;
		int count = 0;
		Arrays.sort(values);
		int length = values.length;
		for (int i = length -1;i>=0;i--)
		{
			count += intermediateCredit/values[i];
			intermediateCredit = intermediateCredit % values[i];
		}
		return count;
	}

	private static int minCoins(int values[],int n,int target) {
		// array to store the min no. of coins required to make a change
		// for each index 0...target
		int[] change = new int[target+1];
		change[0] = 0;
		int i,j;
		for(j=1;j<=target;j++) {
			int min = 100;
			// as per the recursive solution for all i = {0...n-1}
			for(i=0;i<n;i++) {
				if(j>=values[i] && (change[j-values[i]]+1) < min)
					min = change[j-values[i]]+1;
			}
			// store the minimum no. of coins required to make a change for target = j
			change[j] = min;
		}
		return change[target];
	}

//	private int minCoins2(int values[], int target) {
//		int[] change = new int[target+1];
//		int n = values.length;
//		change[0] = 0;
//		int i,j;
//		for(j=1;j<=target;j++) {
//			int min = target+1;
//			for(i=0;i<n;i++) {
//				if(j>=values[i] && (change[j-values[i]]+1) < min)
//					min = change[j-values[i]]+1;
//			}
//			change[j] = min;
//		}
////		System.out.println("The minimum number of coins for : " + target + " is : " + change[target]);
//		return change[target];
//	}

	private static int loop(int[] coinTypes, int value, int k){
		
		if (value < 0 || k == 7)
		{
			return 0;
		}
		if (value == 0)
		{
			return 1;
		}
		else
		{
			return loop(coinTypes, value - coinTypes[k], k) + loop(coinTypes, value, k+1);		
		}
			

	}

}