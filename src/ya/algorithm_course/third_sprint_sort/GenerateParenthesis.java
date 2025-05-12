package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateParenthesis {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            generate(n, "", n * 2, 0, 0);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void generate(int n, String str, int goalSize, int leftCount, int rightCount) {
        if (str.length() == goalSize) {
            System.out.println(str);
            return;
        }

        if (leftCount < n) {
            generate(n, str + "(", goalSize, leftCount + 1, rightCount);
        }
        if (rightCount < leftCount) {
            generate(n, str + ")", goalSize, leftCount, rightCount + 1);
        }
    }

    @SuppressWarnings("unused")
    public static void generateOneZero(int n, String prefix) {
        if (n == 0) {
            System.out.println(prefix);
        } else {
            generateOneZero(n - 1, prefix + "1");
            generateOneZero(n - 1, prefix + "0");
        }
    }
}
