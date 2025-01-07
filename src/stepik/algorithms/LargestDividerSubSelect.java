package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LargestDividerSubSelect {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(findLargestCommonDividerSubSelectLength(array));
    }

    private static int findLargestCommonDividerSubSelectLength(int[] array) {
        int n = array.length;
        int[] answers = new int[n];
        Arrays.fill(answers, 1);//каждое число последовательность из 1 элемента

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] % array[j] == 0 && answers[j] + 1 > answers[i]) {
                    answers[i] = answers[j] + 1;
                }
            }
        }

        int ans = 0;
        for (int a : answers) {
            if (a > ans) {
                ans = a;
            }
        }

        return ans;
    }
}
