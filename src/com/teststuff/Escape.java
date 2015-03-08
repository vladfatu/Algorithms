package com.teststuff;

/**
 * Created by vlad on 03.03.2015.
 */

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
        int playerCount = 3;//in.nextInt(); // number of players (2 or 3)
        int myId = 1;//in.nextInt(); // id of my player (0 = 1st player, 1 = 2nd player, ...)

        System.err.println("my Id is: " + myId);

        Dragon myDragon = new Dragon(myId, new Point(0, 0));
        Dragon dragon1 = null;
        Dragon dragon2 = null;


        int destinationsRightColumn = 8;
        int destinationsRightRow = -1;

        int destinationsLeftColumn = 0;
        int destinationsLeftRow = -1;

        int destinationsDownColumn = -1;
        int destinationsDownRow = 8;

        if (myId == 0)
        {
            myDragon.setDestination(destinationsRightColumn, destinationsRightRow);
            dragon1 = new Dragon(1, new Point(0, 0));
            dragon1.setDestination(destinationsLeftColumn, destinationsLeftRow);
            if (playerCount > 2)
            {
                dragon2 = new Dragon(2, new Point(0, 0));
                dragon2.setDestination(destinationsDownColumn, destinationsDownRow);
            }
        }
        else if (myId == 1)
        {
            myDragon.setDestination(destinationsLeftColumn, destinationsLeftRow);
            dragon1 = new Dragon(0, new Point(0, 0));
            dragon1.setDestination(destinationsRightColumn, destinationsRightRow);
            if (playerCount > 2)
            {
                dragon2 = new Dragon(2, new Point(0, 0));
                dragon2.setDestination(destinationsDownColumn, destinationsDownRow);
            }
        }
        else
        {
            myDragon.setDestination(destinationsDownColumn, destinationsDownRow);
            dragon1 = new Dragon(0, new Point(0, 0));
            dragon1.setDestination(destinationsRightColumn, destinationsRightRow);
            dragon2 = new Dragon(1, new Point(0, 0));
            dragon2.setDestination(destinationsLeftColumn, destinationsLeftRow);
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

            walls.add(new Wall(7, 5, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.H));
            walls.add(new Wall(5, 7, Orientation.V));
            walls.add(new Wall(8, 3, Orientation.V));
            walls.add(new Wall(5, 5, Orientation.H));
            walls.add(new Wall(2, 4, Orientation.H));
            walls.add(new Wall(4, 4, Orientation.H));
            walls.add(new Wall(5, 4, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.V));



            myDragon.setPosition(new Point(5, 7));
            myDragon.setWallsLeft(10);
            dragon1.setPosition(new Point(3, 4));
            dragon1.setWallsLeft(10);

            dragon2.setPosition(new Point(2, 3));
            dragon2.setWallsLeft(10);

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

            int[][] adiacent = new int[81][81];
            addWalls(adiacent, walls);

            calculateShortestPath(adiacent, myDragon);

            Dragon bestDragon = null;
            Dragon otherDragon = null;

            if (dragon2 == null)
            {
                bestDragon = dragon1;
                calculateShortestPath(adiacent, dragon1);
            }
            else
            {
                calculateShortestPath(adiacent, dragon1);
                calculateShortestPath(adiacent, dragon2);
                if (dragon1.getBestDistance() <= dragon2.getBestDistance())
                {
                    bestDragon = dragon1;
                    otherDragon = dragon2;
                }
                else
                {
                    bestDragon = dragon2;
                    otherDragon = dragon1;
                }
            }

            System.err.println("my best distance: " + myDragon.getBestDistance());
            System.err.println("best dragon best distance: " + bestDragon.getBestDistance());

            GameState state = getGameState(walls, adiacent, myDragon, bestDragon, otherDragon);

            boolean offensiveWall = false;
            boolean defensiveWall = false;

            int pathDifference = myDragon.getBestDistance() - bestDragon.getBestDistance();

            if (pathDifference <= 0 || myDragon.getWallsLeft() == 0 || round < 5)
            {

            }
            else
            {

            }

            if (offensiveWall)
            {
                Wall wall = state.getBestWall();
                long millis = System.currentTimeMillis() - timestamp;
                System.out.println(wall.getColumn() + " " + wall.getRow() + " " + wall.getOrientation().toString() + " " + millis);
            }
            else if (defensiveWall)
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

    private static void calculateShortestPath(int[][] adiacent, Dragon dragon) {
        AStarManhattan aStar = new AStarManhattan();

        List<Point> shortestPath = aStar.getShortestPath(adiacent, dragon.getPosition(), dragon.getDestinationColumn(), dragon.getDestinationRow());

        if (shortestPath != null && shortestPath.size() > 0) {
            dragon.setBestNeighbour(shortestPath.get(1));
            dragon.setBestDistance(shortestPath.size());
        }
        else
        {
            dragon.setBestNeighbour(null);
            dragon.setBestDistance(100);
        }
//        System.out.println(shortestPath);
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

    private static GameState getGameState(List<Wall> alreadyExistingWalls, int[][] adiacent, Dragon myDragon, Dragon bestDragon, Dragon otherDragon)
    {
        Dragon myDragonCopy = new Dragon(myDragon.getId(), myDragon.getPosition());
        myDragonCopy.setDestinationColumn(myDragon.getDestinationColumn());
        myDragonCopy.setDestinationRow(myDragon.getDestinationRow());

        Dragon bestDragonCopy = new Dragon(bestDragon.getId(), bestDragon.getPosition());
        bestDragonCopy.setDestinationColumn(bestDragon.getDestinationColumn());
        bestDragonCopy.setDestinationRow(bestDragon.getDestinationRow());

        Dragon otherDragonCopy = null;

        if (otherDragon != null) {
            otherDragonCopy = new Dragon(otherDragon.getId(), otherDragon.getPosition());
            otherDragonCopy.setDestinationColumn(otherDragon.getDestinationColumn());
            otherDragonCopy.setDestinationRow(otherDragon.getDestinationRow());
        }

        List<Wall> walls = new ArrayList<Wall>();
        walls.addAll(alreadyExistingWalls);

        Wall bestWall = null;
        Wall worstWall = null;

        int bestDifference = -200;
        int worstDifference = 200;
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
                    addWall(adiacent, wall);

                    calculateShortestPath(adiacent, myDragonCopy);
                    calculateShortestPath(adiacent, bestDragonCopy);
                    if (otherDragonCopy != null) {
                        calculateShortestPath(adiacent, otherDragonCopy);
                    }

                    int differenceForBestDragon = bestDragonCopy.getBestDistance() - bestDragon.getBestDistance();
                    int differenceForOtherDragon = 0;
                    if (otherDragonCopy != null)
                    {
                        differenceForOtherDragon = otherDragonCopy.getBestDistance() - otherDragon.getBestDistance();
                    }
                    int difference = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (differenceForBestDragon);

                    if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
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
                                if (differenceForBestDragon > bestDifferenceForZero)
                                {
                                    bestDifferenceForZero = differenceForBestDragon;
                                    bestDifference = difference;
                                    bestWall = wall;
                                }
                            }
                        }

                        if (difference < worstDifference)
                        {
                            worstDifference = difference;
                            worstWall = wall;
                        }
                    }

                    walls.remove(wall);
                    removeWall(adiacent, wall);
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
                    addWall(adiacent, wall);

                    calculateShortestPath(adiacent, myDragonCopy);
                    calculateShortestPath(adiacent, bestDragonCopy);
                    if (otherDragonCopy != null) {
                        calculateShortestPath(adiacent, otherDragonCopy);
                    }

                    int differenceForBestDragon = bestDragonCopy.getBestDistance() - bestDragon.getBestDistance();
                    int differenceForOtherDragon = 0;
                    if (otherDragonCopy != null)
                    {
                        differenceForOtherDragon = otherDragonCopy.getBestDistance() - otherDragon.getBestDistance();
                    }
                    int difference = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (differenceForBestDragon);

                    if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
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
                                if (differenceForBestDragon > bestDifferenceForZero)
                                {
                                    bestDifferenceForZero = differenceForBestDragon;
                                    bestDifference = difference;
                                    bestWall = wall;
                                }
                            }
                        }

                        if (difference < worstDifference)
                        {
                            worstDifference = difference;
                            worstWall = wall;
                        }
                    }

                    walls.remove(wall);
                    removeWall(adiacent, wall);
                }
            }
        }

        System.err.println("best difference: " + bestDifference);
        System.err.println("best difference for zero: " + bestDifferenceForZero);

        GameState state = new GameState();

        state.setBestWall(bestWall);
        state.setBestDifference(bestDifference);
        state.setWorstWall(worstWall);
        state.setWorstDifference(worstDifference);

        return state;
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

    private static void addWalls(int[][] adiacent, List<Wall> walls)
    {
        for (Wall wall : walls)
        {
            addWall(adiacent, wall);
        }
    }

    private static void addWall(int[][] adiacent, Wall wall)
    {
        if (wall.getOrientation() == Orientation.H)
        {
            Point point1 = new Point(wall.getColumn(), wall.getRow() - 1);
            Point point2 = new Point(wall.getColumn(), wall.getRow());

            int adiacentIndex1 = getAdiacentIndex(point1);
            int adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 1;
            adiacent[adiacentIndex2][adiacentIndex1] = 1;

            point1 = new Point(wall.getColumn() + 1, wall.getRow() - 1);
            point2 = new Point(wall.getColumn() + 1, wall.getRow());

            adiacentIndex1 = getAdiacentIndex(point1);
            adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 1;
            adiacent[adiacentIndex2][adiacentIndex1] = 1;
        }
        else
        {
            Point point1 = new Point(wall.getColumn() - 1, wall.getRow());
            Point point2 = new Point(wall.getColumn(), wall.getRow());

            int adiacentIndex1 = getAdiacentIndex(point1);
            int adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 1;
            adiacent[adiacentIndex2][adiacentIndex1] = 1;

            point1 = new Point(wall.getColumn() - 1, wall.getRow() + 1);
            point2 = new Point(wall.getColumn(), wall.getRow() + 1);

            adiacentIndex1 = getAdiacentIndex(point1);
            adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 1;
            adiacent[adiacentIndex2][adiacentIndex1] = 1;
        }
    }

    private static void removeWall(int[][] adiacent, Wall wall)
    {
        if (wall.getOrientation() == Orientation.H)
        {
            Point point1 = new Point(wall.getColumn(), wall.getRow() - 1);
            Point point2 = new Point(wall.getColumn(), wall.getRow());

            int adiacentIndex1 = getAdiacentIndex(point1);
            int adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 0;
            adiacent[adiacentIndex2][adiacentIndex1] = 0;

            point1 = new Point(wall.getColumn() + 1, wall.getRow() - 1);
            point2 = new Point(wall.getColumn() + 1, wall.getRow());

            adiacentIndex1 = getAdiacentIndex(point1);
            adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 0;
            adiacent[adiacentIndex2][adiacentIndex1] = 0;
        }
        else
        {
            Point point1 = new Point(wall.getColumn() - 1, wall.getRow());
            Point point2 = new Point(wall.getColumn(), wall.getRow());

            int adiacentIndex1 = getAdiacentIndex(point1);
            int adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 0;
            adiacent[adiacentIndex2][adiacentIndex1] = 0;

            point1 = new Point(wall.getColumn() - 1, wall.getRow() + 1);
            point2 = new Point(wall.getColumn(), wall.getRow() + 1);

            adiacentIndex1 = getAdiacentIndex(point1);
            adiacentIndex2 = getAdiacentIndex(point2);

            adiacent[adiacentIndex1][adiacentIndex2] = 0;
            adiacent[adiacentIndex2][adiacentIndex1] = 0;
        }
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
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Point");
        stringBuffer.append('(').append(this.column).append(", ").append(this.row).append(')');
        return stringBuffer.toString();
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

    private int destinationColumn;
    private int destinationRow;

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

    public void setDestination(int destinationColumn, int destinationRow) {
        this.destinationColumn = destinationColumn;
        this.destinationRow = destinationRow;
    }

    public int getDestinationColumn() {
        return destinationColumn;
    }

    public void setDestinationColumn(int destinationColumn) {
        this.destinationColumn = destinationColumn;
    }

    public int getDestinationRow() {
        return destinationRow;
    }

    public void setDestinationRow(int destinationRow) {
        this.destinationRow = destinationRow;
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

class AStarNode {

    private Point position;
    private int rowSize;
    private int columnSize;
    private boolean isValid;
    private int score;
    private int estimatedScore;
    private AStarNode parent;

    public  AStarNode(Point position, int rowSize, int columnSize)
    {
        this.position = position;
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        checkIfValid();
    }

    public List<AStarNode> getNotVisitedNeighbours(Map<Point, AStarNode> visitedMap, int[][] adiacent)
    {
        List<AStarNode> neighbours = new ArrayList<AStarNode>();
        if (visitedMap != null && isValid)
        {
            int positionIndex = getAdiacentIndex(this.position);
            Point point;
            if (this.position.getRow() > 0)
            {
                point = new Point(this.position.getColumn(), this.position.getRow() - 1);
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0 && !visitedMap.containsKey(point))
                {
                    neighbours.add(new AStarNode(point, rowSize, columnSize));
                }
            }
            if (this.position.getRow() + 1 < this.rowSize)
            {
                point = new Point(this.position.getColumn(), this.position.getRow() + 1);
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0 && !visitedMap.containsKey(point))
                {
                    neighbours.add(new AStarNode(point, rowSize, columnSize));
                }
            }
            if (this.position.getColumn() > 0)
            {
                point = new Point(this.position.getColumn() - 1, this.position.getRow());
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0 && !visitedMap.containsKey(point))
                {
                    neighbours.add(new AStarNode(point, rowSize, columnSize));
                }
            }
            if (this.position.getColumn() + 1 < this.columnSize)
            {
                point = new Point(this.position.getColumn() + 1, this.position.getRow());
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0 && !visitedMap.containsKey(point))
                {
                    neighbours.add(new AStarNode(point, rowSize, columnSize));
                }
            }
        }
        return neighbours;
    }

    private static int getAdiacentIndex(Point point)
    {
        return point.getRow()*9 + point.getColumn();
    }

    private void checkIfValid()
    {
        if (this.position != null && rowSize > 0 && columnSize > 0
                && this.position.getRow() >= 0
                && this.position.getRow() < this.rowSize
                && this.position.getColumn() >= 0
                && this.position.getColumn() < this.columnSize)
        {
            this.isValid = true;

        } else
        {
            this.isValid = false;
        }
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public AStarNode getParent()
    {
        return parent;
    }

    public void setParent(AStarNode parent)
    {
        this.parent = parent;
    }

    public int getEstimatedScore()
    {
        return estimatedScore;
    }

    public void setEstimatedScore(int estimatedScore)
    {
        this.estimatedScore = estimatedScore;
    }
}


abstract class AStar {

    private static final int UNIT = 1;

    public List<Point> getShortestPath(int[][] adiacent, Point origin, int destinationColumn, int destinationRow) {
        int rowSize = 9;
        int columnSize = 9;

        Map<Point, AStarNode> visitedMap = new HashMap<Point, AStarNode>();
        Map<Point, AStarNode> nodesToVisitMap = new HashMap<Point, AStarNode>();

        AStarNode startNode = new AStarNode(origin, rowSize, columnSize);
        startNode.setScore(0);
        startNode.setEstimatedScore(heuristicDistance(origin, destinationColumn, destinationRow));

        nodesToVisitMap.put(startNode.getPosition(), startNode);

        while (!nodesToVisitMap.isEmpty()) {
            AStarNode currentNode = getClosestEstimatedNode(nodesToVisitMap);
            if (currentNode.getPosition().getColumn() == destinationColumn || currentNode.getPosition().getRow() == destinationRow) {
                return reconstructPath(currentNode);
            }

            nodesToVisitMap.remove(currentNode.getPosition());
            visitedMap.put(currentNode.getPosition(), currentNode);

            List<AStarNode> neighbours = currentNode.getNotVisitedNeighbours(visitedMap, adiacent);

            for (AStarNode node : neighbours) {
                boolean newNode = false;
                int tentativeScore = currentNode.getScore() + UNIT;

                AStarNode nodeToVisit = nodesToVisitMap.get(node.getPosition());
                if (nodeToVisit == null) {
                    nodeToVisit = node;
                    nodesToVisitMap.put(nodeToVisit.getPosition(), nodeToVisit);
                    newNode = true;
                }

                if (newNode || tentativeScore < nodeToVisit.getScore()) {
                    nodeToVisit.setParent(currentNode);
                    nodeToVisit.setScore(tentativeScore);
                    nodeToVisit.setEstimatedScore(nodeToVisit.getScore() + heuristicDistance(nodeToVisit.getPosition(), destinationColumn, destinationRow));
                }

            }

        }

        return null;   //should make this work for fog of war as well

    }

    private List<Point> reconstructPath(AStarNode goalNode)
    {
        List<Point> path = new ArrayList<Point>();
        AStarNode currentNode = goalNode;
        while (currentNode.getParent() != null)
        {
            path.add(currentNode.getPosition());
            currentNode = currentNode.getParent();
        }
        path.add(currentNode.getPosition());
        Collections.reverse(path);
        return path;
    }

    private AStarNode getClosestEstimatedNode(Map<Point, AStarNode> nodesMap)
    {
        AStarNode closestEstimatedNode = null;
        int min = Integer.MAX_VALUE;
        for (AStarNode node : nodesMap.values())
        {
            if (node.getEstimatedScore() < min)
            {
                closestEstimatedNode = node;
                min = node.getEstimatedScore();
            }
        }
        return  closestEstimatedNode;
    }

    public abstract int heuristicDistance(Point startPoint, int destinationColumn, int destinationRow);

}

class AStarManhattan extends AStar {

    @Override
    public int heuristicDistance(Point startPoint, int destinationColumn, int destinationRow)
    {
        if (destinationColumn == -1)
        {
            return Math.abs(startPoint.getRow() - destinationRow);
        }
        else
        {
            return Math.abs(startPoint.getColumn() - destinationColumn);
        }
    }
}

class GameState
{
    private Wall bestWall;
    private Wall worstWall;
    private int bestDifference;
    private int worstDifference;

    public Wall getBestWall() {
        return bestWall;
    }

    public void setBestWall(Wall bestWall) {
        this.bestWall = bestWall;
    }

    public Wall getWorstWall() {
        return worstWall;
    }

    public void setWorstWall(Wall worstWall) {
        this.worstWall = worstWall;
    }

    public int getBestDifference() {
        return bestDifference;
    }

    public void setBestDifference(int bestDifference) {
        this.bestDifference = bestDifference;
    }

    public int getWorstDifference() {
        return worstDifference;
    }

    public void setWorstDifference(int worstDifference) {
        this.worstDifference = worstDifference;
    }
}
