package org.cmbeam.advent2022;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Day8
{
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> rows = new ArrayList<>();
        ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

        try {
            File myObj = new File("day8/src/main/resources/day8input.txt");
            Scanner myReader = new Scanner(myObj);

            int rowNumber = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < line.length() ; i++) {
                    row.add(Integer.parseInt(line.substring(i, i+1)));
                }
                rows.add(row);

            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        int columnNumber = rows.get(0).size();

        for (int i = 0; i < columnNumber ; i++) {
            columns.add(new ArrayList<>());
        }

        for (ArrayList<Integer> row:rows
             ) {
            for (int i = 0; i < row.size() ; i++) {
                columns.get(i).add(row.get(i));
            }
        }

        int[][] trees = new int[columns.size()][rows.size()];
        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j < rows.size(); j++) {
                trees[i][j] = 0;
            }
        }
        int[][] scenic = new int[columns.size()][rows.size()];
        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j < rows.size(); j++) {
                scenic[i][j] = 1;
            }
        }

        int rowCounter = 0;
        for (ArrayList<Integer> row: rows
        ) {
            int tall = -1;
            for (int i = 0; i < row.size() ; i++) {

                if(row.get(i) > tall){
                    tall = row.get(i);
                    trees[rowCounter][i] = 1;

                }
                int vizCount = 0;
                int scenicTallest = -1;
                for (int j = i - 1; j >= 0 ; j--) {
                    //System.out.println("DOWN: i: " + row.get(i) + " j: " + row.get(j));
                    if(row.get(i) > row.get(j)){
                        vizCount++;
                    }
                    else{
                            vizCount++;
                        break;
                    }
                }
                System.out.println(i+ "VIZ: " + vizCount);
                scenic[rowCounter][i] = scenic[rowCounter][i] * vizCount;

//                if(row.get(i) > lastTall){
//                    vizCount++;
//                    lastTall = row.get(i);
//                }
//                else {
//                    vizCount = 1;
////                    lastTall = -1;
//                }

            }
            tall = -1;
            for (int i = row.size() - 1; i >= 0 ; i--) {
                if(row.get(i) > tall){
                    tall = row.get(i);
                    trees[rowCounter][i] = 1;

                }
                int vizCount = 0;
                int scenicTallest = -1;
                for (int j = i + 1; j < row.size() ; j++) {
                   // System.out.println("UP: i: " + row.get(i) + " j: " + row.get(j));
                    if(row.get(i) > row.get(j)){
                        vizCount++;
                    }
                    else{
                            vizCount++;
                        break;
                    }
                }
                System.out.println(i+"VIZ: " + vizCount);
                scenic[rowCounter][i] = scenic[rowCounter][i] * vizCount;

            }
            rowCounter++;
            System.out.println(row.toString());
        }

        System.out.println();
        System.out.println();

        int columnCounter = 0;
        for (ArrayList<Integer> column: columns
             ) {
            int tall = -1;

            for (int i = 0; i < column.size() ; i++) {
                if(column.get(i) > tall){
                    tall = column.get(i);
                    trees[i][columnCounter] = 1;

                }
                int vizCount = 0;
                int scenicTallest = -1;
                for (int j = i - 1; j >= 0 ; j--) {
                    if(column.get(i) > column.get(j)){
                        vizCount++;
                    }
                    else{
                            vizCount++;
                        break;
                    }
                }
                System.out.println(i+"VIZ: " + vizCount);
                scenic[i][columnCounter] = scenic[i][columnCounter] * vizCount;

            }
            tall = -1;
            for (int i = column.size() - 1; i >= 0 ; i--) {

                if(column.get(i) > tall){
                    tall = column.get(i);
                    trees[i][columnCounter] = 1;

                }
                int vizCount = 0;
                int scenicTallest = -1;
                for (int j = i + 1; j < column.size() ; j++) {
                    if(column.get(i) > column.get(j)){
                        vizCount++;
                    }
                    else{
                        vizCount++;
                        break;
                    }
                }
                System.out.println(i+"VIZ: " + vizCount);
                scenic[i][columnCounter] = scenic[i][columnCounter] * vizCount;

            }
            columnCounter++;
            System.out.println(column.toString());
        }

        System.out.println();
        System.out.println();

        int count = 0;
        for (int i = 0; i < trees.length ; i++) {
            for (int j = 0; j < trees.length ; j++) {
                System.out.print(trees[i][j]);
                if(trees[i][j] == 1)
                    count++;

            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Total count: " + count);

        System.out.println();
        int high = 0;
        for (int i = 0; i < scenic.length ; i++) {
            for (int j = 0; j < scenic.length ; j++) {
                System.out.print(scenic[i][j] + " ");
                if(scenic[i][j] > high)
                    high = scenic[i][j];
                if(scenic[i][j] == 1)
                    count++;

            }
            System.out.println();

        }
        System.out.println("High score: " + high);
    }
}
