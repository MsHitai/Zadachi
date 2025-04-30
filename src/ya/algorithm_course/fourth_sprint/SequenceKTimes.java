package ya.algorithm_course.fourth_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SequenceKTimes {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] line = reader.readLine().split(" ");
            int goalLength = Integer.parseInt(line[0]);
            int kTimes = Integer.parseInt(line[1]);
            String input = reader.readLine();
            System.out.println(findIndexesOfCommonSubstrings(input, goalLength, kTimes));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String findIndexesOfCommonSubstrings(String input, int goalLength, int kTimes) {
        StringBuilder sb = new StringBuilder();
        Map<Long, Count> counts = new HashMap<>();
        int base = 345;
        long modulus = 5608713984039443L;
        int n = input.length();
        String temp = input.substring(0, goalLength);
        long hash = 0;
        long power = 1;
        for (int i = 0; i < temp.length(); i++) {
            hash = (hash * base + temp.charAt(i)) % modulus;
            power = (power * base) % modulus;
        }
        Count first = new Count(1, 0);
        counts.put(hash, first);

        for (int i = 1; i < n; i++) {
            if (i + goalLength < n) {
                long substruct = (input.charAt(i - 1) * power) % modulus;
                long left = Math.abs(hash - substruct);
                hash = (left * base + (input.charAt(i + goalLength - 1))) % modulus;
                if (!counts.containsKey(hash)) {
                    Count count = new Count(1, i);
                    counts.put(hash, count);
                } else {
                    Count count = counts.get(hash);
                    count.count++;
                }
            }
        }

        for (Long h : counts.keySet()) {
            if (counts.get(h).count >= kTimes) {
                sb.append(counts.get(h).firstIndex).append(" ");
            }
        }

        return sb.toString();
    }

    public static class Count {
        int count;
        int firstIndex;

        public Count(int count, int firstIndex) {
            this.count = count;
            this.firstIndex = firstIndex;
        }
    }
}