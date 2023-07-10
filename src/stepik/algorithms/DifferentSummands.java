package stepik.algorithms;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DifferentSummands {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        Set<Integer> result = findDistinctSummands(number);

        Set<Integer> secondResult = findDifferentSummands(number);

        printResult(result);
        System.out.println();
        printResult(secondResult);


    }

    private static void printResult(Set<Integer> result) {
        System.out.println(result.size());
        for (Integer summand : result) {
            System.out.print(summand + " ");
        }
    }

    public static Set<Integer> findDistinctSummands(int n) {
        Set<Integer> summands = new HashSet<>();
        for (int k = n, l = 1; k > 0; l++) {
            if (k <= 2 * l) {
                summands.add(k);
                k -= k;
            } else {
                summands.add(l);
                k -= l;
            }
        }
        return summands;
    }

    public static Set<Integer> findDifferentSummands(int number) {
        int start = 1;
        Set<Integer> summands = new HashSet<>();

        while (true) {
            if (summands.contains(number - start)) {
                start++;
                continue;
            } else {
                if (number <= start * 2) {
                    summands.add(number);
                    break;
                }
                number -= start;
                summands.add(start);
                start++;
            }

            if (number <= 0) {
                return summands;
            }
        }
        return summands;
    }
}
