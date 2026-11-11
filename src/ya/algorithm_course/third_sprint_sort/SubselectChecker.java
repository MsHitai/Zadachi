package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Является ли первая строка подпоследовательностью второй (LeetCode 392 "Is Subsequence").
 * Два указателя: идём по второй строке и сдвигаем указатель в первой при совпадении. O(n + m).
 */
public class SubselectChecker {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String subsequence = reader.readLine();
            String text = reader.readLine();
            System.out.println(isSubsequence(subsequence, text) ? "True" : "False");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isSubsequence(String subsequence, String text) {
        int i = 0;
        for (int j = 0; j < text.length() && i < subsequence.length(); j++) {
            if (subsequence.charAt(i) == text.charAt(j)) {
                i++;
            }
        }
        return i == subsequence.length();
    }
}
