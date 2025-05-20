package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSort {

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
            System.out.println(topologicalSort(nodes, pairs)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
            TopologicalSort sort = new TopologicalSort();
            System.out.println(sort.mainTopSort(nodes, pairs)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Integer> topologicalSort(int n, List<List<Integer>> nodes) {
        String[] color = new String[n];
        Arrays.fill(color, "white");
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> neighbors = buildDirectedLists(nodes, n);

        for (int k = 0; k < neighbors.size(); k++) {
            if ("white".equals(color[k])) {
                stack.push(k);

                while (!stack.isEmpty()) {
                    int node = stack.pop();

                    if ("white".equals(color[node])) {
                        color[node] = "grey";
                        stack.push(node);

                        List<Integer> row = neighbors.get(node);
                        for (int i = row.size() - 1; i >= 0; i--) {
                            if ("white".equals(color[row.get(i)])) {
                                stack.push(row.get(i));
                            }
                        }
                    } else if ("grey".equals(color[node])) {
                        color[node] = "black";
                        result.add(node + 1);
                    }
                }
            }
        }

        Collections.reverse(result);

        return result;
    }

    private static List<List<Integer>> buildDirectedLists(List<List<Integer>> pairs, int n) {
        List<List<Integer>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }
        for (List<Integer> pair : pairs) {
            int i = pair.getFirst() - 1;
            int j = pair.getLast() - 1;
            result.get(i).add(j);
        }
        result.forEach(Collections::sort);

        return result;
    }

    private void topSort(int vertex, String[] color, Stack<Integer> order, List<List<Integer>> nodes) {
        color[vertex] = "grey";
        for (Integer w : nodes.get(vertex)) {
            if ("white".equals(color[w])) {
                topSort(w, color, order, nodes);
            }
        }
        color[vertex] = "black";
        order.push(vertex + 1);
    }

    /**
     * Same but using recursion
     *
     * @param n     number of vertices
     * @param nodes all nodes
     * @return sorted graph from left to right
     */
    private List<Integer> mainTopSort(int n, List<List<Integer>> nodes) {
        String[] color = new String[n];
        Arrays.fill(color, "white");
        Stack<Integer> order = new Stack<>();
        List<List<Integer>> neighbors = buildDirectedLists(nodes, n);

        for (int i = 0; i < n; i++) {
            if ("white".equals(color[i])) {
                topSort(i, color, order, neighbors);
            }
        }
        Collections.reverse(order);
        return order;
    }
}
