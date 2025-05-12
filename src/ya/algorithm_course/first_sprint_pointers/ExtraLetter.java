package ya.algorithm_course.first_sprint_pointers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtraLetter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String first = reader.readLine();
        String second = reader.readLine();
        System.out.println(findExtraLetter(first, second));
    }

    private static String findExtraLetter(String first, String second) {
        Map<String, Integer> countFirst = new HashMap<>();
        Map<String, Integer> countSecond = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            String key = String.valueOf(first.charAt(i));
            countFirst.put(key, countFirst.getOrDefault(key, 0) + 1);
        }
        for (int i = 0; i < second.length(); i++) {
            String key = String.valueOf(second.charAt(i));
            countSecond.put(key, countSecond.getOrDefault(key, 0) + 1);
        }
        for (String str : countSecond.keySet()) {
            int count = countSecond.get(str);
            if (countFirst.get(str) == null || count != countFirst.get(str)) {
                return str;
            }
        }
        return "";
    }

}
