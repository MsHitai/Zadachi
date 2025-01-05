package stepik.algorithms;

import java.util.Scanner;

public class RocksHackGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] table = new int[n + 1][m + 1];
        table[0][0] = -1;
        table[1][0] = 1;
        table[2][0] = 1;
        table[2][1] = 1;
        table[0][1] = 1;
        table[0][2] = 1;
        table[1][2] = 1;
        for (int i = 2; i < n + 1; i++) {
            if (table[i - 1][0] == 1 && table[i - 2][0] == 1) {
                table[i][0] = -1;
            } else {
                table[i][0] = 1;
            }
        }

        for (int j = 2; j < m + 1; j++) {
            if (table[0][j - 1] == 1 && table[0][j - 2] == 1) {
                table[0][j] = -1;
            }
            else {
                table[0][j] = 1;
            }
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 2; j < m + 1; j++) {
                if (table[i - 1][j] == 1
                        && table[i - 2][j] == 1
                        && table[i - 2][j - 1] == 1
                        && table[i][j - 1] == 1
                        && table[i][j - 2] == 1
                        && table[i - 1][j - 2] == 1) {
                    table[i][j] = -1;
                } else {
                    table[i][j] = 1;
                }
            }
        }
        System.out.println(table[n][m] == 1 ? "Win" : "Lose");
    }
}
