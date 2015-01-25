package com.algorithms.planning.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 1/25/2015.
 */
public class EightProblemAStarState extends AStarState {

    private int[][] state;
    private String key;

    public EightProblemAStarState(int[][] state)
    {
        super();
        this.state = state;
        generateKey();
    }

    private void generateKey()
    {
        StringBuffer buffer = new StringBuffer();
        int rows = state.length;
        int columns = 0;
        if (rows > 0)
        {
            columns = state[0].length;
        }
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                buffer.append(state[i][j]);
            }
        }
        this.key = buffer.toString();
    }

    @Override
    public List<AStarState> getNeighbours() {
        List<AStarState> neighbours = new ArrayList<AStarState>();

        return neighbours;
    }

    @Override
    public String getKey() {

        return this.key;
    }
}
