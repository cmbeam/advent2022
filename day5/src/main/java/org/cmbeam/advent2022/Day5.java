package org.cmbeam.advent2022;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Day5
{
    public static void main(String[] args) {

        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        Stack<Character> stack3 = new Stack<>();
        Stack<Character> stack4 = new Stack<>();
        Stack<Character> stack5 = new Stack<>();
        Stack<Character> stack6 = new Stack<>();
        Stack<Character> stack7 = new Stack<>();
        Stack<Character> stack8 = new Stack<>();
        Stack<Character> stack9 = new Stack<>();

        List<Stack<Character>> stacks = new ArrayList<>();

        try {
            File myObj = new File("day5/src/main/resources/day5input.txt");
            Scanner myReader = new Scanner(myObj);



            boolean part2 = false;
            while (myReader.hasNextLine() && !part2) {

                String line = myReader.nextLine();
                if(line.equals("")) part2 = true;
                else if(line.charAt(1) != '1') {
                    System.out.println("Line: " + line);
                    if(line.length() > 0)
                        stack1.push(line.charAt(1));
                    if(line.length() > 3)
                        stack2.push(line.charAt(5));
                    if(line.length() > 7)
                        stack3.push(line.charAt(9));
                    if(line.length() > 11)
                        stack4.push(line.charAt(13));
                    if(line.length() > 15)
                        stack5.push(line.charAt(17));
                    if(line.length() > 19)
                        stack6.push(line.charAt(21));
                    if(line.length() > 23)
                        stack7.push(line.charAt(25));
                    if(line.length() > 27)
                        stack8.push(line.charAt(29));
                    if(line.length() > 31)
                        stack9.push(line.charAt(33));

                }

            }

            stack1 = cleanUpStack(stack1);
            stack2 = cleanUpStack(stack2);
            stack3 = cleanUpStack(stack3);
            stack4 = cleanUpStack(stack4);
            stack5 = cleanUpStack(stack5);
            stack6 = cleanUpStack(stack6);
            stack7 = cleanUpStack(stack7);
            stack8 = cleanUpStack(stack8);
            stack9 = cleanUpStack(stack9);
            stacks.add(stack1);
            stacks.add(stack2);
            stacks.add(stack3);
            stacks.add(stack4);
            stacks.add(stack5);
            stacks.add(stack6);
            stacks.add(stack7);
            stacks.add(stack8);
            stacks.add(stack9);

            while (myReader.hasNextLine()){
                String line = myReader.nextLine();
                String[] instructions = line.split(" ");
                move9001(Integer.parseInt(instructions[1]), stacks.get(Integer.parseInt(instructions[3]) - 1), stacks.get(Integer.parseInt(instructions[5]) - 1));
                System.out.println("Part2: " + line);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        printStacks(stacks);



    }
    public static Stack<Character> cleanUpStack(Stack<Character> stack){
        Stack<Character> cleanStack = new Stack<>();
        while(!stack.empty()){
            char letter = stack.pop();
            if(letter != ' '){
                cleanStack.push(letter);
            }
        }
        return cleanStack;
    }

    public static void printStacks(List<Stack<Character>> stacks){
        for (Stack<Character> stack:stacks
             ) {
            System.out.println(stack.toString());
        }
    }

    public static void move(int quantity, Stack<Character> stackFrom, Stack<Character> stackTo){
        for (int i = 0; i < quantity; i++) {
            Character crate = stackFrom.pop();
            stackTo.push(crate);
        }
    }

    public static void move9001(int quantity, Stack<Character> stackFrom, Stack<Character> stackTo){
        Stack<Character> tempStack = new Stack<>();
        for (int i = 0; i < quantity; i++) {
            Character crate = stackFrom.pop();
            tempStack.push(crate);

        }
        while(!tempStack.empty()){
            Character crate = tempStack.pop();
            stackTo.push(crate);
        }
    }
}
