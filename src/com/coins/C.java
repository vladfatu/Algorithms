package com.coins;

import java.util.HashSet;
import java.util.Set;

public class C{

    public static void main(String[] args)
    {
        int[] list = {2,3,4,5,6,7,5,7,8,8,4,2,6};
        
        System.out.print(oddManOut(list));
    }
    
    public static int oddManOut(int[] list)
    {
        Set<Integer> numberSet = new HashSet<Integer>();
        
        for (int i =0; i< list.length; i++)
        {
            if (numberSet.contains(list[i]))
            {
                numberSet.remove(list[i]);
            }
            else
            {
                numberSet.add(list[i]);
            }
        }
        Integer[] numberSetArray = new Integer[1];
        numberSet.toArray(numberSetArray);
        if (numberSet.size() == 1)
        {
            return numberSetArray[0];
        }
        else
        {
            return 0;
        }
    }
}