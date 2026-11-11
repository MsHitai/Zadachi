package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Странное сравнение: можно ли заменой букв получить из первой строки вторую (одинаковые буквы -> одинаковые,
 * разные -> разные). Аналог LeetCode 205 "Isomorphic Strings".
 * <p>
 * Нужна биекция: храним отображение в обе стороны, иначе "ab" -> "aa" будет ошибочно считаться равным.
 * Время O(n), память O(алфавит).
 */
public class WeirdComparison {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String first = reader.readLine();
            String second = reader.readLine();
            System.out.println(isIsomorphic(first, second) ? "YES" : "NO");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static boolean isIsomorphic(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        Map<Character, Character> forward = new HashMap<>();
        Map<Character, Character> backward = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            char a = first.charAt(i);
            char b = second.charAt(i);
            Character mappedA = forward.putIfAbsent(a, b);
            Character mappedB = backward.putIfAbsent(b, a);
            if ((mappedA != null && mappedA != b) || (mappedB != null && mappedB != a)) {
                return false;
            }
        }
        return true;
    }
}
