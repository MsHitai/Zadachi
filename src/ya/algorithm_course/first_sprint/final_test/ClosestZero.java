package ya.algorithm_course.first_sprint.final_test;

/*https://contest.yandex.ru/contest/22450/run-report/134922337/*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ClosestZero {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int length = Integer.parseInt(reader.readLine());
        int[] houses = new int[length];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < length; i++) {
            houses[i] = Integer.parseInt(tokenizer.nextToken());
        }

        List<Integer> result = findTheClosestZero(houses);
        System.out.println(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static List<Integer> findTheClosestZero(int[] houses) {
        List<Integer> result = new ArrayList<>(houses.length);
        int left = getFirstLeftZeroIndex(houses);
        int right = getFirstRightZeroIndex(houses);
        boolean leftEqualsRight = left == right;
        int difference;
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == 0 && i != left) {
                left = i;
            }
            difference = Math.abs(i - left);
            result.add(difference);
        }

        if (leftEqualsRight) {
            return result;
        }

        for (int i = houses.length - 1; i >= 0; i--) {
            if (houses[i] == 0 && i != right) {
                right = i;
            }
            difference = Math.abs(i - right);
            if (result.get(i) > difference) {
                result.set(i, difference);
            }
        }

        return result;
    }

    private static int getFirstRightZeroIndex(int[] houses) {
        for (int i = houses.length - 1; i >= 0; i--) {
            if (houses[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static int getFirstLeftZeroIndex(int[] houses) {
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
