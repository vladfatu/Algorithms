package com.anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Anagrams{

	public static void main(String[] args){
	
		List<String> words = new ArrayList<String>();
		words.add("car");
		words.add("amcr");
		words.add("arc");
		words.add("aclr");
		words.add("carl");
		words.add("lacr");
		words.add("cae");
		words.add("acri");
		words.add("caro");
		words.add("amcr");
		anagrams(words);	

	}

	private static void anagrams(List<String> anagrams){
	
		Map<String, List<String>> anagramMap = new HashMap<String, List<String>>();
		for (String anagram : anagrams)
		{
			
			String sortedAnagram = sortString(anagram);
			List<String> anagramList = anagramMap.get(sortedAnagram);
			if(anagramList == null)
			{

				anagramList = new ArrayList<String>();
				anagramMap.put(sortedAnagram, anagramList);

			}		
			anagramList.add(anagram);

		}	

		print(anagramMap);

	}

	private static String sortString(String anagram){

		char[] anagramArray = anagram.toCharArray();
		Arrays.sort(anagramArray);			
		return new String(anagramArray);
	}

	private static void print(Map<String, List<String>> anagramsMap){
	
		for (List<String> anagramsList : anagramsMap.values())
		{
			System.out.println(anagramsList.toString());
		}

	}

}