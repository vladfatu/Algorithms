package com.palindrome;

public class Palindrome{

	public static void main(String[] args)
	{
		int x = 503305;
		System.out.println(isPalindrome(x));
		
		int palindrome = toPalindrome(x);
		System.out.println(palindrome);
		
		System.out.println(isPalindrome(palindrome));
	}
	
	private static boolean isPalindrome(int x)
	{
		int k = 1;
		
		while (x/k > 10)
		{
			k*= 10;
		}
		
		return isPalindrome(x, k);
	}
	
	private static boolean isPalindrome(int x, int k)
	{
		if (k <= 1)
		{
			return true;
		}
		else
		{
			if (x/k == x%10)
			{
				if (k == 10)
				{
					return true;
				}
				else
				{
					int temp = (x%k)/10;
					
					return isPalindrome(temp, k/100);
				}
			}
			else
			{
				return false;
			}
		}
	}
	
	private static int toPalindrome(int x)
	{
		int[] digits = new int[10];
		int temp = x;
		while (temp > 0)
		{
			digits[temp%10]++;
			temp /= 10;
		}
		
		int unevenDigit = -1;
		
		for (int i=0; i< 10; i++)
		{
			if (digits[i]%2 != 0)
			{
				if (unevenDigit != -1)
				{
					return -1;
				}
				else
				{
					unevenDigit= i;
					digits[i]--;
				}
			}
		}
		
		int palindrome = 0;
		
		for (int i=1; i< 10; i++)
		{
			for (int j=0; j< digits[i]/2; j++)
			{
				palindrome *= 10;
				palindrome += i;
				digits[i]--;
			}
		}
		
		for (int j=0; j< digits[0]/2; j++)
		{
			palindrome *= 10;
			palindrome += 0;
			digits[0]--;
		}
			
		if (unevenDigit != -1)
		{
			palindrome *= 10;
			palindrome += unevenDigit;
		}
		
		for (int j=0; j< digits[0]; j++)
		{
			palindrome *= 10;
			palindrome += 0;
			digits[j]--;
		}
		
		for (int i=9; i>0; i--)
		{
			for (int j=0; j< digits[i]; j++)
			{
				palindrome *= 10;
				palindrome += i;
				digits[i]--;
			}
		}
		
		return palindrome;
	}

}
