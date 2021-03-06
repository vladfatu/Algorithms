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
        int playerCount = 2;//in.nextInt(); // number of players (2 or 3)
        int myId = 0;//in.nextInt(); // id of my player (0 = 1st player, 1 = 2nd player, ...)

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

        int round = 7;

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

            walls.add(new Wall(3, 7, Orientation.V));
            walls.add(new Wall(3, 7, Orientation.H));
            walls.add(new Wall(8, 3, Orientation.V));
            walls.add(new Wall(8, 5, Orientation.V));
            walls.add(new Wall(5, 7, Orientation.H));
            walls.add(new Wall(8, 7, Orientation.V));
            walls.add(new Wall(7, 1, Orientation.V));
            walls.add(new Wall(5, 1, Orientation.H));
            walls.add(new Wall(5, 5, Orientation.V));
            walls.add(new Wall(5, 3, Orientation.V));
            walls.add(new Wall(6, 3, Orientation.H));
            walls.add(new Wall(5, 4, Orientation.H));
            walls.add(new Wall(6, 5, Orientation.H));
            walls.add(new Wall(3, 1, Orientation.H));
            walls.add(new Wall(3, 1, Orientation.V));
            walls.add(new Wall(4, 2, Orientation.V));
            walls.add(new Wall(2, 3, Orientation.H));
            walls.add(new Wall(6, 1, Orientation.V));

            myDragon.setPosition(new Point(3, 1));
            myDragon.setWallsLeft(1);
            dragon1.setPosition(new Point(6, 4));
            dragon1.setWallsLeft(10);

//            dragon2.setPosition(new Point(0, 0));
//            dragon2.setWallsLeft(10);

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
//                if (dragon1.getBestDistance() <= dragon2.getBestDistance())
//                {
//                    bestDragon = dragon1;
//                    otherDragon = dragon2;
//                }
//                else
//                {
//                    bestDragon = dragon2;
//                    otherDragon = dragon1;
//                }
                bestDragon = dragon1;
                otherDragon = dragon2;
            }

            boolean offensiveWall = false;
            boolean defensiveWall = false;

            System.err.println("my best distance: " + myDragon.getBestDistance());
            System.err.println("best dragon best distance: " + bestDragon.getBestDistance());

            int levelsDeep = 2;
            if (myDragon.getWallsLeft() < 2)
            {
                levelsDeep = 1;
            }

            GameState state = getGameState(walls, adiacent, myDragon, bestDragon, otherDragon, levelsDeep);

            int bestDifferenceAverage = Math.max((int) (state.getBestDifference() / 2), (int) (state.getBestDifferenceForCurrentLevel()));
            int worstDifference = (int) state.getWorstDifference();

            if (bestDragon.getWallsLeft() == 0 && (otherDragon == null || otherDragon.getWallsLeft() == 0))
            {
                worstDifference = 0;
            }

            Wall bestWall = state.getBestWall();

            if (bestWall != null)
            {
                System.err.println("best Wall: " + bestWall.getColumn() + ", " + bestWall.getRow());
            }
            System.err.println("best difference average: " + bestDifferenceAverage);
            if (state.getWorstWall() != null)
            {
                System.err.println("worst Wall: " + state.getWorstWall().getColumn() + ", " + state.getWorstWall().getRow());
            }
            System.err.println("worst difference: " + worstDifference);

            Wall blockingWall = null;

            int pathDifference = bestDragon.getBestDistance() - myDragon.getBestDistance();
            int bestDragonDelta = 0;
            // if (playerCount == 3)
            // {
            //     bestDragonDelta = 1;
            // }

            if (playerCount == 2
                    && ((myDragon.getId() == 0 && round == 3) || (myDragon.getId() == 1 && round == 2))
                    && isJapaneseTactic(walls, myDragon, bestDragon))
            {
                offensiveWall = true;
//                Wall japaneseTacticsBestWall = getJapaneseTacticsBestWall(walls, myDragon, bestDragon);
//                if (japaneseTacticsBestWall != null)
//                {
//                    bestWall = japaneseTacticsBestWall;
//                }
            }
            else {
                if (myDragon.getBestDistance() > 2 && myDragon.getWallsLeft() > 0 && round > 5) {
                    if (worstDifference + bestDifferenceAverage >= -1)// || playerCount > 2)
                    {
                        if (bestDifferenceAverage > 0 && bestDifferenceAverage > pathDifference + bestDragonDelta) {
                            offensiveWall = true;
                        }
                    } else {
                        if (worstDifference < 0 && -worstDifference > pathDifference + bestDragonDelta + 1) {
                            blockingWall = findBlockingWall(walls, state.getWorstWall(), worstDifference, adiacent, myDragon, bestDragon, otherDragon);
                            if (blockingWall != null) {
                                defensiveWall = true;
                            } else {
                                if (bestDifferenceAverage > 0 && bestDifferenceAverage > pathDifference + 1) {
                                    offensiveWall = true;
                                }
                            }
                        }
                    }

                }
            }

            if (offensiveWall)
            {
                Wall wall = bestWall;
                long millis = System.currentTimeMillis() - timestamp;
                System.out.println(wall.getColumn() + " " + wall.getRow() + " " + wall.getOrientation().toString() + " " + millis);
            }
            else if (defensiveWall && blockingWall != null)
            {
                long millis = System.currentTimeMillis() - timestamp;
                System.out.println(blockingWall.getColumn() + " " + blockingWall.getRow() + " " + blockingWall.getOrientation().toString() + " " + millis);
            }
            else
            {
                long millis = System.currentTimeMillis() - timestamp;
                Point bestNeighbour = myDragon.getBestNeighbour();
                if (myDragon.getBestDistance() > 2)
                {
                    bestNeighbour = findBestMove(walls, adiacent, myDragon, bestDragon, otherDragon);
                }
                System.out.println(getDirection(myDragon.getPosition(), bestNeighbour) + " " + millis); // action: LEFT, RIGHT, UP, DOWN or "putX putY putOrientation" to place a wall
            }


    }

    private static void calculateShortestPath(int[][] adiacent, Dragon dragon) {
        AStarManhattan aStar = new AStarManhattan();

        List<Point> shortestPath = aStar.getShortestPath(adiacent, dragon.getPosition(), dragon.getDestinationColumn(), dragon.getDestinationRow());

        if (shortestPath != null && shortestPath.size() > 1) {
            dragon.setBestNeighbour(shortestPath.get(1));
            dragon.setBestPath(shortestPath);
            dragon.setBestDistance(shortestPath.size());
        }
        else
        {
            dragon.setBestNeighbour(null);
            dragon.setBestPath(null);
            dragon.setBestDistance(100);
        }
//        System.out.println(shortestPath);
    }

    private static boolean isJapaneseTactic(List<Wall> walls, Dragon myDragon, Dragon otherDragon)
    {
        if (myDragon.getId() == 0 && myDragon.getPosition().getRow() == otherDragon.getPosition().getRow()
                && (myDragon.getPosition().getRow() == 0 || myDragon.getPosition().getRow() == 8))
        {
            return false;
        }
        else if (walls.size() == 2 && walls.get(0).getOrientation() == Orientation.H && walls.get(1).getOrientation() == Orientation.H)
        {
            return true;
        }
        return false;
    }

//    private static Wall getJapaneseTacticsBestWall(List<Wall> walls, Dragon myDragon, Dragon otherDragon)
//    {
//        if (otherDragon.getPosition().getRow() < 2 || otherDragon.getPosition().getRow() > 6)
//        {
//            return null;
//        }
//        else
//        {
//            if (myDragon.getId() == 0)
//            {
//                return new Wall(4, walls.get(0).getRow() - 1, Orientation.V);
//            }
//            else
//            {
//                return new Wall(5, walls.get(0).getRow() - 1, Orientation.V);
//            }
//        }
//    }

    private static List<Wall> getCollisions(Wall wall)
    {
        List<Wall> collisionWalls = new ArrayList<Wall>();
        if (wall.getOrientation() == Orientation.H)
        {
            if (wall.getColumn() > 0)
            {
                collisionWalls.add(new Wall(wall.getColumn() - 1, wall.getRow(), Orientation.H));
            }
            if (wall.getColumn() < 7)
            {
                collisionWalls.add(new Wall(wall.getColumn() + 1, wall.getRow(), Orientation.H));
            }
            if (wall.getColumn() < 8 && wall.getRow() > 0)
            {
                collisionWalls.add(new Wall(wall.getColumn() + 1, wall.getRow() - 1, Orientation.V));
            }
        }
        else
        {
            if (wall.getRow() > 0)
            {
                collisionWalls.add(new Wall(wall.getColumn(), wall.getRow() - 1, Orientation.V));
            }
            if (wall.getRow() < 7)
            {
                collisionWalls.add(new Wall(wall.getColumn(), wall.getRow() + 1, Orientation.V));
            }
            if (wall.getColumn() > 0 && wall.getRow() < 8)
            {
                collisionWalls.add(new Wall(wall.getColumn() - 1, wall.getRow() + 1, Orientation.H));
            }
        }

        return collisionWalls;

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

    private static Point findBestMove(List<Wall> alreadyExistingWalls, int[][] adiacent, Dragon myDragon, Dragon bestDragon, Dragon otherDragon)
    {
        Dragon myDragonCopy = new Dragon(myDragon.getId(), myDragon.getPosition());
        myDragonCopy.setDestinationColumn(myDragon.getDestinationColumn());
        myDragonCopy.setDestinationRow(myDragon.getDestinationRow());

        AStarNode node = new AStarNode(myDragonCopy.getPosition(), 9, 9);
        List<AStarNode> neighbours = node.getNotVisitedNeighbours(new HashMap<Point, AStarNode>(), adiacent);

        double bestWorstDifference = -1000;
        double bestDistance = 100;
        Point bestMove = null;

        for (AStarNode neighbour : neighbours)
        {
            myDragonCopy.setPosition(neighbour.getPosition());
            calculateShortestPath(adiacent, myDragonCopy);
            GameState gameState = getGameState(alreadyExistingWalls, adiacent, myDragon, bestDragon, otherDragon, 1);

            double worstWallDistance = gameState.getWorstDifference() - myDragonCopy.getBestDistance();
            if (worstWallDistance > bestWorstDifference)
            {
                bestDistance = myDragonCopy.getBestDistance();
                bestWorstDifference = worstWallDistance;
                bestMove = neighbour.getPosition();
            }
            else if (worstWallDistance == bestWorstDifference && bestDistance > myDragonCopy.getBestDistance())
            {
                bestDistance = myDragonCopy.getBestDistance();
                bestWorstDifference = worstWallDistance;
                bestMove = neighbour.getPosition();
            }
        }

        return bestMove;
    }

    private static Wall findBlockingWall(List<Wall> alreadyExistingWalls, Wall worstWall, int worstDifference, int[][] adiacent, Dragon myDragon, Dragon bestDragon, Dragon otherDragon)
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
        walls.add(worstWall);
        addWall(adiacent, worstWall);

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

                    if (myDragonCopy.getBestDistance() >= 100)
                    {
                        walls.remove(worstWall);
                        removeWall(adiacent, worstWall);

                        calculateShortestPath(adiacent, myDragonCopy);
                        calculateShortestPath(adiacent, bestDragonCopy);
                        if (otherDragonCopy != null) {
                            calculateShortestPath(adiacent, otherDragonCopy);
                        }

                        if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
                        {
                            removeWall(adiacent, wall);
                            return wall;
                        }

                        walls.add(worstWall);
                        addWall(adiacent, worstWall);
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

                    if (myDragonCopy.getBestDistance() >= 100)
                    {
                        walls.remove(worstWall);
                        removeWall(adiacent, worstWall);

                        calculateShortestPath(adiacent, myDragonCopy);
                        calculateShortestPath(adiacent, bestDragonCopy);
                        if (otherDragonCopy != null) {
                            calculateShortestPath(adiacent, otherDragonCopy);
                        }

                        if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
                        {
                            removeWall(adiacent, wall);
                            return wall;
                        }

                        walls.add(worstWall);
                        addWall(adiacent, worstWall);
                    }

                    walls.remove(wall);
                    removeWall(adiacent, wall);
                }
            }
        }

        walls.remove(worstWall);
        removeWall(adiacent, worstWall);

        List<Wall> collisionWalls = getCollisions(worstWall);

        Wall bestWall = null;
        double bestDifference = -200;

        for (Wall wall : collisionWalls)
        {
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
                double difference = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (differenceForBestDragon) + (double)(differenceForOtherDragon) / 1000;

                if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
                {
                    if (difference > bestDifference)
                    {
                        bestDifference = difference;
                        bestWall = wall;
                    }
                }

                walls.remove(wall);
                removeWall(adiacent, wall);
            }
        }

        if (bestDifference > worstDifference + 1)
        {
            return bestWall;
        } else
        {
            return null;
        }
    }

    private static GameState getGameState(List<Wall> alreadyExistingWalls, int[][] adiacent, Dragon myDragon, Dragon bestDragon, Dragon otherDragon, int levelsDeep)
    {
        levelsDeep--;
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

        double bestDifference = -200;
        double worstDifference = 200;
        int worstCount = 0;
        double bestDifferenceForCurrentLevel = -200;

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
                    double differenceForCurrentLevel = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (differenceForBestDragon) + (double)(differenceForOtherDragon) / 1000 + (double)(getProximity(wall, bestDragonCopy.getPosition(), bestDragonCopy.getBestPath()))/100000;

                    double difference;

                    if (levelsDeep > 0 && (int)differenceForCurrentLevel > 0)
                    {
                        difference = differenceForCurrentLevel + getGameState(walls, adiacent, myDragonCopy, bestDragonCopy, otherDragonCopy, levelsDeep).getBestDifference();
                    }
                    else
                    {
                        difference = differenceForCurrentLevel;
                    }

                    if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
                    {
                        if (difference > bestDifference)
                        {
                            bestDifference = difference;
                            bestDifferenceForCurrentLevel = differenceForCurrentLevel;
                            bestWall = wall;
                        }
                        else if (difference == bestDifference && differenceForCurrentLevel > bestDifferenceForCurrentLevel)
                        {
                            bestDifference = difference;
                            bestDifferenceForCurrentLevel = differenceForCurrentLevel;
                            bestWall = wall;
                        }

                        if ((int)differenceForCurrentLevel < (int)worstDifference)
                        {
                            worstCount = 1;
                            worstDifference = differenceForCurrentLevel;
                            worstWall = wall;
                        }
                        else if ((int)differenceForCurrentLevel == (int)worstDifference)
                        {
                            worstCount++;
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
                    double differenceForCurrentLevel = (myDragon.getBestDistance() - myDragonCopy.getBestDistance()) + (differenceForBestDragon) + (double)(differenceForOtherDragon) / 1000 + (double)(getProximity(wall, bestDragonCopy.getPosition(), bestDragonCopy.getBestPath()))/100000;

                    double difference;

                    if (levelsDeep > 0 && (int)differenceForCurrentLevel > 0)
                    {
                        difference = differenceForCurrentLevel + getGameState(walls, adiacent, myDragonCopy, bestDragonCopy, otherDragonCopy, levelsDeep).getBestDifference();
                    }
                    else
                    {
                        difference = differenceForCurrentLevel;
                    }

                    if (myDragonCopy.getBestDistance() < 100 && bestDragonCopy.getBestDistance() < 100 && (otherDragonCopy == null || otherDragonCopy.getBestDistance() < 100))
                    {
                        if (difference > bestDifference)
                        {
                            bestDifference = difference;
                            bestDifferenceForCurrentLevel = differenceForCurrentLevel;
                            bestWall = wall;
                        }
                        else if (difference == bestDifference && differenceForCurrentLevel > bestDifferenceForCurrentLevel)
                        {
                            bestDifference = difference;
                            bestDifferenceForCurrentLevel = differenceForCurrentLevel;
                            bestWall = wall;
                        }

                        if ((int)differenceForCurrentLevel < (int)worstDifference)
                        {
                            worstCount = 1;
                            worstDifference = differenceForCurrentLevel;
                            worstWall = wall;
                        }
                        else if ((int)differenceForCurrentLevel == (int)worstDifference)
                        {
                            worstCount++;
                        }
                    }

                    walls.remove(wall);
                    removeWall(adiacent, wall);
                }
            }
        }

        GameState state = new GameState();

        state.setBestWall(bestWall);
        state.setBestDifference(bestDifference);
        state.setBestDifferenceForCurrentLevel(bestDifferenceForCurrentLevel);
        state.setWorstWall(worstWall);
        // if (worstCount <= 1)
        // {
        state.setWorstDifference(worstDifference);
        // }
        // else
        // {
        //     state.setWorstDifference(0);
        // }

        return state;
    }

    private static int getProximity(Wall wall, Point position, List<Point> path)
    {
        if (path != null) {
            Point point1 = new Point(wall.getColumn(), wall.getRow() - 1);
            Point point2 = new Point(wall.getColumn(), wall.getRow());
            Point point3 = new Point(wall.getColumn() + 1, wall.getRow() - 1);
            Point point4 = new Point(wall.getColumn() + 1, wall.getRow());
            if (wall.getOrientation() == Orientation.V) {
                point1 = new Point(wall.getColumn() - 1, wall.getRow());
                point2 = new Point(wall.getColumn(), wall.getRow());
                point3 = new Point(wall.getColumn() - 1, wall.getRow() + 1);
                point4 = new Point(wall.getColumn(), wall.getRow() + 1);

            }
            for (int i = 0; i < path.size(); i++) {
                if (path.get(i).equals(point1) || path.get(i).equals(point2) || path.get(i).equals(point3) || path.get(i).equals(point4)) {
                    return 99 - i;
                }
            }
        }
        return 19 - (Math.abs(wall.getColumn() - position.getColumn()) + Math.abs(wall.getRow() - position.getRow()));
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
    private List<Point> bestPath;

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

    public List<Point> getBestPath() {
        return bestPath;
    }

    public void setBestPath(List<Point> bestPath) {
        this.bestPath = bestPath;
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

    public int getValidNeighboursCount(int[][] adiacent)
    {
        int count = 0;

        if (isValid)
        {
            int positionIndex = getAdiacentIndex(this.position);
            Point point;
            if (this.position.getRow() > 0)
            {
                point = new Point(this.position.getColumn(), this.position.getRow() - 1);
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0)
                {
                    count++;
                }
            }
            if (this.position.getRow() + 1 < this.rowSize)
            {
                point = new Point(this.position.getColumn(), this.position.getRow() + 1);
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0)
                {
                    count++;
                }
            }
            if (this.position.getColumn() > 0)
            {
                point = new Point(this.position.getColumn() - 1, this.position.getRow());
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0)
                {
                    count++;
                }
            }
            if (this.position.getColumn() + 1 < this.columnSize)
            {
                point = new Point(this.position.getColumn() + 1, this.position.getRow());
                int pointIndex = getAdiacentIndex(point);
                if (adiacent[positionIndex][pointIndex] == 0)
                {
                    count++;
                }
            }
        }

        return count;
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

    protected static final int UNIT = 10;

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
                int tentativeScore = currentNode.getScore() + UNIT + (4 - node.getValidNeighboursCount(adiacent));

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
            return (Math.abs(startPoint.getRow() - destinationRow) * UNIT);
        }
        else
        {
            return (Math.abs(startPoint.getColumn() - destinationColumn) * UNIT);
        }
    }
}

class GameState
{
    private Wall bestWall;
    private Wall worstWall;
    private double bestDifference;
    private double bestDifferenceForCurrentLevel;
    private double worstDifference;

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

    public double getBestDifference() {
        return bestDifference;
    }

    public void setBestDifference(double bestDifference) {
        this.bestDifference = bestDifference;
    }

    public double getWorstDifference() {
        return worstDifference;
    }

    public void setWorstDifference(double worstDifference) {
        this.worstDifference = worstDifference;
    }

    public double getBestDifferenceForCurrentLevel() {
        return bestDifferenceForCurrentLevel;
    }

    public void setBestDifferenceForCurrentLevel(double bestDifferenceForCurrentLevel) {
        this.bestDifferenceForCurrentLevel = bestDifferenceForCurrentLevel;
    }
}
