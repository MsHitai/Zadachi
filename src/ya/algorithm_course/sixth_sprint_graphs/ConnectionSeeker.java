package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ConnectionSeeker {

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
            List<List<Integer>> connections = findConnections(nodes, pairs);
            System.out.println(connections.size());
            System.out.println(connections
                    .stream()
                    .map(list -> list.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<List<Integer>> findConnections(int n, List<List<Integer>> nodes) {
        Integer[] color = new Integer[n];
        Arrays.fill(color, -1);
        int count = 1;

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> neighbors = buildUndirectedLists(nodes, n);

        for (int k = 0; k < color.length; k++) {
            if (-1 == color[k]) {
                stack.push(k);

                while (!stack.isEmpty()) {
                    int node = stack.pop();

                    if (-1 == color[node]) {
                        color[node] = count;
                        stack.push(node);

                        List<Integer> row = neighbors.get(node);
                        for (int i = row.size() - 1; i >= 0; i--) {
                            if (-1 == color[row.get(i)]) {
                                stack.push(row.get(i));
                            }
                        }
                    } else if (count == color[node]) {
                        color[node] = count;
                    }
                }
                count++;
            }
        }

        return getResultedConnections(color);
    }

    private static List<List<Integer>> getResultedConnections(Integer[] color) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> colorToIndexes = new HashMap<>();
        for (int i = 0; i < color.length; i++) {
            colorToIndexes.computeIfAbsent(color[i], k -> new ArrayList<>()).add(i + 1);
        }
        List<Integer> keys = new ArrayList<>(colorToIndexes.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            result.add(colorToIndexes.get(key));
        }
        result.forEach(Collections::sort);

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

        return result;
    }
}
