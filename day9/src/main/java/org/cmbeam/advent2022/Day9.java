package org.cmbeam.advent2022;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    public static void main(String[] args) {
        Coordinate hPosition = new Coordinate();
        Coordinate tPosition = new Coordinate();
        Coordinate position2 = new Coordinate();
        Coordinate position3 = new Coordinate();
        Coordinate position4 = new Coordinate();
        Coordinate position5 = new Coordinate();
        Coordinate position6 = new Coordinate();
        Coordinate position7 = new Coordinate();
        Coordinate position8 = new Coordinate();
        Coordinate position9 = new Coordinate();

        Set<String> tVisited = new HashSet<>();
        tVisited.add("0,0");
        Set<String> longTailVisited = new HashSet<>();
        longTailVisited.add("0,0");
        try {
            File myObj = new File("day9/src/main/resources/day9input.txt");
            Scanner myReader = new Scanner(myObj);



            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                char direction = line.split(" ")[0].charAt(0);
                int distance = Integer.parseInt(line.split(" ")[1]);

                System.out.println(line);
                for (int i = 0; i < distance ; i++) {
                    move(hPosition, tPosition, direction);
                    tVisited.add(tPosition.x+","+tPosition.y);

                    System.out.println("h: " + hPosition.x+","+hPosition.y);
                    System.out.println("t: " + tPosition.x+","+tPosition.y);
                    moveSimple(tPosition, position2);
                    System.out.println("2: " + position2.x+","+position2.y);
                    moveSimple(position2, position3);
                    System.out.println("3: " + position3.x+","+position3.y);
                    moveSimple(position3, position4);
                    System.out.println("4: " + position4.x+","+position4.y);
                    moveSimple(position4, position5);
                    System.out.println("5: " + position5.x+","+position5.y);
                    moveSimple(position5, position6);
                    System.out.println("6: " + position6.x+","+position6.y);
                    moveSimple(position6, position7);
                    System.out.println("7: " + position7.x+","+position7.y);
                    moveSimple(position7, position8);
                    System.out.println("8: " + position8.x+","+position8.y);
                    moveSimple(position8, position9);
                    System.out.println("9: " + position9.x+","+position9.y);
                    longTailVisited.add((position9.x+","+position9.y));
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
        System.out.println("Count of long tail visited spots: "+ longTailVisited.size());


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

    public static void moveSimple(Coordinate h, Coordinate t){
                if ((t.x <= h.x - 1 && t.y == h.y  - 2 ) || (t.x == h.x - 2 && t.y <= h.y  - 1 ))  {
                    t.x++;
                    t.y++;
                }
                else if (t.x == h.x - 2 && t.y == h.y) {
                    t.x++;
                }
                else if ((t.x <= h.x - 1 && t.y == h.y + 2 ) ||  (t.x == h.x - 2 && t.y >= h.y + 1 ))  {
                    t.x++;
                    t.y--;
                }
                else if (t.x == h.x && t.y == h.y - 2) {
                    t.y++;
                }
                else if ((t.x >= h.x + 1 && t.y == h.y + 2 ) || (t.x == h.x + 2 && t.y >= h.y + 1 )) {
                    t.x--;
                    t.y--;
                }
                else if (t.x == h.x + 2 && t.y ==  h.y ) {
                    t.x--;
                }
                else if ((t.x >= h.x + 1 && t.y == h.y - 2 ) || (t.x == h.x + 2 && t.y <= h.y - 1 )) {
                    t.x--;
                    t.y++;
                }
                else if (t.x == h.x && t.y == h.y + 2){
                    t.y--;
                }

    }

    public static class Coordinate{
        int x = 0;
        int y = 0;
    }
}