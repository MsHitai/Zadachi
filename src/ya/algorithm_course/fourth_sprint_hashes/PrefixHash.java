package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class PrefixHash {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int base = Integer.parseInt(reader.readLine());
            int module = Integer.parseInt(reader.readLine());
            String string = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            int[][] intervals = new int[n][];
            StringTokenizer tokenizer;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                intervals[i] = new int[2];
                intervals[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
                intervals[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
            }
            System.out.println(getHashesForIntervals(intervals, string, base, module).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Long> getHashesForIntervals(int[][] intervals, String string, int base, int module) {
        List<Long> result = new ArrayList<>();
        int n = string.length();
        long[] hashes = new long[n];
        hashes[0] = string.charAt(0) % module;
        for (int i = 1; i < n; i++) {
            hashes[i] = (hashes[i - 1] * base + string.charAt(i)) % module;
        }
        long[] powers = new long[n + 1];
        powers[0] = 1;
        for (int i = 1; i <= n; i++) {
            powers[i] = (powers[i - 1] * base) % module;
        }
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left < 0 || right >= n || left > right) {
                result.add(0L);
                continue;
            }
            long currentHash;
            if (left == 0) {
                currentHash = hashes[right];
            } else {
                long part1 = hashes[right];
                long part2 = (hashes[left - 1] * powers[right - left + 1]) % module;
                currentHash = (part1 - part2) % module;
            }

            if (currentHash < 0) {
                currentHash += module;
            }
            result.add(currentHash);
        }

        return result;
    }
}
