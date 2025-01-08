package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@SuppressWarnings("unused")
public class RocksGame {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();

        System.out.println(findGameResult(n, m));
    }

    private static String findGameResult(int n, int m) {
        int[][] result = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                boolean canWin = checkCanWin(i, result, j);
                result[i][j] = canWin ? 1 : 0;
            }
        }

        return result[n][m] == 1 ? "Win" : "Lose";
    }

    private static boolean checkCanWin(int i, int[][] result, int j) {
        boolean canWin = i > 0 && result[i - 1][j] == 0;
        // Take 1 stone from one pile
        if (j > 0 && result[i][j - 1] == 0) {
            canWin = true;
        }

        // Take 2 stones from one pile
        if (i >= 2 && result[i - 2][j] == 0) {
            canWin = true;
        }
        if (j >= 2 && result[i][j - 2] == 0) {
            canWin = true;
        }

        // Take 2 from one and 1 from another
        if (i >= 2 && j >= 1 && result[i - 2][j - 1] == 0) canWin = true;
        if (i >= 1 && j >= 2 && result[i - 1][j - 2] == 0) canWin = true;
        return canWin;
    }

    private static String findEvenGameResult(int n, int m) {
        if (n % 2 == 0 && m % 2 == 0) {
            return "Lose";
        } else {
            return "Win";
        }
    }
}
