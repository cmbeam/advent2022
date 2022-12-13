package org.cmbeam.advent2022;

import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        List<Node> packets = new ArrayList<>();
        try {
            File myObj = new File("day13/src/main/resources/day13sample.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String entry = myReader.nextLine();
                if(!entry.isBlank()) {
                    //System.out.println(entry);
                    Node node = parsePacket(new StringReader(entry));
                    packets.add(node);
                }
            }

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        List<Integer> inOrderList = new ArrayList<>();
        for (int i = 0; i < packets.size(); i = i + 2) {
            Node nodeL = packets.get(i);
            Node nodeR = packets.get(i+1);
            System.out.println("Node L: " + nodeL.toString());
            System.out.println("Node R: " + nodeR.toString());
            if(compare(nodeL, nodeR) >= 0){
                inOrderList.add(i/2 + 1);
            }
        }

        System.out.println(inOrderList);
        int sum = 0;
        for (Integer indice:inOrderList
             ) {
            sum += indice;
        }
        System.out.println("Sum: " + sum);

    }

    public static Node parsePacket(Reader in) throws IOException {
        PacketScanner  scanner = new PacketScanner(in);
        scanner.nextToken();
        return parse(scanner);
    }

    private static Node parse(PacketScanner scanner) throws IOException {
        switch (scanner.getToken()) {
            case NUMBER:
                int value = scanner.getNumber();
                scanner.nextToken();
                return new NumberNode(value);
            case LPAR:
                scanner.nextToken();
                List<Node> nodes = parseList(scanner);
                if (scanner.getToken() != PacketScanner.Token.RPAR) {
                    throw new RuntimeException(") expected");
                }
                scanner.nextToken();
                return new ListNode(nodes);
            default:
                throw new RuntimeException("Number or ( expected");
        }
    }

    private static List<Node> parseList(PacketScanner scanner) throws IOException {
        List<Node> nodes = new ArrayList<>();
        if (scanner.getToken() != PacketScanner.Token.RPAR) {
            nodes.add(parse(scanner));
            while (scanner.getToken() == PacketScanner.Token.COMMA) {
                scanner.nextToken();
                nodes.add(parse(scanner));
            }
        }
        return nodes;
    }

    public static class PacketScanner {
        private final Reader in;
        private int c;
        private Token token;
        private int number;

        public static enum Token {LPAR,RPAR,NUMBER,COMMA,EOF};

        public PacketScanner(Reader in) throws IOException {
            this.in = in;
            c = in.read();
        }

        public Token getToken() {
            return token;
        }

        public int getNumber() {
            return number;
        }

        public Token nextToken() throws IOException {
            while (c == ' ') {
                c = in.read();
            }
            if (c < 0) {
                return token = Token.EOF;
            }
            if (c >= '0' && c <= '9') {
                number = c - '0';
                c = in.read();
                while (c >= '0' && c <= '9') {
                    number = 10*number + (c-'0');
                    c = in.read();
                }
                return token = Token.NUMBER;
            }
            switch (c) {
                case '[':
                    c = in.read();
                    return token = Token.LPAR;
                case ']':
                    c = in.read();
                    return token = Token.RPAR;
                case ',':
                    c = in.read();
                    return token = Token.COMMA;
                default:
                    throw new RuntimeException("Unknown character " + c);
            }
        }

    }
    public static abstract class Node {
    }

    public static class NumberNode extends Node {
        private final int number;

        public NumberNode(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return Integer.toString(number);
        }


    }

    public static class ListNode extends Node {
        private final List<Node> list = new ArrayList<>();

        public ListNode(Collection<Node> nodes) {
            list.addAll(nodes);
        }

        @Override
        public String toString() {
            StringBuilder buf = new StringBuilder();
            buf.append('(');
            boolean first = true;
            for (Node node: list) {
                if (first) {
                    first = false;
                } else {
                    buf.append(',');
                }
                buf.append(node);
            }
            buf.append(')');
            return buf.toString();
        }

    }
    public static int compare(Node l, Node r){
        return 0;
    }
}