package ya.algorithm_course.first_sprint_pointers.final_test;

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
        int zeroIndex = Integer.MAX_VALUE;
        int difference;
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == 0) {
                zeroIndex = i;
            }
            difference = Math.abs(i - zeroIndex);
            result.add(difference);
        }

        for (int i = houses.length - 1; i >= 0; i--) {
            if (houses[i] == 0) {
                zeroIndex = i;
            }
            difference = Math.abs(i - zeroIndex);
            if (result.get(i) > difference) {
                result.set(i, difference);
            }
        }

        return result;
    }
}
