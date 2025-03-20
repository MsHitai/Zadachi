package ya.algorithm_course.second_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CorrectParenthesis {

    private final static Map<String, String> DICTIONARY = new HashMap<>();

    static {
        DICTIONARY.put(")", "(");
        DICTIONARY.put("}", "{");
        DICTIONARY.put("]", "[");
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String parenthesis = reader.readLine();
            if (correctParenthesis(parenthesis)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean correctParenthesis(String parenthesis) {
        if (parenthesis.isEmpty()) {
            return true;
        }
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < parenthesis.length(); i++) {
            String bracket = String.valueOf(parenthesis.charAt(i));
            if (!DICTIONARY.containsKey(bracket)) {
                stack.addLast(bracket);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String bracketInStack = stack.getLast();
                if (bracketInStack.equals(DICTIONARY.get(bracket))) {
                    stack.removeLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
