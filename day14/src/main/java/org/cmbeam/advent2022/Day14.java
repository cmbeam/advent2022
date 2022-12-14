package org.cmbeam.advent2022;

import java.io.File;
import java.util.Scanner;

public class Day14 {
    
   public static void main(String[] args) {
        int xLength = 750;
        int yLength = 165; //1010;
        char[][] grid = new char[xLength][yLength];
        for (int i = 0; i < xLength ; i++) {
            for (int j = 0; j < yLength; j++) {
                grid[i][j] = '.';
            }

        }
        grid[500][0] = '+';
        int maxy = 0;
        try {
            File myObj = new File("input14.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                String[] points = entry.split(" -> ");
                String point1 = points[0];
                for (int i = 1; i < points.length ; i++) {
                    String point2 = points[i];
                    int x1 = Integer.parseInt(point1.split(",")[0]);
                    int x2 = Integer.parseInt(point2.split(",")[0]);
                    int y1 = Integer.parseInt(point1.split(",")[1]);
                    int y2 = Integer.parseInt(point2.split(",")[1]);
                    if(x1 > x2) {
                        int temp = x1;
                        x1 = x2;
                        x2 = temp;
                    }
                    if(y1 > y2) {
                        int temp = y1;
                        y1 = y2;
                        y2 = temp;
                    }

                    for (int j = x1; j <= x2 ; j++) {
                        for (int k = y1; k <= y2; k++) {
                            grid[j][k] = '#';
                        }
                    }
                    if(y2 > maxy)
                        maxy = y2;
                    point1 = points[i];
                }
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


//        for (int i = 0; i < xLength ; i++) {
//            for (int j = 0; j < yLength; j++) {
//                System.out.print(grid[j][i]);
//            }
//            System.out.println();
//        }
        System.out.println(maxy);
        boolean done = false;
        int grains = 0;

        while(!done){
            int x = 500;
            int y = 0;
            boolean stopped = false;
            while(y < yLength - 1 && !stopped){
                if (y == maxy + 1 ){
                    grid[x][y] = 'O';
                    grains++;
                    stopped = true;
                }
                else if(grid[x][y+1] == '.'){
                    y++;
                }
                else if(grid[x-1][y+1] == '.'){
                    x--;
                    y++;
                }
                else if(grid[x+1][y+1] == '.'){
                    x++;
                    y++;
                }
                else {
                    grid[x][y] = 'O';
                    grains++;
                    stopped = true;
                }
            }
//            if(y >= yLength - 1 )
//                done = true;
            if((grains == 60000) || (x == 500 && y == 0))
                done = true;

        }
        for (int i = 0; i < yLength ; i++) {
            for (int j = 250; j < xLength; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
        System.out.println("grains: " + grains);

    }
}
