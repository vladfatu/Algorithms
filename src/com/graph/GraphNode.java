package com.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode{

	List<GraphNode> adiacentNodes;
	String value;
	
	public GraphNode(String value)
	{
		this.value = value;
		adiacentNodes = new ArrayList<GraphNode>();
	}
	
	public void addAdiacentNode(GraphNode node)
	{
		adiacentNodes.add(node);
	}

}
