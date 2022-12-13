package org.cmbeam.advent2022;

import java.io.File;
import java.util.Scanner;

public class DayX {
    public static void main(String[] args) {
        try {
            File myObj = new File("dayX/src/main/resources/dayXinput.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                System.out.println(entry);
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}