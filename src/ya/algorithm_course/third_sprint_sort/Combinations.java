package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Combinations {

    private final static Map<String, String> DICT = new HashMap<>();

    static {
        DICT.put("2", "abc");
        DICT.put("3", "def");
        DICT.put("4", "ghi");
        DICT.put("5", "jkl");
        DICT.put("6", "mno");
        DICT.put("7", "pqrs");
        DICT.put("8", "tuv");
        DICT.put("9", "wxyz");
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] nums = reader.readLine().split("");
            List<String> variations = new ArrayList<>();
            for (String num : nums) {
                variations.add(DICT.get(num));
            }
            combine(variations, "", 0);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Выведите все возможные комбинации букв через пробел в лексикографическом (алфавитном) порядке по возрастанию
     *
     * @param letters буквы соответствующие цифре на мобильном телефоне, i.e. 2 -> a,b,c
     * @param combine сочетание
     * @param i       индекс для прохода по списку букв
     */
    private static void combine(List<String> letters, String combine, int i) {
        if (combine.length() == letters.size()) {
            System.out.print(combine + " ");
            return;
        }

        if (i < letters.size()) {
            String let1 = letters.get(i);
            for (int k = 0; k < let1.length(); k++) {
                String letter = String.valueOf(let1.charAt(k));
                combine(letters, combine + letter, i + 1);
            }
        }
    }

    // сгенерировать надо длиной 2, поэтому фиксированное число, turned out I was wrong, it should be length
    private static void combineTwo(String first, String second, String combine, int left, int right) {
        if (combine.length() == 2) {
            System.out.print(combine + " ");
        }

        if (left < first.length() && right < second.length()) {
            String let1 = String.valueOf(first.charAt(left));
            String let2 = String.valueOf(second.charAt(right));
            combineTwo(first, second, let1 + let2, left, right + 1);
        } else if (left + 1 < first.length()) {
            left += 1;
            right = 0;
            combineTwo(first, second, "", left, right);
        }
    }
}
