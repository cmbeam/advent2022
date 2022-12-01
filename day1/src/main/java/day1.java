import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day1 {

    public static void main(String[] args) {

        ArrayList<Integer> inventory = new ArrayList<>();
        try {
            File myObj = new File("/Users/cbeam/GIT/advent2022/day1/src/main/resources/day1input.txt");
            Scanner myReader = new Scanner(myObj);

            int tally = 0;
            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                if(entry.equals("")){
                    inventory.add(tally);
                    tally = 0;
                }
                else{
                    tally = tally + Integer.parseInt(entry);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }
        inventory.sort(Collections.reverseOrder());
        System.out.println("Elf with the most: " + inventory.get(0));
        System.out.println("Total top 3 elves: " + (inventory.get(0) + inventory.get(1) + inventory.get(2)));
    }
}
