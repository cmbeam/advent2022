package org.cmbeam.advent2022;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 {
    public static void main(String[] args) {
        int total = 0;

        Directory rootDirectory = new Directory();
        rootDirectory.name="root";
        //pointer to current directory level
        Directory currentDirectory = rootDirectory;

        try {
            File myObj = new File("day7/src/main/resources/day7input.txt");
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.substring(0,4).equals("$ cd")){
                    System.out.println("Change directory");
                    if(line.split(" ")[2].equals("/")){
                        System.out.println("Go back to root");
                        currentDirectory = rootDirectory;
                    }
                    else if(line.split(" ")[2].equals("..")){
                        System.out.println("Back to parent");
                        currentDirectory=currentDirectory.parent;
                    }
                    else{
                        System.out.println("Go to sub directory");
                        if(currentDirectory.directoryExist(line.split(" ")[2])){
                            currentDirectory=currentDirectory.get(line.split(" ")[2]);
                        }
                        else {
                            Directory subDirectory = new Directory();
                            subDirectory.name=line.split(" ")[2];
                            currentDirectory.addDirectory(subDirectory);
                            currentDirectory = subDirectory;

                        }
                    }
                }
                if(Character.isDigit(line.charAt(0))){
                    System.out.println("File");
                    currentDirectory.addFile(line.split(" ")[1], Integer.parseInt(line.split(" ")[0]));
                    //currentDirectory.fileSize = currentDirectory.fileSize + Integer.parseInt(line.split(" ")[0]);
                }
                if(line.split(" ")[0].equals("dir")){
                    System.out.println("Directory");
                }


            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        rootDirectory.contents();
        rootDirectory.representation(0);
        adder();
        deleter();
    }

    public static class Directory{
        private String name;
        private Directory parent;
        private int fileSize;
        private Map<String, Integer> files = new HashMap<>();
        private List<Directory> directories = new ArrayList<>();

        public void addDirectory(Directory directory){
            directory.parent = this;
            directories.add(directory);
        }
        
        public void addFile(String name, Integer size){
            if(!files.containsKey(name)){
                files.put(name, size);
            }
        }

        public void goToParent(){

        }

        public boolean directoryExist(String name){
            for (Directory dir:directories
                 ) {
                if(dir.name.equals(name))
                    return true;
            }
            return false;
        }

        public Directory get(String name){
            for (Directory dir:directories
            ) {
                if(dir.name.equals(name))
                    return dir;
            }
            return null;
        }

        public void contents(){
            System.out.println(getFileSize());
            for(Directory dir:directories){
               dir.contents();
            }
        }

        public void representation(int level){
            String padder = "";
            for (int i = 0; i < level  ; i++) {
                padder = padder + " ";
            }
            System.out.println(padder+getFileSize()+" Directory: " + name );
            final String pad = padder;
            files.forEach((name, value) -> System.out.println(pad + " File: " + name + " Size: " + value));
            for(Directory dir:directories){
                dir.representation(level+1);
            }
        }

        public int getFileSize(){
            AtomicInteger size = new AtomicInteger();
            files.forEach((name, value) -> size.addAndGet(value));
            for (Directory dir:directories
                 ) {
                size.addAndGet(dir.getFileSize());
            }
//           if(size.get() <= 100000)
                return size.get();
//           else return 0;
        }
    }

    public static void adder(){
        int total = 0;
        try {
            File myObj = new File("day7/src/main/resources/tally.txt");
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                int size = Integer.parseInt(myReader.nextLine());
                if(size <= 100000)
                    total += size;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Total: " + total);
    }

    public static void deleter(){
        int currentSize  = 42586708;
        int currentFree  = 70000000  - currentSize;
        int needToDelete = 30000000 - currentFree;

        int deleteSize = currentSize;

        System.out.println("Need to delete: " + needToDelete);
        try {
            File myObj = new File("day7/src/main/resources/tally.txt");
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                int size = Integer.parseInt(myReader.nextLine());
                if(size >= needToDelete && size <= deleteSize)
                    deleteSize = size;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Delete size: " + deleteSize);
    }
}