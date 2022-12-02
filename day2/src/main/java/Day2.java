import java.io.File;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {

        int part1Score = 0;
        int part2Score = 0;
        try {
            File myObj = new File("/Users/cbeam/GIT/advent2022/day2/src/main/resources/day2input.txt");
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                System.out.println(entry);
                switch (entry) {
                    case "A X" -> {
                        part1Score = part1Score + 4;
                        part2Score = part2Score + 3;
                    }
                    case "A Y" -> {
                        part1Score = part1Score + 8;
                        part2Score = part2Score + 4;
                    }
                    case "A Z" -> {
                        part1Score = part1Score + 3;
                        part2Score = part2Score + 8;
                    }
                    case "B X" -> {
                        part1Score = part1Score + 1;
                        part2Score = part2Score + 1;
                    }
                    case "B Y" -> {
                        part1Score = part1Score + 5;
                        part2Score = part2Score + 5;
                    }
                    case "B Z" -> {
                        part1Score = part1Score + 9;
                        part2Score = part2Score + 9;
                    }
                    case "C X" -> {
                        part1Score = part1Score + 7;
                        part2Score = part2Score + 2;
                    }
                    case "C Y" -> {
                        part1Score = part1Score + 2;
                        part2Score = part2Score + 6;
                    }
                    case "C Z" -> {
                        part1Score = part1Score + 6;
                        part2Score = part2Score + 7;
                    }
                }

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());

        }

        System.out.println("part1 Score: " + part1Score);
        System.out.println("part2 Score: " + part2Score);

    }
}
