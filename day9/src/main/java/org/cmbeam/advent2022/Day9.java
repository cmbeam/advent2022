package org.cmbeam.advent2022;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) {
        Coordinate hPosition = new Coordinate();
        Coordinate tPosition = new Coordinate();
        Set<String> tVisited = new HashSet<>();
        tVisited.add("0,0");
        try {
            File myObj = new File("day9/src/main/resources/day9input.txt");
            Scanner myReader = new Scanner(myObj);



            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                char direction = line.split(" ")[0].charAt(0);
                int distance = Integer.parseInt(line.split(" ")[1]);

                for (int i = 0; i < distance ; i++) {
                    move(hPosition, tPosition, direction);
                    tVisited.add(tPosition.x+","+tPosition.y);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(hPosition.x+","+ hPosition.y+" "+tPosition.x+","+ tPosition.y);
        for (String t:tVisited
             ) {
            System.out.println(t);
        }
        System.out.println("Count of visited spots: "+ tVisited.size());


    }

    public static void move(Coordinate h, Coordinate t, char direction){
        switch (direction){
            case 'R':
                if (t.x < h.x && t.y < h.y ) {
                    t.x = h.x;
                    t.y = h.y;
                }
                else if (t.x < h.x && t.y > h.y){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if(t.x < h.x)
                    t.x++;
                h.x += 1;
                break;
            case 'L':
                if (t.x > h.x && t.y > h.y ){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if (t.x > h.x && t.y < h.y){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if(t.x > h.x)
                    t.x--;
                h.x -= 1;
                break;
            case 'U':
                if (t.x < h.x && t.y < h.y ){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if (t.x > h.x && t.y < h.y){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if(t.y < h.y)
                    t.y++;
                h.y += 1;
                break;
            case 'D':
                if (t.x < h.x && t.y > h.y ){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if (t.x > h.x && t.y > h.y){
                    t.x = h.x;
                    t.y = h.y;
                }
                else if(t.y > h.y)
                    t.y--;
                h.y -= 1;
                break;
        }

    }

    public static class Coordinate{
        int x = 0;
        int y = 0;
    }
}