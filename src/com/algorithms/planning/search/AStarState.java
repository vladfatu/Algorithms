package com.algorithms.planning.search;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by vlad on 19.01.2015.
 */
public abstract class AStarState {

    int distanceFromInitialNode;

    public AStarState()
    {
        this.distanceFromInitialNode = 0;
    }

    public abstract List<AStarState> getNeighbours();

    public List<AStarState> getNotVisitedNeighbours(Map<String, AStarState> visitedMap)
    {
        List<AStarState> neighbours = getNeighbours();
        Iterator<AStarState> neighboursIterator = neighbours.iterator();
        while (neighboursIterator.hasNext()) {
            if (visitedMap.containsKey(neighboursIterator.next().getKey()))
            {
                neighboursIterator.remove();
            }
        }
        return neighbours;
    }

    public int getDistanceFromInitialNode()
    {
        return distanceFromInitialNode;
    }

    public void setDistanceFromInitialNode(int distanceFromInitialNode)
    {
        this.distanceFromInitialNode = distanceFromInitialNode;
    }

    public abstract String getKey();
}
