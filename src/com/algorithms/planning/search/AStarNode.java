package com.algorithms.planning.search;

import java.util.List;

/**
 * Created by vlad on 19.01.2015.
 */
public class AStarNode {

    int distanceFromInitialNode;

    public AStarNode()
    {
        this.distanceFromInitialNode = 0;
    }

    public int getDistanceFromInitialNode()
    {
        return distanceFromInitialNode;
    }

//    public List<AStarNode> getNeighbours()
//    {
//
//    }

}
