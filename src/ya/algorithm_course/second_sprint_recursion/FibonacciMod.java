package ya.algorithm_course.second_sprint_recursion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FibonacciMod {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());
            long mod = 1;
            for (int i = 0; i < k; i++) {
                mod *= 10;
            }
            long result = computeProblemF(n, mod);
            System.out.println(result == 0 && k == 1 ? "0" : String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static long computeProblemF(int n, long mod) {
        if (mod == 1) return 0;
        return fastDoubling(n + 1, mod);
    }

    private static long fastDoubling(int n, long mod) {
        long a = 0;
        long b = 1;
        int mask = 1 << 30;
        while (mask > 0 && (n & mask) == 0) {
            mask >>= 1;
        }
        for (; mask > 0; mask >>= 1) {
            long c = (a * ((2 * b - a + mod) % mod)) % mod;
            long d = (a * a % mod + b * b % mod) % mod;
            if ((n & mask) != 0) {
                a = d;
                b = (c + d) % mod;
            } else {
                a = c;
                b = d;
            }
        }
        return a;
    }
}
