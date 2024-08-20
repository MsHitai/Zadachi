package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        Huffman huffman = new Huffman();
        huffman.run();
    }

    public void run() throws FileNotFoundException {
        Map<Character, Integer> letters = new HashMap<>();

        Scanner input = new Scanner(new File("input.txt"));
        String s = input.next();

        if (s.isEmpty()) {
            System.out.println("0 0");
            System.out.println(s + ": 0");
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            letters.put(s.charAt(i), letters.getOrDefault(s.charAt(i), 0) + 1);
        }

        Map<Character, Node> charNodes = new HashMap<>();

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getSum));

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            LeafNode node = new LeafNode(entry.getKey(), entry.getValue());
            charNodes.put(entry.getKey(), node);
            priorityQueue.add(node);
        }

        int sum = 0;

        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            InternalNode nodes = new InternalNode(first, second);
            sum += nodes.sum;
            priorityQueue.add(nodes);
        }

        if (letters.size() == 1) {
            sum = s.length();
        }

        System.out.println(letters.size() + " " + sum);

        Node root = priorityQueue.poll();
        if (letters.size() == 1) {
            root.buildCode("0");
        } else {
            root.buildCode("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(charNodes.get(c).code);
        }
        System.out.println(sb);
    }

    class Node {
        final int sum;
        String code;

        void buildCode(String code) {
            this.code = code;
        }

        public Node(int sum) {
            this.sum = sum;
        }

        public int getSum() {
            return sum;
        }
    }

    class InternalNode extends Node {
        Node left;
        Node right;

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }

        public InternalNode(Node left, Node right) {
            super(left.sum + right.sum);
            this.left = left;
            this.right = right;
        }
    }

    class LeafNode extends Node {
        char symbol;

        @Override
        void buildCode(String code) {
            super.buildCode(code);
            System.out.println(symbol + ": " + code);
        }

        public LeafNode(char symbol, int sum) {
            super(sum);
            this.symbol = symbol;
        }
    }
}

/*


        int countKeys = 0;
        StringBuilder sb = new StringBuilder();

        for (Character character : letters.keySet()) {
            countKeys++;
        }

        for (int i = 0; i < s.length(); i++) {
            sb.append(dictionary.get(s.charAt(i)));
        }

        System.out.println(countKeys + " " + sb.length());
        for (Character ch : letters.keySet()) {
            System.out.println(ch + ": " + dictionary.getOrDefault(ch, 0));
        }
        System.out.println(sb);*/