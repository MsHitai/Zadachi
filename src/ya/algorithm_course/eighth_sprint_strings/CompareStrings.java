package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompareStrings {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String first = reader.readLine();
            String second = reader.readLine();

            int x = compareStrings(first, second);
            if (x < 0) {
                System.out.println("-1");
            } else if (x > 0) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int compareStrings(String first, String second) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) % 2 == 0) {
                sb1.append(first.charAt(i));
            }
        }
        for (int i = 0; i < second.length(); i++) {
            if (second.charAt(i) % 2 == 0) {
                sb2.append(second.charAt(i));
            }
        }
        return sb1.compareTo(sb2);
    }
}
