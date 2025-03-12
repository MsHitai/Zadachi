package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Neighbors {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int height = Integer.parseInt(reader.readLine());
        int width = Integer.parseInt(reader.readLine());
        int[][] grid = new int[height][width];
        StringTokenizer tokenizer;
        for (int i = 0; i < height; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int[] line = new int[width];
            for (int j = 0; j < width; j++) {
                line[j] = Integer.parseInt(tokenizer.nextToken());
            }
            grid[i] = line;
        }
        int y = Integer.parseInt(reader.readLine());
        int x = Integer.parseInt(reader.readLine());
        System.out.println(findNeighbors(grid, y, x, width));
    }

    private static String findNeighbors(int[][] grid, int y, int x, int width) {
        List<Integer> result = new ArrayList<>();
        if (x + 1 < width) {
            result.add(grid[y][x + 1]); // right
        }
        if (y < grid.length && y > 0) {
            result.add(grid[y - 1][x]); // top neighbor
        }
        if (y + 1 < grid.length) {
            result.add(grid[y + 1][x]); // down neighbor
        }
        if (x < width && x > 0) {
            result.add(grid[y][x - 1]); // left
        }

        return result.stream()
                .sorted(Comparator.comparingInt(e -> e))
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
