package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonPrefixLength {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<String> words = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                words.add(reader.readLine());
            }
            System.out.println(findCommonPrefixLength(words));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findCommonPrefixLength(List<String> words) {
        Collections.sort(words);
        String first = words.getFirst();
        String last = words.getLast();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) == last.charAt(i)) {
                sb.append(first.charAt(i));
            } else {
                break;
            }
        }

        return sb.toString().length();
    }
}
