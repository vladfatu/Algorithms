package com.teststuff;

/**
 * Created by vlad on 03.03.2015.
 */

import com.algorithms.utils.Utils;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 */
class Escape {

    public static void main(String args[])
    {

        long timestamp = System.currentTimeMillis();

        List<Wall> walls = new ArrayList<Wall>();

        walls.add(new Wall(1, 1, Orientation.H));
        walls.add(new Wall(3, 0, Orientation.V));

        int[][] adiacent = calculateAdiacent(walls);

        List<Point> destinations = new ArrayList<Point>();

        for (int i = 0 ; i < 9; i++)
        {
            destinations.add(new Point(8, i));
        }

        Point start = new Point(0, 0);

        while (start.getColumn() < 8)
        {
            long millis = System.currentTimeMillis() - timestamp;
            Point nextPoint = getNextPoint(adiacent, start, destinations);
            System.err.println(nextPoint.getColumn() + ", " + nextPoint.getRow());
            System.err.println(getDirection(start, nextPoint) + " " + millis); // action: LEFT, RIGHT, UP, DOWN or "putX putY putOrientation" to place a wall
            // System.out.println("3 2 V " + millis);
            start = nextPoint;
        }

        Utils.printIntMatrix(adiacent);
    }

    private static Point getNextPoint(int[][] adiacent, Point start, List<Point> destinations)
    {
        int startIndex = getAdiacentIndex(start);
        int min = 100;
        int minNeighbourIndex = -1;
        for (int i = 0; i < 81; i++)
        {
            if (adiacent[startIndex][i] == 1)
            {
                int minForNeighbour = 100;
                for (Point destination : destinations)
                {
                    int destinationIndex = getAdiacentIndex(destination);
                    if (minForNeighbour > adiacent[i][destinationIndex])
                    {
                        minForNeighbour = adiacent[i][destinationIndex];
                    }
                }
                if (min > minForNeighbour)
                {
                    min = minForNeighbour;
                    minNeighbourIndex = i;
                }
            }
        }

        if (minNeighbourIndex != -1)
        {
            return getPointFromAdiacentIndex(minNeighbourIndex);
        }
        else
        {
            return null;
        }
    }

    private static String getDirection(Point start, Point neighbour)
    {
        if (start != null && neighbour != null)
        {
            if (start.getRow() == neighbour.getRow() && start.getColumn() + 1 == neighbour.getColumn())
            {
                return "RIGHT";
            } else if (start.getRow() == neighbour.getRow() && start.getColumn() - 1 == neighbour.getColumn())
            {
                return "LEFT";
            } else if (start.getRow() + 1 == neighbour.getRow() && start.getColumn() == neighbour.getColumn())
            {
                return "DOWN";
            } else if (start.getRow() - 1 == neighbour.getRow() && start.getColumn() == neighbour.getColumn())
            {
                return "UP";
            } else
            {
                return "RIGHT";
            }
        }
        else
        {
            return "RIGHT";
        }

    }

    private static int[][] calculateAdiacent(List<Wall> walls)
    {
        int[][] adiacent = new int[81][81];

        for (int i = 0;i<81;i++)
        {
            for (int j = 0;j<81;j++)
            {
                adiacent[i][j] = 100;
            }
        }

        for (int x = 0;x<81;x++)
        {
            adiacent[x][x] = 0;
        }

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                int rowIndex = getAdiacentIndex(i, j);

                int columnIndex;

                if (i > 0)
                {
                    columnIndex = getAdiacentIndex(i-1, j);
                    adiacent[rowIndex][columnIndex] = 1;
                }

                if (i < 8)
                {
                    columnIndex = getAdiacentIndex(i+1, j);
                    adiacent[rowIndex][columnIndex] = 1;
                }

                if (j > 0)
                {
                    columnIndex = getAdiacentIndex(i, j-1);
                    adiacent[rowIndex][columnIndex] = 1;
                }

                if (j < 8)
                {
                    columnIndex = getAdiacentIndex(i, j+1);
                    adiacent[rowIndex][columnIndex] = 1;
                }
            }
        }

        for (Wall wall : walls)
        {
            if (wall.getOrientation() == Orientation.H)
            {
                Point point1 = new Point(wall.getColumn(), wall.getRow() - 1);
                Point point2 = new Point(wall.getColumn(), wall.getRow());

                int adiacentIndex1 = getAdiacentIndex(point1);
                int adiacentIndex2 = getAdiacentIndex(point2);

                adiacent[adiacentIndex1][adiacentIndex2] = 100;
                adiacent[adiacentIndex2][adiacentIndex1] = 100;

                point1 = new Point(wall.getColumn() + 1, wall.getRow() - 1);
                point2 = new Point(wall.getColumn() + 1, wall.getRow());

                adiacentIndex1 = getAdiacentIndex(point1);
                adiacentIndex2 = getAdiacentIndex(point2);

                adiacent[adiacentIndex1][adiacentIndex2] = 100;
                adiacent[adiacentIndex2][adiacentIndex1] = 100;
            }
            else
            {
                Point point1 = new Point(wall.getColumn() - 1, wall.getRow());
                Point point2 = new Point(wall.getColumn(), wall.getRow());

                int adiacentIndex1 = getAdiacentIndex(point1);
                int adiacentIndex2 = getAdiacentIndex(point2);

                adiacent[adiacentIndex1][adiacentIndex2] = 100;
                adiacent[adiacentIndex2][adiacentIndex1] = 100;

                point1 = new Point(wall.getColumn() - 1, wall.getRow() + 1);
                point2 = new Point(wall.getColumn(), wall.getRow() + 1);

                adiacentIndex1 = getAdiacentIndex(point1);
                adiacentIndex2 = getAdiacentIndex(point2);

                adiacent[adiacentIndex1][adiacentIndex2] = 100;
                adiacent[adiacentIndex2][adiacentIndex1] = 100;
            }
        }

        for (int k = 0;k<81;k++)
        {
            for (int i = 0;i<81;i++)
            {
                for (int j = 0;j<81;j++)
                {
                    if (adiacent[i][j] > adiacent[i][k] + adiacent[k][j])
                    {
                        adiacent[i][j] = adiacent[i][k] + adiacent[k][j];
                    }
                }
            }
        }
        return adiacent;
    }

    private static Point getPointFromAdiacentIndex(int index)
    {
        return new Point(index % 9, index / 9);
    }

    private static int getAdiacentIndex(Point point)
    {
        return point.getRow()*9 + point.getColumn();
    }

    private static int getAdiacentIndex(int i, int j)
    {
        return i*9 + j;
    }
}

class Point
{
    private int row;
    private int column;

    public  Point(int column, int row)
    {
        this.row = row;
        this.column = column;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (column != point.column) return false;
        if (row != point.row) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}

enum Orientation
{
    H,
    V
}

class Wall
{
    private int row;
    private int column;
    private Orientation orientation;

    public Wall(int column, int row, Orientation orientation)
    {
        this.row = row;
        this.column = column;
        this.orientation = orientation;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    public Orientation getOrientation()
    {
        return orientation;
    }
}