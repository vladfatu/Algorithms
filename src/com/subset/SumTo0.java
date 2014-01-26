package com.subset;

import java.util.HashSet;
import java.util.Set;

public class SumTo0{

    public static void main(String[] args){
    
        Set<Integer> set = new HashSet<Integer>();
        set.add(-1);
        set.add(-7);
        set.add(2);
        set.add(4);
        set.add(5);
        
        Integer[] x = new Integer[set.size()];
        set.toArray(x);
        
        for (int i=0; i< set.size(); i++)        
        {
            for (int j =0;j < set.size(); j++)
            {
            	if (i != j)
            	{
	                int temp = -(x[i]+x[j]);
					if (temp != x[i] && temp != x[j] && set.contains(temp))
	                {
	                    System.out.println(true);
	                    return;
	                }
            	}
            }
        }
        
    }
    
}
