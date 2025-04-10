package ya.algorithm_course.fourth_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WeirdComparison {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String first = reader.readLine();
            String second = reader.readLine();
            if (isEqualTo(first, second)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static boolean isEqualTo(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        int count = 0;
        Map<Character, Integer> firstSymbolsCount = new LinkedHashMap<>();
        Map<Character, Integer> secSymbolsCount = new LinkedHashMap<>();
        for (int i = 0; i < first.length(); i++) {
            firstSymbolsCount.put(first.charAt(i), firstSymbolsCount.getOrDefault(first.charAt(i), 0) + 1);
        }
        for (int i = 0; i < second.length(); i++) {
            secSymbolsCount.put(second.charAt(i), secSymbolsCount.getOrDefault(second.charAt(i), 0) + 1);
        }
        List<Integer> firstValues = new ArrayList<>(firstSymbolsCount.values());
        List<Integer> secValues = new ArrayList<>(secSymbolsCount.values());
        if (firstValues.size() != secValues.size()) {
            return false;
        }
        for (int i = 0; i < firstValues.size(); i++) {
            if (Objects.equals(firstValues.get(i), secValues.get(i))) {
                count++;
            }
        }
        return count == firstValues.size();
    }
}
