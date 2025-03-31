package ya.algorithm_course.third_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ColorCountingSort {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] nums = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(Arrays.stream(countSort(nums, n))
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int[] countSort(int[] colors, int n) {
        int[] quantity = new int[3];
        int[] sorted = new int[n];

        for (int color : colors) {
            switch (color) {
                case 0 -> quantity[0]++;
                case 1 -> quantity[1]++;
                case 2 -> quantity[2]++;
            }
        }

        int index = 0;

        for (int i = 0; i < quantity.length; i++) {
            for (int j = 0; j < quantity[i]; j++) {
                sorted[index] = i;
                index++;
            }
        }

        return sorted;
    }
}
