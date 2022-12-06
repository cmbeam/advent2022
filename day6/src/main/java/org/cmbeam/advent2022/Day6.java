package org.cmbeam.advent2022;


import java.io.File;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        try {
            File myObj = new File("day6/src/main/resources/day6input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                for (int i = 0; i < entry.length() - 3; i++) {
                    String potentialKey = entry.substring(i, i + 4);
//                    System.out.println(potentialKey);
                    if(checkUnique(potentialKey)){
                        System.out.println("Key at: " + (i + 4));
                        break;
                    }
                }

                for (int i = 0; i < entry.length() - 13; i++) {
                    String potentialKey = entry.substring(i, i + 14);
//                    System.out.println(potentialKey);
                    if(checkUnique(potentialKey)){
                        System.out.println("Message at: " + (i + 14));
                        break;
                    }
                }
            }

            }
        catch(Exception e){
            System.out.println();
        }
    }

    public static boolean checkUnique(String pKey){
        for (int i = 0; i < pKey.length() ; i++) {
            for (int j = 0; j < pKey.length() ; j++) {
                if(i != j && pKey.charAt(i) == pKey.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
