package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ArrayOfGraphs {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int nodes = Integer.parseInt(input[0]);
            int edges = Integer.parseInt(input[1]);
            List<List<Integer>> pairs = new ArrayList<>();
            StringTokenizer tokenizer;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                List<Integer> nodeToNode = new ArrayList<>();
                nodeToNode.add(Integer.parseInt(tokenizer.nextToken()));
                nodeToNode.add(Integer.parseInt(tokenizer.nextToken()));
                pairs.add(nodeToNode);
            }
            System.out.println(countNodesToEdges(nodes, pairs));
            System.out.println(Arrays.stream(buildMatrix(pairs, nodes))
                    .map(row -> Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String countNodesToEdges(int n, List<List<Integer>> pairs) {
        StringBuilder sb = new StringBuilder();
        int[][] matrix = buildMatrix(pairs, n);

        for (int[] row : matrix) {
            int sum = Arrays.stream(row).sum();
            if (sum == 0) {
                sb.append("0").append("\n");
                continue;
            }
            sb.append(sum).append(" ");
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    sb.append(j + 1).append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    private static int[][] buildMatrix(List<List<Integer>> pairs, int n) {
        int[][] result = new int[n][n];

        for (List<Integer> pair : pairs) {
            int i = pair.getFirst() - 1;
            int j = pair.getLast() - 1;
            result[i][j] = 1;
        }

        return result;
    }
}
