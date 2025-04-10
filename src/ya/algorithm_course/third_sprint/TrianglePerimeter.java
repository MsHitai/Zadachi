package ya.algorithm_course.third_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TrianglePerimeter {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] lines = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                lines[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(findThePerimeter(lines));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int findThePerimeter(int[] lines) {
        int[] sorted = Arrays.stream(lines).sorted().toArray();
        int[] triangle = new int[3];
        for (int current = sorted.length - 1; current >= 2; current--) {
            int left = current - 1;
            int right = current - 2;
            if (sorted[current] < sorted[left] + sorted[right]) {
                triangle[0] = Math.max(sorted[current], triangle[0]);
                triangle[1] = Math.max(sorted[left], triangle[1]);
                triangle[2] = Math.max(sorted[right], triangle[2]);
                current = right;
            }
        }

        return Arrays.stream(triangle).sum();
    }
}
