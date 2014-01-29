package com.trees;

public class BSTValidator{

	public static void main(String[] args)
	{
		Node root = new Node(7);
		root.left = new Node(2);
		root.right = new Node(10);
		root.left.left = new Node(1);
		root.left.right = new Node(6);
		root.right.left = new Node(8);
		
		System.out.println(isValid(root, -1000, 1000));
	}
	
	public static boolean isValid(Node node, int min, int max)
	{
		if (node == null)
		{
			return true;
		}
		else
		{
			if (node.value < max && node.value > min)
			{
				if (node.left == null && node.right == null)
				{
					return true;
				}
				else if (node.right == null && node.value > node.left.value)
				{
					return isValid(node.left, min, node.value);
				}
				else if (node.left == null && node.value < node.right.value)
				{
					return isValid(node.right, node.value, max);
				}
				else if (node.value > node.left.value && node.value < node.right.value)
				{
					return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}
}