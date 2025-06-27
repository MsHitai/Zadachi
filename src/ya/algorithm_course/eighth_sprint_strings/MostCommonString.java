package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostCommonString {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(reader.readLine());
            }

            System.out.println(getMostCommonString(words));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getMostCommonString(List<String> words) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        int max = 0;
        String result = "";
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                result = entry.getKey();
            } else if (entry.getValue() == max) {
                result = result.compareTo(entry.getKey()) < 0 ? result : entry.getKey();
            }
        }
        return result;
    }
}
