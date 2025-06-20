package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class LeprechaunThieves {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int maxWeight = Integer.parseInt(input[1]);
            List<Integer> goldWeights = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                goldWeights.add(Integer.parseInt(tokenizer.nextToken()));
            }
            System.out.println(findMaxWeightCanCarry(maxWeight, goldWeights));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int findMaxWeightCanCarry(int maxWeight, List<Integer> weights) {
        int[] dp = new int[maxWeight + 1];

        for (int weight : weights) {
            for (int j = maxWeight; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + weight);
            }
        }

        System.out.println(Arrays.toString(dp));

        return dp[maxWeight];
    }
}
