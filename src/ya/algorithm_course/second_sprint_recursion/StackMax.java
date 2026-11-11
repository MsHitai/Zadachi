package ya.algorithm_course.second_sprint_recursion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Стек с операцией get_max за O(1) (аналог LeetCode 155 "Min Stack").
 * <p>
 * Рядом с основным стеком храним стек максимумов: на вершине всегда максимум среди текущих элементов.
 * При push кладём max(item, текущий максимум), при pop снимаем с обоих стеков. Все операции O(1).
 */
public class StackMax {

    private final Deque<Integer> items = new ArrayDeque<>();
    private final Deque<Integer> maxes = new ArrayDeque<>();

    public void push(int item) {
        items.push(item);
        maxes.push(maxes.isEmpty() ? item : Math.max(item, maxes.peek()));
    }

    /**
     * @return снятый элемент или null, если стек пуст
     */
    public Integer pop() {
        if (items.isEmpty()) {
            return null;
        }
        maxes.pop();
        return items.pop();
    }

    public Integer top() {
        return items.peek();
    }

    public Integer getMax() {
        return maxes.peek();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int length = Integer.parseInt(reader.readLine().trim());
            StackMax stack = new StackMax();
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < length; i++) {
                String command = reader.readLine();
                if (command.startsWith("get_max")) {
                    Integer max = stack.getMax();
                    out.append(max == null ? "None" : max).append('\n');
                } else if (command.startsWith("push")) {
                    stack.push(Integer.parseInt(command.split(" ")[1]));
                } else if (command.startsWith("top")) {
                    Integer top = stack.top();
                    out.append(top == null ? "error" : top).append('\n');
                } else if (stack.pop() == null) {
                    out.append("error").append('\n');
                }
            }
            System.out.print(out);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
