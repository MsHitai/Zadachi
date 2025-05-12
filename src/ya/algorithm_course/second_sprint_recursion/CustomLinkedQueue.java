package ya.algorithm_course.second_sprint_recursion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomLinkedQueue {

    int size;
    Node head;
    Node tail;

    public CustomLinkedQueue() {
        size = 0;
    }

    public int get() {
        if (size == 0) {
            throw new RuntimeException();
        }
        Node temp = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return temp.value;
    }

    public void put(int value) {
        Node prev = tail;
        Node node = new Node(prev, value, null);

        tail = node;

        if (prev != null) {
            prev.next = node;
        } else {
            head = node;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int length = Integer.parseInt(reader.readLine());
            String[] commands = new String[length];
            for (int i = 0; i < length; i++) {
                commands[i] = reader.readLine();
            }
            CustomLinkedQueue queue = new CustomLinkedQueue();
            processCommands(commands, queue);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void processCommands(String[] commands, CustomLinkedQueue queue) {
        Integer temp;
        for (String command : commands) {
            if (command.startsWith("get")) {
                temp = getValue(queue);
                printValue(temp);
            } else if (command.startsWith("put")) {
                String[] split = command.split(" ");
                temp = Integer.parseInt(split[1]);
                queue.put(temp);
            } else if (command.startsWith("size")) {
                temp = queue.size();
                System.out.println(temp);
            }
        }
    }

    private static Integer getValue(CustomLinkedQueue queue) {
        try {
            return queue.get();
        } catch (RuntimeException e) {
            System.out.println("error");
        }
        return null;
    }

    private static void printValue(Integer value) {
        if (value != null) {
            System.out.println(value);
        }
    }

    static class Node {
        int value;
        Node next;
        Node prev;

        public Node(Node prev, int value, Node next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
