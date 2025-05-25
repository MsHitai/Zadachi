package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BFSDistance {

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
            System.out.println(printMaxDistance(nodes, pairs, start));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Integer printMaxDistance(int n, List<List<Integer>> nodes, int start) {
        List<Integer> distance = new ArrayList<>(Arrays.asList(new Integer[n]));
        Collections.fill(distance, 0);
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int startIndex = start - 1;
        queue.add(startIndex);
        distance.set(startIndex, 0);
        visited[startIndex] = true;
        List<List<Integer>> neighbors = buildUndirectedLists(nodes, n);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            List<Integer> row = neighbors.get(node);
            for (Integer vertex : row) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    distance.set(vertex, distance.get(node) + 1);
                    queue.add(vertex);
                }
            }
            visited[node] = true;
        }

        return Collections.max(distance);
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
