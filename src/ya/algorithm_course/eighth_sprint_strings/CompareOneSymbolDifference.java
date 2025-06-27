package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompareOneSymbolDifference {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String first = reader.readLine();
            String second = reader.readLine();
            boolean equal = isEqual(first, second);
            if (equal) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isEqual(String first, String second) {
        if (first.equalsIgnoreCase(second)) {
            return true;
        }
        StringBuilder sb1 = new StringBuilder(first);
        StringBuilder sb2 = new StringBuilder(second);
        int count = 0;
        int size = Math.max(first.length(), second.length());
        int left = 0;

        while (left < size) {
            if (left < first.length() && left < second.length()) {
                if (first.charAt(left) == second.charAt(left)) {
                    left++;
                    continue;
                } else {
                    if (first.length() < second.length()) {
                        sb1.insert(left, second.charAt(left));
                    } else if (second.length() < first.length()) {
                        sb2.insert(left, first.charAt(left));
                    }
                    count++;
                    if (sb1.compareTo(sb2) == 0) {
                        return true;
                    }
                }
            }
            left++;
        }

        return sb1.compareTo(sb2) == 0 || count == 1;
    }

}
