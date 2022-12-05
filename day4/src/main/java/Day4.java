import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        int fullTotal = 0;
        int partialTotal = 0;

        try {
            File myObj = new File("day4/src/main/resources/day4input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String entry = myReader.next();
                //System.out.println(entry);
                String[] assignments = entry.split(",");
                String[] assignment1 = assignments[0].split("-");
                String[] assignment2 = assignments[1].split("-");
                int x1 = Integer.parseInt(assignment1[0]);
                int x2 = Integer.parseInt(assignment2[0]);
                int y1 = Integer.parseInt(assignment1[1]);
                int y2 = Integer.parseInt(assignment2[1]);

                if((x1 >= x2 && y1 <= y2) || (x2 >= x1 && y2 <= y1) ){
                  //  System.out.println("Full MATCH");
                    fullTotal++;
                }

                boolean contains = false;
                List<Integer> list1 = new ArrayList<>();
                List<Integer> list2 = new ArrayList<>();
                for (int i = x1; i <= y1; i++) {
                    list1.add(i);
                }
                for (int i = x2; i <= y2; i++) {
                    list2.add(i);
                }
                for (Integer one:list1
                     ) {
                    if (list2.contains(one)) {
                        contains = true;
                        break;
                    }
                }
                if(contains){
                    //System.out.println("Partial Match");
                    partialTotal++;
                }

            }
        }
        catch (Exception e){
            System.out.println("Error "+ e.getMessage());
        }
        System.out.println("Part 1: " + fullTotal);
        System.out.println("Part 2: " + partialTotal);
    }
}
