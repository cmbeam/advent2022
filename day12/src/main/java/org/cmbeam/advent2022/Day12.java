package org.cmbeam.advent2022;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Day12 {
    public static void main(String[] args) {

        char[][] grid = new char[81][41];
        Point startingPoint = new Point(0, 0, null);
        Point endPoint = new Point(0, 0, null);
        List<Point> startingPoints = new ArrayList<>();
        try {
            File myObj = new File("day12/src/main/resources/day12input.txt");
            Scanner myReader = new Scanner(myObj);

            int row = 40;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                for (int j = 0; j < 81; j++) {
                    if (line.charAt(j) == 'S') {
                        startingPoint.x = j;
                        startingPoint.y = row;
                    }
                    if (line.charAt(j) == 'E') {
                        endPoint.x = j;
                        endPoint.y = row;
                    }
                    if (line.charAt(j) == 'a') {
                        startingPoints.add(new Point(j,row,null));
                    }
                    grid[j][row] = line.charAt(j);
                }
                row--;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 40; i >= 0; i--) {
            for (int j = 0; j < 81; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();

        }
        System.out.println("Starting point: (" + startingPoint.x + "," + startingPoint.y + ")");
        System.out.println("End point: (" + endPoint.x + "," + endPoint.y + ")");

        Date time = new Date();
        List<Point> path = findPath(grid, startingPoint, endPoint);
        long timeTaken = new Date().getTime() - time.getTime();
        System.out.println(timeTaken+ " Answer part 1: " + path.size());

        System.out.println(startingPoints.size());
        int shortestDistance = 10000;
        int iteration = 1;
//        for (Point point: startingPoints
//             ) {
//            System.out.println("Path check " + iteration++);
//            List<Point> potentialPath = findPath(grid, point, endPoint);
//            if(potentialPath != null && potentialPath.size() < shortestDistance)
//                shortestDistance = potentialPath.size();
//
//        }
//        System.out.println("Shortest path: " + shortestDistance);
        List<Point> shortestAPath = findPathToClosestA(grid, endPoint);
        System.out.println("Shortest path: " + shortestAPath.size());
    }

    public static List<Point> findPath(char[][] grid, Point currentLocation, Point endPoint) {

        boolean done = false;
        List<Point> traveled = new ArrayList<>();
        traveled.add(currentLocation);

        while(!done) {
            List<Point> possiblePaths = new ArrayList<>();
            for (int i = 0; i < traveled.size(); ++i) {
                Point point = traveled.get(i);
                for (Point neighbor : findNeighbors(grid, point)) {
                    if (!traveled.contains(neighbor) && !possiblePaths.contains(neighbor)) {
                        possiblePaths.add(neighbor);
                    }
                }
            }

            for (Point point : possiblePaths) {
                traveled.add(point);
                if (endPoint.equals(point)) {
                    done = true;
                    break;
                }
            }
            if (!done && possiblePaths.isEmpty())
                return null;
        }

        List<Point> path = new ArrayList<>();
        Point point = traveled.get(traveled.size() - 1);
        while (point.parent != null) {
            path.add(0, point);
            point = point.parent;
        }
        return path;
    }

    public static List<Point> findPathToClosestA(char[][] grid, Point currentLocation) {

        boolean done = false;
        List<Point> traveled = new ArrayList<>();
        traveled.add(currentLocation);

        while(!done) {
            List<Point> possiblePaths = new ArrayList<>();
            for (int i = 0; i < traveled.size(); ++i) {
                Point point = traveled.get(i);
                for (Point neighbor : findNeighborsReverse(grid, point)) {
                    if (!traveled.contains(neighbor) && !possiblePaths.contains(neighbor)) {
                        possiblePaths.add(neighbor);
                    }
                }
            }

            for (Point point : possiblePaths) {
                traveled.add(point);
                if (grid[point.x][point.y] == 'a') {
                    done = true;
                    break;
                }
            }
            if (!done && possiblePaths.isEmpty())
                return null;
        }

        List<Point> path = new ArrayList<>();
        Point point = traveled.get(traveled.size() - 1);
        while (point.parent != null) {
            path.add(0, point);
            point = point.parent;
        }
        return path;
    }


    public static boolean isPassable(char[][] grid, Point point, Point nextPoint){
        if (nextPoint.x < 0 || nextPoint.x > grid.length - 1)
            return false;
        if (nextPoint.y < 0 || nextPoint.y > grid[0].length - 1)
            return false;
        int pointScore = (int)grid[point.x][point.y];
        if(pointScore == 83 )
            pointScore = 97;
        int nextScore = (int)grid[nextPoint.x][nextPoint.y];
        if(grid[nextPoint.x][nextPoint.y] == 'E' )
            nextScore = (int) 'z';

//        if(pointScore >= nextScore && pointScore >= nextScore - 1)
            if(nextScore - 1 <= pointScore)
            return true;
        else
            return false;

    }

    public static boolean isPassableReverse(char[][] grid, Point point, Point nextPoint){
        if (nextPoint.x < 0 || nextPoint.x > grid.length - 1)
            return false;
        if (nextPoint.y < 0 || nextPoint.y > grid[0].length - 1)
            return false;
        int pointScore = (int)grid[point.x][point.y];
        if(pointScore == 83 )
            pointScore = 97;
        int nextScore = (int)grid[nextPoint.x][nextPoint.y];
        if(grid[nextPoint.x][nextPoint.y] == 'E' )
            nextScore = (int) 'z';

//        if(pointScore >= nextScore && pointScore >= nextScore - 1)
        if(pointScore <= nextScore || pointScore == nextScore + 1)
            return true;
        else
            return false;

    }

    public static List<Point> findNeighbors(char[][] grid, Point point){
        List<Point> neighbors = new ArrayList<>();
        if (isPassable(grid, point, new Point(point.x, point.y + 1, point)))
            neighbors.add(new Point(point.x, point.y + 1, point));
        if (isPassable(grid, point,  new Point(point.x, point.y - 1, point)))
            neighbors.add(new Point(point.x, point.y - 1, point));
        if (isPassable(grid, point, new Point(point.x - 1, point.y, point)))
            neighbors.add(new Point(point.x - 1, point.y, point));
        if (isPassable(grid, point, new Point(point.x + 1, point.y, point)))
            neighbors.add(new Point(point.x + 1, point.y, point));
        return neighbors;
    }

    public static List<Point> findNeighborsReverse(char[][] grid, Point point){
        List<Point> neighbors = new ArrayList<>();
        if (isPassableReverse(grid, point, new Point(point.x, point.y + 1, point)))
            neighbors.add(new Point(point.x, point.y + 1, point));
        if (isPassableReverse(grid, point,  new Point(point.x, point.y - 1, point)))
            neighbors.add(new Point(point.x, point.y - 1, point));
        if (isPassableReverse(grid, point, new Point(point.x - 1, point.y, point)))
            neighbors.add(new Point(point.x - 1, point.y, point));
        if (isPassableReverse(grid, point, new Point(point.x + 1, point.y, point)))
            neighbors.add(new Point(point.x + 1, point.y, point));
        return neighbors;
    }
    public static class Point{
        int x;
        int y;
        Point parent;
        public Point(int x, int y, Point parent){
            this.x = x;
            this.y = y;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

    }
}