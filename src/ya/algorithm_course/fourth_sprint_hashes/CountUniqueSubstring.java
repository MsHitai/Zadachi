package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CountUniqueSubstring {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String input = reader.readLine();
            System.out.println(findLongestUniqueSubstring(input));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int findLongestUniqueSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int left = 0, best = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (seen.contains(c)) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(c);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
