package stepik.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class HanoiTower {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int minMoves = minStepsHanoiFourPegs(n);
        System.out.println(minMoves);
        List<List<Integer>> result = new ArrayList<>();
        moveFourTowers(n, 1, 4, 2, 3, result);
        System.out.println(result.size());
    }

    /**
     * Formula: T4(n) = 2 * T4(n - k) + T3(k)
     * <a href="https://dl.acm.org/doi/pdf/10.1145/126459.126460">...</a>
     */
    private static int minStepsHanoiFourPegs(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int minMoves = Integer.MAX_VALUE;

        for (int k = 1; k < n; k++) {
            int moves = 2 * minStepsHanoiFourPegs(n - k) + (1 << k) - 1;
            minMoves = Math.min(minMoves, moves);
        }

        return minMoves;
    }

    /**
     * Не минимальное кол-во ходов
     */
    private static void moveFourTowers(int n, int start, int finish, int temp1, int temp2, List<List<Integer>> result) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            result.add(List.of(start, finish));
            return;
        }
        moveFourTowers(n - 2, start, temp1, temp2, finish, result);
        result.add(List.of(start, temp2));
        result.add(List.of(start, finish));
        result.add(List.of(temp2, finish));
        moveFourTowers(n - 2, temp1, finish, start, temp2, result);
    }

    /**
     * Вывести пару ходов с начальной на конечную башню с новой строки
     */
    private static void printResult(List<List<Integer>> result) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> list : result) {
            String temp = list.stream().map(String::valueOf).collect(Collectors.joining(" "));
            sb.append(temp);
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * 3 башни
     */
    private static void move(int n, int start, int finish, List<List<Integer>> result) {
        if (n == 1) {
            List<Integer> steps = new ArrayList<>();
            steps.add(start);
            steps.add(finish);
            result.add(steps);
        } else {
            int temp = 6 - start - finish;
            move(n - 1, start, temp, result);
            List<Integer> steps = new ArrayList<>();
            steps.add(start);
            steps.add(finish);
            result.add(steps);
            move(n - 1, temp, finish, result);
        }
    }
}
