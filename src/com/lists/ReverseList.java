package com.lists;

public class ReverseList{

    public static void main(String[] args)
    {
        Item i1 = new Item(1);
        Item i2 = new Item(2);
        Item i3 = new Item(3);
        Item i4 = new Item(4);
        Item i5 = new Item(5);
        
        i1.next = i2;
        i2.next = i3;
        i3.next = i4;
        i4.next = i5;
        
        print(i1);
        
        print(reverse(i1));
        
    }
    
    private static Item reverse(Item i)
    {
        Item previous = null;
        Item next = null;
        while (i!= null)
        {
            next = i.next;
            i.next = previous;
            previous = i;
            i = next;
        }
        return previous;
    }
    
    private static void print(Item i)
    {
        while(i!= null)
        {
            System.out.print(i.value);
            i = i.next;
        }
        System.out.println();
    }
    
    static class Item{
        int value;
        Item next;
        
        Item(int value)
        {
            this.value = value;
        }
    }

}

