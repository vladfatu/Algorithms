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

    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//        int w = in.nextInt(); // width of the board
//        int h = in.nextInt(); // height of the board
        int playerCount = 2;//in.nextInt(); // number of players (2 or 3)
        int myId = 1;//in.nextInt(); // id of my player (0 = 1st player, 1 = 2nd player, ...)

        System.err.println("my Id is: " + myId);

        Dragon myDragon = new Dragon(myId, new Point(0, 0));
        Dragon dragon1 = null;
        Dragon dragon2 = null;


        List<Point> destinationsRight = new ArrayList<Point>();
        for (int i = 0 ; i < 9; i++)
        {
            destinationsRight.add(new Point(8, i));
        }
        List<Point> destinationsLeft = new ArrayList<Point>();
        for (int i = 0 ; i < 9; i++)
        {
            destinationsLeft.add(new Point(0, i));
        }
        List<Point> destinationsDown = new ArrayList<Point>();
        for (int i = 0 ; i < 9; i++)
        {
            destinationsDown.add(new Point(i, 8));
        }

        if (myId == 0)
        {
            myDragon.setDestinations(destinationsRight);
            dragon1 = new Dragon(1, new Point(0, 0));
            dragon1.setDestinations(destinationsLeft);
            if (playerCount > 2)
            {
                dragon2 = new Dragon(2, new Point(0, 0));
                dragon2.setDestinations(destinationsDown);
            }
        }
        else if (myId == 1)
        {
            myDragon.setDestinations(destinationsLeft);
            dragon1 = new Dragon(0, new Point(0, 0));
            dragon1.setDestinations(destinationsRight);
            if (playerCount > 2)
            {
                dragon2 = new Dragon(2, new Point(0, 0));
                dragon2.setDestinations(destinationsDown);
            }
        }
        else
        {
            myDragon.setDestinations(destinationsDown);
            dragon1 = new Dragon(0, new Point(0, 0));
            dragon1.setDestinations(destinationsRight);
            dragon2 = new Dragon(1, new Point(0, 0));
            dragon2.setDestinations(destinationsLeft);
        }

        int round = 5;

        // game loop
//        while (true) {

            List<Wall> walls = new ArrayList<Wall>();
            Point start = null;

//            for (int i = 0; i < playerCount; i++) {
//                int x = in.nextInt(); // x-coordinate of the player
//                int y = in.nextInt(); // y-coordinate of the player
//                int wallsLeft = in.nextInt(); // number of walls available for the player
//
//                if (myDragon.getId() == i)
//                {
//                    myDragon.setPosition(new Point(x, y));
//                }
//                else if (dragon1.getId() == i)
//                {
//                    dragon1.setPosition(new Point(x, y));
//                }
//                else if (dragon2 != null && dragon2.getId() == i)
//                {
//                    dragon2.setPosition(new Point(x, y));
//                }
//
//            }

            walls.add(new Wall(2, 0, Orientation.V));
            walls.add(new Wall(3, 1, Orientation.V));
            walls.add(new Wall(1, 3, Orientation.H));

            myDragon.setPosition(new Point(8, 3));
            myDragon.setWallsLeft(10);
            dragon1.setPosition(new Point(2, 1));
            dragon1.setWallsLeft(10);

//            int wallCount = in.nextInt(); // number of walls on the board
//            for (int i = 0; i < wallCount; i++) {
//                int wallX = in.nextInt(); // x-coordinate of the wall
//                int wallY = in.nextInt(); // y-coordinate of the wall
//                String wallOrientation = in.next(); // wall orientation ('H' or 'V')
//                Wall wall = null;
//                if (wallOrientation.equals("H"))
//                {
//                    wall = new Wall(wallX, wallY, Orientation.H);
//                }
//                else
//                {
//                    wall = new Wall(wallX, wallY, Orientation.V);
//                }
//                walls.add(wall);
//            }

            long timestamp = System.currentTimeMillis();

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            int[][] adiacent = calculateAdiacent(walls);

            calculateNextPoint(adiacent, myDragon);

            Dragon bestDragon = null;

            if (dragon2 == null)
            {
                bestDragon = dragon1;
                calculateNextPoint(adiacent, dragon1);
            }
            else
            {
                calculateNextPoint(adiacent, dragon1);
                calculateNextPoint(adiacent, dragon2);
                if (dragon1.getBestDistance() <= dragon2.getBestDistance())
                {
                    bestDragon = dragon1;
                }
                else
                {
                    bestDragon = dragon2;
                }
            }

            System.err.println("my best distance: " + myDragon.getBestDistance());
            System.err.println("best dragon best distance: " + bestDragon.getBestDistance());

            if (myDragon.getBestDistance() <= bestDragon.getBestDistance() || myDragon.getWallsLeft() == 0 || round < 5)
            {
                long millis = System.currentTimeMillis() - timestamp;
                System.out.println(getDirection(myDragon.getPosition(), myDragon.getBestNeighbour()) + " " + millis); // action: LEFT, RIGHT, UP, DOWN or "putX putY putOrientation" to place a wall
            }
            else
            {
                Wall wall = findBestWall(walls, myDragon, bestDragon);
                if (wall != null)
                {
                    long millis = System.currentTimeMillis() - timestamp;
                    System.out.println(wall.getColumn() + " " + wall.getRow() + " " + wall.getOrientation().toString() + " " + millis);
                }
                else
                {
                    long millis = System.currentTimeMillis() - timestamp;
                    System.out.println(getDirection(myDragon.getPosition(), myDragon.getBestNeighbour()) + " " + millis); // action: LEFT, RIGHT, UP, DOWN or "putX putY putOrientation" to place a wall
                }

            }
//        }
    }

    private static boolean collision(Wall wallToCheck, List<Wall> walls)
    {
        for (Wall wall : walls)
        {
            if (collision(wall, wallToCheck))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean collision(Wall wall1, Wall wall2)
    {
        if (wall1.getOrientation() == Orientation.H)
        {
            if (wall2.getOrientation() == Orientation.H)
            {
                if (wall1.getRow() == wall2.getRow())
                {
                    if (Math.abs(wall1.getColumn() - wall2.getColumn()) <= 1)
                    {
                        return true;
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
            else
            {
                if (wall1.getColumn() == wall2.getColumn() - 1 && wall1.getRow() == wall2.getRow() + 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            if (wall2.getOrientation() == Orientation.V)
            {
                if (wall1.getColumn() == wall2.getColumn())
                {
                    if (Math.abs(wall1.getRow() - wall2.getRow()) <= 1)
                    {
                        return true;
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
            else
            {
                if (wall1.getColumn() == wall2.getColumn() + 1 && wall1.getRow() == wall2.getRow() - 1)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }

    private static Wall findBestWall(List<Wall> alreadyExistingWalls, Dragon myDragon, Dragon otherDragon)
    {
        Dragon myDragonCopy = new Dragon(myDragon.getId(), myDragon.getPosition());
        myDragonCopy.setDestinations(myDragon.getDestinations());
        Dragon otherDragonCopy = new Dragon(otherDragon.getId(), otherDragon.getPosition());
        otherDragonCopy.setDestinations(otherDragon.getDestinations());

        List<Wall> walls = new ArrayList<Wall>();
        walls.addAll(alreadyExistingWalls);

        Wall bestWall = null;

        int bestDifference = -200;
        int bestDifferenceForZero = -200;

        //vertical
        for (int i = 0; i < 8; i++)
        {
            for (int j = 1;j < 9; j++)
            {
                Wall wall = new Wall(j, i, Orientation.V);
                if (!collision(wall, walls))
                {
                    walls.add(wall);
                    int[][] adiacent = calculateAdiacent(walls);

                    calculateNextPoint(adiacent, myDragonCopy);
                    calculateNextPoint(adiacent, otherDragonCopy);

                    int otherDifference = otherDragonCopy.getBestDistance() - otherDragon.getBestDistance();
                    int difference = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (otherDifference);

                    if (myDragonCopy.getBestDistance() < 100 && otherDragonCopy.getBestDistance() < 100)
                    {
                        if (difference > bestDifference)
                        {
                            bestDifference = difference;
                            bestWall = wall;
                        }
                        else if (difference == bestDifference)
                        {
                            if (difference == 0)
                            {
                                if (otherDifference > bestDifferenceForZero)
                                {
                                    bestDifferenceForZero = otherDifference;
                                    bestDifference = difference;
                                    bestWall = wall;
                                }
                            }
                        }
                    }

                    walls.remove(wall);
                }
            }
        }

        //horizontal
        for (int i = 1; i < 9; i++)
        {
            for (int j = 0;j < 8; j++)
            {
                Wall wall = new Wall(j, i, Orientation.H);
                if (!collision(wall, walls))
                {
                    walls.add(wall);
                    int[][] adiacent = calculateAdiacent(walls);

                    calculateNextPoint(adiacent, myDragonCopy);
                    calculateNextPoint(adiacent, otherDragonCopy);

                    int otherDifference = otherDragonCopy.getBestDistance() - otherDragon.getBestDistance();
                    int difference = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (otherDifference);

                    if (myDragonCopy.getBestDistance() < 100 && otherDragonCopy.getBestDistance() < 100)
                    {
                        if (difference > bestDifference)
                        {
                            bestDifference = difference;
                            bestWall = wall;
                        }
                        else if (difference == bestDifference)
                        {
                            if (difference == 0)
                            {
                                if (otherDifference > bestDifferenceForZero)
                                {
                                    bestDifferenceForZero = otherDifference;
                                    bestDifference = difference;
                                    bestWall = wall;
                                }
                            }
                        }
                    }

                    walls.remove(wall);
                }
            }
        }

        System.err.println("best difference: " + bestDifference);
        System.err.println("best difference for zero: " + bestDifferenceForZero);

        if (bestDifference > 0)
        {
            return bestWall;
        }
        else
        {
            return null;
        }
    }

    private static void calculateNextPoint(int[][] adiacent, Dragon dragon)
    {
        int startIndex = getAdiacentIndex(dragon.getPosition());
        int min = 100;
        int minNeighbourIndex = -1;
        for (int i = 0; i < 81; i++)
        {
            if (adiacent[startIndex][i] == 1)
            {
                int minForNeighbour = 100;
                for (Point destination : dragon.getDestinations())
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
            // System.err.println("Min distance: " + (min + 1));
            dragon.setBestNeighbour(getPointFromAdiacentIndex(minNeighbourIndex));
            dragon.setBestDistance(min + 1);
        }
        else
        {
            dragon.setBestNeighbour(null);
            dragon.setBestDistance(100);
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

class Dragon
{
    private int id;
    private Point position;
    private Point bestNeighbour;
    private int bestDistance;
    private int wallsLeft;

    private List<Point> destinations;

    public Dragon(int id, Point position)
    {
        this.id = id;
        this.position = position;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public Point getBestNeighbour()
    {
        return bestNeighbour;
    }

    public void setBestNeighbour(Point bestNeighbour)
    {
        this.bestNeighbour = bestNeighbour;
    }

    public int getBestDistance()
    {
        return bestDistance;
    }

    public void setBestDistance(int bestDistance)
    {
        this.bestDistance = bestDistance;
    }

    public List<Point> getDestinations()
    {
        return destinations;
    }

    public void setDestinations(List<Point> destinations)
    {
        this.destinations = destinations;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getWallsLeft()
    {
        return wallsLeft;
    }

    public void setWallsLeft(int wallsLeft)
    {
        this.wallsLeft = wallsLeft;
    }
}