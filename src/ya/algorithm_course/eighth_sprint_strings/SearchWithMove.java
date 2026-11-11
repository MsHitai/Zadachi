package ya.algorithm_course.eighth_sprint_strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Поиск со сдвигом: найти все позиции i (с 1), где x[i + k] - p[k] одинаково для всех k,
 * т.е. шаблон p встречается в x с точностью до прибавления константы.
 * <p>
 * Сравниваем разности соседних элементов: x[i+k] - x[i+k-1] == p[k] - p[k-1].
 * Шаблон длины 1 подходит в любой позиции. Время O(n * m), память O(m).
 * (Можно O(n + m): построить массивы разностей и искать один в другом через КМП / Z-функцию.)
 */
public class SearchWithMove {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] nums = readArray(reader.readLine(), n);
            int m = Integer.parseInt(reader.readLine().trim());
            int[] pattern = readArray(reader.readLine(), m);

            System.out.println(findPositions(nums, pattern).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int[] readArray(String line, int size) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }

    static List<Integer> findPositions(int[] nums, int[] pattern) {
        List<Integer> result = new ArrayList<>();
        int m = pattern.length;
        for (int i = 0; i + m <= nums.length; i++) {
            boolean matches = true;
            for (int k = 1; k < m; k++) {
                if (nums[i + k] - nums[i + k - 1] != pattern[k] - pattern[k - 1]) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
