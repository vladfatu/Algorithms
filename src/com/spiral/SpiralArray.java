package com.spiral;

public class SpiralArray{

    public static void main(String[] args){
    
    	int n = 5;
       int[][] x = new int[n][];
       
       x[0] = new int[5];
       
       x[0] = new int[]{1, 2, 3, 4, 5};
       x[1] = new int[]{6, 7, 8, 9, 10};
       x[2] = new int[]{11, 12, 13, 14, 15};
       x[3] = new int[]{16, 17, 18, 19, 20};
       x[4] = new int[]{21, 22, 23, 24, 25};
       
       for (int k = 0 ;k < n/2; k++)
       {
           for (int j = k; j< n-k-1; j++)
           {
               System.out.println(x[k][j]);
           }
           
           for (int i = k; i< n-k-1; i++)
           {
               System.out.println(x[i][n-k-1]);
           }
           
           for (int j = n-k-1; j > k; j--)
           {
               System.out.println(x[n-k-1][j]);
           }
           
           for (int i = n-k-1; i> k; i--)
           {
               System.out.println(x[i][k]);
           }
       }
       
       if (n%2 == 1)
       {
    	   System.out.println(x[n/2][n/2]);
       }
    }
    
}