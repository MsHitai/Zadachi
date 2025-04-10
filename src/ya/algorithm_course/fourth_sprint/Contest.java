package ya.algorithm_course.fourth_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Contest {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] rounds = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                rounds[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(longestSeries(rounds));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int longestSeries(int[] rounds) {
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int result = 0;
        int sum = 0;

        for (int i = 0; i < rounds.length; i++) {
            if (rounds[i] == 0) {
                rounds[i] = -1;
            }
            if (i == 0) {
                sum = rounds[i];
                sumToIndex.put(rounds[i], i);
                continue;
            }
            sum = sum + rounds[i];
            if (sumToIndex.containsKey(sum)) {
                result = Math.max(result, i - sumToIndex.get(sum));
            } else if (sum == 0) {
                result = i + 1;
            } else {
                sumToIndex.put(sum, i);
            }
        }

        return result;
    }
}
