package ya.algorithm_course.second_sprint_recursion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StackMax {

    private final List<Integer> items;
    private final Deque<Integer> sorted;

    public StackMax(int length) {
        items = new ArrayList<>(length);
        sorted = new LinkedList<>();
    }

    public void push(Integer item) {
        if (!sorted.isEmpty() && item >= sorted.getLast()) {
            sorted.add(item);
            items.add(item);
            return;
        }
        items.add(item);
        sorted.addFirst(item);
    }

    public Integer pop() {
        if (items.isEmpty()) {
            return Integer.MIN_VALUE;
        } else if (Objects.equals(items.get(size() - 1), sorted.getLast())) {
            sorted.removeLast();
            return items.remove(items.size() - 1);
        }
        Integer temp = items.remove(items.size() - 1);
        sorted.remove(temp);
        return temp;
    }

    public Integer top() {
        if (items.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return items.get(items.size() - 1);
        }
    }

    public Integer getMax() {
        if (items.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return sorted.getLast();
        }
    }

    public int size() {
        return items.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int length = Integer.parseInt(reader.readLine());
            String[] commands = new String[length];
            for (int i = 0; i < length; i++) {
                commands[i] = reader.readLine();
            }
            StackMax stackMax = new StackMax(length);
            processCommands(commands, stackMax);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCommands(String[] commands, StackMax stackMax) {
        int temp;
        for (String command : commands) {
            if (command.startsWith("get_max")) {
                temp = stackMax.getMax();
                if (temp == Integer.MIN_VALUE) {
                    System.out.println("None");
                } else {
                    System.out.println(temp);
                }
            } else if (command.startsWith("push")) {
                String[] split = command.split(" ");
                temp = Integer.parseInt(split[1]);
                stackMax.push(temp);
            } else if (command.startsWith("top")) {
                temp = stackMax.top();
                if (temp == Integer.MIN_VALUE) {
                    System.out.println("error");
                } else {
                    System.out.println(temp);
                }
            } else {
                temp = stackMax.pop();
                if (temp == Integer.MIN_VALUE) {
                    System.out.println("error");
                }
            }
        }
    }
}
