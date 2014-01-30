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
		GraphNode würzburgNode = new GraphNode("Würzburg");
		GraphNode stuttgartNode = new GraphNode("Stuttgart");
		GraphNode kasselNode = new GraphNode("Kassel");
		GraphNode karlsruheNode = new GraphNode("Karlsruhe");
		GraphNode erfurtNode = new GraphNode("Erfurt");
		GraphNode nürnbergNode = new GraphNode("Nürnberg");
		GraphNode augsburgNode = new GraphNode("Augsburg");
		GraphNode münchenNode = new GraphNode("München");
		
		frankfurtNode.addAdiacentNode(mannheimNode);
		frankfurtNode.addAdiacentNode(würzburgNode);
		frankfurtNode.addAdiacentNode(kasselNode);
		
		mannheimNode.addAdiacentNode(frankfurtNode);
		mannheimNode.addAdiacentNode(karlsruheNode);
		
		würzburgNode.addAdiacentNode(frankfurtNode);
		würzburgNode.addAdiacentNode(nürnbergNode);
		würzburgNode.addAdiacentNode(erfurtNode);
		
		stuttgartNode.addAdiacentNode(nürnbergNode);
		
		kasselNode.addAdiacentNode(frankfurtNode);
		kasselNode.addAdiacentNode(münchenNode);
		
		karlsruheNode.addAdiacentNode(mannheimNode);
		karlsruheNode.addAdiacentNode(augsburgNode);
		
		erfurtNode.addAdiacentNode(würzburgNode);
		
		nürnbergNode.addAdiacentNode(würzburgNode);
		nürnbergNode.addAdiacentNode(stuttgartNode);
		nürnbergNode.addAdiacentNode(münchenNode);
		
		augsburgNode.addAdiacentNode(karlsruheNode);
		augsburgNode.addAdiacentNode(münchenNode);
		
		münchenNode.addAdiacentNode(augsburgNode);
		münchenNode.addAdiacentNode(nürnbergNode);
		münchenNode.addAdiacentNode(kasselNode);
		
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