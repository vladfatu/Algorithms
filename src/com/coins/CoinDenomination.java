package com.coins;

public class CoinDenomination{

	public static void main(String[] args){

		int value = 100;
		int k = 1;
		int[] coinTypes = new int[7];
		coinTypes[0] = 6;
		coinTypes[1] = 1;
		coinTypes[2] = 5;
		coinTypes[3] = 10;
		coinTypes[4] = 25;
		coinTypes[5] = 50;
		coinTypes[6] = 100;

		System.out.println(loop(coinTypes, value, k));


	}

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