package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AllaLadders {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            System.out.println(findLaddersHopVariations(n, k));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long findLaddersHopVariations(int n, int k) {
        long mod = (long) Math.pow(10, 9) + 7;
        long[] dp = new long[n + 1];
        long currentSum = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = currentSum;
            currentSum = (currentSum + dp[i]) % mod;
            if (i > k) {
                currentSum = (currentSum - dp[i - k] + mod) % mod;
            }
        }
        return dp[n];
    }
}
