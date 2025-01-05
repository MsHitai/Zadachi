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
        System.out.println(findUnevenGameResult(n, m));
    }

    private static String findUnevenGameResult(int n, int m) {
        String lose = "Lose";
        String win = "Win";
        List<String> evenRow = new ArrayList<>();
        for (int i = 0; i <= n; i = i + 3) {
            evenRow.add(lose);
            evenRow.add(win);
            evenRow.add(win);
        }
        Map<Boolean, List<String>> result = new HashMap<>();
        result.put(Boolean.TRUE, evenRow);
        boolean even = m % 2 == 0;
        if (even) {
            List<String> values = result.get(Boolean.TRUE);
            return values.get(n - 1);
        } else {
            return win;
        }
    }

    private static String findEvenGameResult(int n, int m) {
        if (n % 2 == 0 && m % 2 == 0) {
            return "Lose";
        } else {
            return "Win";
        }
    }
}
