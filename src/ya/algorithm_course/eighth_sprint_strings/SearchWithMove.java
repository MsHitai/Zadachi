package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SearchWithMove {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {

            int n = Integer.parseInt(reader.readLine());
            List<Integer> temperatures = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; i++) {
                temperatures.add(Integer.parseInt(tokenizer.nextToken()));
            }

            int step = Integer.parseInt(reader.readLine());
            List<Integer> template = new ArrayList<>();
            tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < step; i++) {
                template.add(Integer.parseInt(tokenizer.nextToken()));
            }

            System.out.println(findPositions(temperatures, template, step).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Integer> findPositions(List<Integer> nums, List<Integer> template, int step) {
        List<Integer> result = new ArrayList<>();
        int[] diffs = new int[step - 1];
        boolean check = false;
        int j = 0;

        for (int i = 0; i < template.size() - 1; i++) {
            diffs[i] = template.get(i + 1) - template.get(i);
        }

        for (int i = 0; i < nums.size() - 1; i++) {
            for (int k = 1; k < step; k++) {
                if (i + k < nums.size() && nums.get(i + k) - nums.get(i + j++) == diffs[k - 1]) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check) {
                result.add(i + 1);
                check = false;
            }
            j = 0;
        }

        return result;
    }

}
