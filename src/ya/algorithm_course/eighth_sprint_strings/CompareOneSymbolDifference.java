package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompareOneSymbolDifference {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String first = reader.readLine();
            String second = reader.readLine();
            boolean equal = isOneEditDistance(first, second);
            if (equal) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isOneEditDistance(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int firstLength = first.length();
        int secondLength = second.length();

        // Ensure first is the shorter string
        if (firstLength > secondLength) {
            return isOneEditDistance(second, first);
        }

        // If length difference is more than 1, can't be one edit apart
        if (secondLength - firstLength > 1) {
            return false;
        }

        for (int i = 0; i < firstLength; i++) {
            if (first.charAt(i) != second.charAt(i)) {
                // If lengths are the same, must be a replacement
                if (firstLength == secondLength) {
                    return first.substring(i + 1).equals(second.substring(i + 1));
                } else {
                    // Must be an insertion in second or deletion in first
                    return first.substring(i).equals(second.substring(i + 1));
                }
            }
        }
        // If all previous chars are the same, only one extra char at the end
        return secondLength - firstLength == 1;
    }
}
