package com.topcoder.srm315;

public class RosePetals {
	
	public int getScore(int[] dice)
	{
		int petals = 0;
		for (int i=0; i< dice.length; i++)
		{
			switch (dice[i])
			{
				case 3:
					petals += 2;
					break;
				case 5: 
					petals += 4;
					break;

				default:
					break;
			}
		}
		return petals;
	}

}
