package ya.algorithm_course.seventh_sprint_dynamic;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlowerFieldWithPrevious {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int[][] field = new int[n][m];
            String flowers;
            for (int i = 0; i < n; i++) {
                int[] row = new int[m];
                flowers = reader.readLine();
                for (int j = 0; j < m; j++) {
                    row[j] = Integer.parseInt(String.valueOf(flowers.charAt(j)));
                }
                field[i] = row;
            }
            System.out.println(findMaxFlowersCount(n, m, field));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static FieldGuide findMaxFlowersCount(int n, int m, int[][] field) {
        FieldGuide fg = new FieldGuide(0, new StringBuilder());
        int[][] dp = new int[n][m];
        dp[n - 1][0] = field[n - 1][0];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (j - 1 >= 0 && i + 1 < n) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]) + field[i][j];
                } else if (j - 1 >= 0) {
                    dp[i][j] = dp[i][j - 1] + field[i][j];
                } else if (i + 1 < n) {
                    dp[i][j] = dp[i + 1][j] + field[i][j];
                }
            }
        }

        fg.maxFlowers = dp[0][m - 1];
        fg.setPrevious(constructBackPath(n, m, dp).reverse());

        return fg;
    }

    private static StringBuilder constructBackPath(int n, int m, int[][] dp) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = m - 1;

        while (i < n && j >= 0) {
            if (j - 1 >= 0 && i + 1 < n) {
                if (dp[i][j - 1] >= dp[i + 1][j]) {
                    sb.append("R");
                    j--;
                } else {
                    sb.append("U");
                    i++;
                }
            } else if (i + 1 == n && j - 1 >= 0) {
                sb.append("R");
                j--;
            } else if (j == 0 && i + 1 < n) {
                sb.append("U");
                i++;
            } else {
                i++;
            }
        }
        return sb;
    }

    static class FieldGuide {
        int maxFlowers;
        @Setter
        StringBuilder previous;

        public FieldGuide(int maxFlowers, StringBuilder previous) {
            this.maxFlowers = maxFlowers;
            this.previous = previous;
        }

        @Override
        public String toString() {
            return maxFlowers +
                    "\n" + previous;
        }
    }
}
