package ya.algorithm_course.second_sprint_recursion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReverseMatrix {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int height = Integer.parseInt(reader.readLine());
            int width = Integer.parseInt(reader.readLine());
            String[][] grid = new String[height][width];
            StringTokenizer tokenizer;
            for (int i = 0; i < height; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String[] line = new String[width];
                for (int j = 0; j < width; j++) {
                    line[j] = tokenizer.nextToken();
                }
                grid[i] = line;
            }
            System.out.println(reverseGrid(grid, width));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String reverseGrid(String[][] grid, int width) {
        List<String> result = new ArrayList<>();
        int j = 0;
        while (j != width) {
            List<String> line = new ArrayList<>();
            for (String[] str : grid) {
                line.add(str[j]);
            }
            result.add(String.join(" ", line));
            j++;
        }
        return String.join("\n", result);
    }
}
