package com.patterns;

public class PatternMatching{

	public static void main(String[] args)
	{
		System.out.println(match("Facebooooooook", "F.cebo*k"));
	}
	
	private static boolean match(String string, String pattern)
	{
		int k1 = 0, k2 = 0;
		while (k1 < string.length() && k2 < pattern.length())
		{
			if (pattern.charAt(k2) == '.')
			{
				k1++;
				k2++;
			}
			else if (pattern.charAt(k2) == '*')
			{
				if (k2 == 0)
				{
					return false;
				}
				else
				{
					char tempChar = string.charAt(k1-1);
					if (string.charAt(k1) != tempChar)
					{
						return false;
					}
					while (string.charAt(k1) == tempChar)
					{
						k1++;
					}
					k2++;
				}
			}
			else
			{
				if (pattern.charAt(k2) == string.charAt(k1))
				{
					k1++;
					k2++;
				}
				else
				{
					return false;
				}
			}
		}
		if (k1 == string.length() && k2 == pattern.length())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
