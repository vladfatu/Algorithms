package com.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS{
	
	public static void main(String[] args)
	{
		GraphNode frankfurtNode = new GraphNode("Frankfurt");
		GraphNode mannheimNode = new GraphNode("Mannheim");
		GraphNode wurzburgNode = new GraphNode("Wurzburg");
		GraphNode stuttgartNode = new GraphNode("Stuttgart");
		GraphNode kasselNode = new GraphNode("Kassel");
		GraphNode karlsruheNode = new GraphNode("Karlsruhe");
		GraphNode erfurtNode = new GraphNode("Erfurt");
		GraphNode nurnbergNode = new GraphNode("Nurnberg");
		GraphNode augsburgNode = new GraphNode("Augsburg");
		GraphNode munchenNode = new GraphNode("Munchen");
		
		frankfurtNode.addAdiacentNode(mannheimNode);
		frankfurtNode.addAdiacentNode(wurzburgNode);
		frankfurtNode.addAdiacentNode(kasselNode);
		
		mannheimNode.addAdiacentNode(frankfurtNode);
		mannheimNode.addAdiacentNode(karlsruheNode);
		
		wurzburgNode.addAdiacentNode(frankfurtNode);
		wurzburgNode.addAdiacentNode(nurnbergNode);
		wurzburgNode.addAdiacentNode(erfurtNode);
		
		stuttgartNode.addAdiacentNode(nurnbergNode);
		
		kasselNode.addAdiacentNode(frankfurtNode);
		kasselNode.addAdiacentNode(munchenNode);
		
		karlsruheNode.addAdiacentNode(mannheimNode);
		karlsruheNode.addAdiacentNode(augsburgNode);
		
		erfurtNode.addAdiacentNode(wurzburgNode);
		
		nurnbergNode.addAdiacentNode(wurzburgNode);
		nurnbergNode.addAdiacentNode(stuttgartNode);
		nurnbergNode.addAdiacentNode(munchenNode);
		
		augsburgNode.addAdiacentNode(karlsruheNode);
		augsburgNode.addAdiacentNode(munchenNode);
		
		munchenNode.addAdiacentNode(augsburgNode);
		munchenNode.addAdiacentNode(nurnbergNode);
		munchenNode.addAdiacentNode(kasselNode);
		
		traverseTree(frankfurtNode);
		
	}

	public static void traverseTree(GraphNode root)
	{
		Set<String> passedNodes = new HashSet<String>();
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.add(root);
		while (!q.isEmpty())
		{	
			GraphNode tempNode = q.poll();
			passedNodes.add(tempNode.value);
			
			System.out.print(tempNode.value + " ; ");
			
			for (GraphNode node : tempNode.adiacentNodes)
			{
				if (!passedNodes.contains(node.value))
				{
					q.add(node);
					passedNodes.add(node.value);
				}
			}
		}
	}

}