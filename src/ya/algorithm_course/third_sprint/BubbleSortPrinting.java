package ya.algorithm_course.third_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BubbleSortPrinting {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] numbers = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(tokenizer.nextToken());
            }
            List<String> result = bubleSort(numbers);
            if (result.size() > 1) {
                result.removeLast();
            }
            System.out.println(String.join("\n", result));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<String> bubleSort(int[] numbers) {
        boolean sorted = false;
        List<String> result = new ArrayList<>();
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;
                    sorted = false;
                }
            }
           result.add(Arrays.stream(numbers)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }
        return result;
    }
}
