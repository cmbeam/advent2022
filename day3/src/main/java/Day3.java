import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        int total = 0;
        int badgeTotal = 0;
        try {
            File myObj = new File("/Users/cbeam/GIT/advent2022/day3/src/main/resources/day3input.txt");
            Scanner myReader = new Scanner(myObj);

            int groupCount = 1;
            String elf1="", elf2="", elf3 = "";
            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                String rucksack1 = entry.substring(0, entry.length()/2);
                String rucksack2 = entry.substring(entry.length()/2);
                System.out.println(rucksack1 + " " + rucksack2);
                System.out.println(getCommon(rucksack1, rucksack2));
                System.out.println(priority(getCommon(rucksack1,rucksack2)));
                total += priority(getCommon(rucksack1,rucksack2));
                if(groupCount == 3){
                    elf3 = entry;
                    System.out.println("Badge: "+getBadge(elf1, elf2, elf3));
                    badgeTotal += priority(getBadge(elf1,elf2,elf3));
                    groupCount = 1;
                }
                else if(groupCount == 2){
                    elf2 = entry;
                    groupCount = 3;
                }
                else{
                    elf1 = entry;
                    groupCount = 2;
                }
            }
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        System.out.println("Total: "+total);
        System.out.println("Badge Total: "+badgeTotal);
    }


    public static char getCommon(String c1, String c2) {
        CharacterIterator itr1
                = new StringCharacterIterator(c1);
        while (itr1.current() != CharacterIterator.DONE){
            CharacterIterator itr2
                    = new StringCharacterIterator(c2);
            while (itr2.current() != CharacterIterator.DONE){
                if(itr1.current() == itr2.current()) return itr1.current();
                itr2.next();
            }
            itr1.next();
        }
        return ' ';
    }

    public static char getBadge(String c1, String c2, String c3) {
        for (char alph = 'a'; alph <= 'z'; alph++){
            if (c1.contains("" + alph) && c2.contains("" + alph) && c3.contains("" + alph)) {
                return alph;
            }
    }
        for (char alph = 'A'; alph <= 'Z'; alph++){
            if (c1.contains("" + alph) && c2.contains("" + alph) && c3.contains("" + alph)) {
                return alph;
            }
        }
        return ' ';
    }

    public static int priority(char p){
        int ascii = p;
        if(Character.isLowerCase(p))
            return ascii - 96;
        else
            return ascii - 38;
    }
}
