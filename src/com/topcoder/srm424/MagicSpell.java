package com.topcoder.srm424;

public class MagicSpell
{
	public String fixTheSpell(String spell)
	{
		String temp = "";
		StringBuilder spellBuilder = new StringBuilder(spell);
		for (int i=0; i < spellBuilder.length(); i++)
		{
			if (spellBuilder.charAt(i) == 'A' || spell.charAt(i) == 'Z')
			{
				temp +=spell.charAt(i);
				spellBuilder.setCharAt(i, '-');
			}
		}
		int j = temp.length();
		for (int i=0; i < spellBuilder.length(); i++)
		{
			if (spellBuilder.charAt(i) == '-')
			{
				spellBuilder.setCharAt(i, temp.charAt(j));
				j--;
			}
		}
		return spellBuilder.toString();
	}
}
