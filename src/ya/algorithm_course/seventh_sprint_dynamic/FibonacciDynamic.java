package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FibonacciDynamic {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(findFibonacciDynamic(n));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long findFibonacciDynamic(int n) {
        long mod = (long) Math.pow(10, 9) + 7;
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }
        return dp[n];
    }
}
