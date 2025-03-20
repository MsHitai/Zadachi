package ya.algorithm_course.second_sprint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RestrictedQueue {

    private final Integer[] items;
    private int tail;
    private int head;
    private int size;
    private final int maxSize;

    public RestrictedQueue(int maxSize) {
        items = new Integer[maxSize];
        size = 0;
        tail = 0;
        head = 0;
        this.maxSize = maxSize;
    }

    public void push(Integer item) {
        if (size < maxSize) {
            items[tail] = item;
            tail = (tail + 1) % maxSize;
            size++;
        } else {
            System.out.println("error");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return items[head];
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        Integer result = items[head];
        items[head] = null;
        head = (head + 1) % maxSize;
        size--;
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int length = Integer.parseInt(reader.readLine());
            int maxSize = Integer.parseInt(reader.readLine());
            String[] commands = new String[length];
            for (int i = 0; i < length; i++) {
                commands[i] = reader.readLine();
            }
            RestrictedQueue queue = new RestrictedQueue(maxSize);
            processCommands(commands, queue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCommands(String[] commands, RestrictedQueue queue) {
        Integer temp;
        for (String command : commands) {
            if (command.startsWith("peek")) {
                temp = queue.peek();
                if (temp == null) {
                    System.out.println("None");
                } else {
                    System.out.println(temp);
                }
            } else if (command.startsWith("push")) {
                String[] split = command.split(" ");
                temp = Integer.parseInt(split[1]);
                queue.push(temp);
            } else if (command.startsWith("size")) {
                temp = queue.getSize();
                System.out.println(temp);
            } else {
                temp = queue.pop();
                if (temp == null) {
                    System.out.println("None");
                } else {
                    System.out.println(temp);
                }
            }
        }
    }
}
