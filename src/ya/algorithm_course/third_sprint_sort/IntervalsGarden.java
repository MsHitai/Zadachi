package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Клумбы: объединить пересекающиеся (и касающиеся) отрезки (LeetCode 56 "Merge Intervals").
 * <p>
 * Сортируем по началу; идём слева направо и либо расширяем текущий отрезок, либо начинаем новый.
 * Время O(n log n), память O(n).
 */
public class IntervalsGarden {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[][] intervals = new int[n][2];
            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                intervals[i][0] = Integer.parseInt(tokenizer.nextToken());
                intervals[i][1] = Integer.parseInt(tokenizer.nextToken());
            }
            StringBuilder sb = new StringBuilder();
            for (int[] interval : merge(intervals)) {
                sb.append(interval[0]).append(' ').append(interval[1]).append('\n');
            }
            System.out.print(sb);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static List<int[]> merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(new int[]{interval[0], interval[1]});
            } else {
                result.getLast()[1] = Math.max(result.getLast()[1], interval[1]);
            }
        }
        return result;
    }
}
