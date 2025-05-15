package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DFSGraphs {

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
            int start = Integer.parseInt(reader.readLine());
            System.out.println(printUndirectedNodes(nodes, pairs, start).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Integer> printUndirectedNodes(int n, List<List<Integer>> nodes, int start) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(start - 1);
        List<List<Integer>> neighbors = buildUndirectedLists(nodes, n);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                visited[node] = true;
                result.add(node + 1);

                List<Integer> row = neighbors.get(node);
                for (int i = row.size() - 1; i >= 0; i--) {
                    if (!visited[row.get(i)]) {
                        stack.push(row.get(i));
                    }
                }
            }
        }

        return result;
    }

    private static List<List<Integer>> buildUndirectedLists(List<List<Integer>> pairs, int n) {
        List<List<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        for (List<Integer> pair : pairs) {
            int i = pair.getFirst() - 1;
            int j = pair.getLast() - 1;
            result.get(i).add(j);
            result.get(j).add(i);
        }
        result.forEach(Collections::sort);

        return result;
    }
}
