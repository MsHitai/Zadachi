package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BFSSitesDjiakstra {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int nodes = Integer.parseInt(input[0]);
            int edges = Integer.parseInt(input[1]);
            List<Edge> pairs = new ArrayList<>();
            StringTokenizer tokenizer;
            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int from = Integer.parseInt(tokenizer.nextToken()) - 1;
                int to = Integer.parseInt(tokenizer.nextToken()) - 1;
                int weight = Integer.parseInt(tokenizer.nextToken());
                pairs.add(new Edge(from, to, weight));
            }

            System.out.println(buildFastestPathMatrix(pairs, nodes).stream()
                    .map(list -> list.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<List<Integer>> buildFastestPathMatrix(List<Edge> edges, int nodes) {
        Map<Integer, List<Edge>> neighbors = buildUndirectedLists(edges);
        List<List<Integer>> matrix = new ArrayList<>();

        for (int source = 0; source < nodes; source++) {
            int[] distance = dijkstra(neighbors, source, nodes);
            List<Integer> row = new ArrayList<>();
            for (int i : distance) {
                row.add(i == Integer.MAX_VALUE ? -1 : i);
            }
            matrix.add(row);
        }
        return matrix;
    }

    private static int[] dijkstra(Map<Integer, List<Edge>> graph, int i, int n) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[i] = 0;

        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.naturalOrder());
        queue.add(new Edge(i, i, 0));

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int currentTo = current.getTo();

            if (visited[currentTo]) {
                continue;
            }
            visited[currentTo] = true;

            List<Edge> neighbors = graph.getOrDefault(currentTo, Collections.emptyList());
            for (Edge edge : neighbors) {
                int edgeTo = edge.getTo();
                int weight = edge.getWeight();
                if (!visited[edgeTo] && distance[currentTo] != Integer.MAX_VALUE
                        && distance[currentTo] + weight < distance[edgeTo]) {
                    distance[edgeTo] = distance[currentTo] + weight;
                    queue.add(new Edge(currentTo, edgeTo, distance[edgeTo]));
                }
            }
        }
        return distance;
    }


    private static Map<Integer, List<Edge>> buildUndirectedLists(List<Edge> nodes) {
        Map<Integer, List<Edge>> result = new HashMap<>();
        for (Edge edge : nodes) {
            result.computeIfAbsent(edge.getFrom(), v -> new ArrayList<>()).add(edge);
            result.computeIfAbsent(edge.getTo(), v -> new ArrayList<>()).add(
                    new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
        }

        return result;
    }

    private static class Edge implements Comparable<Edge> {
        private final int weight;
        private final int to;
        private final int from;

        public Edge(int from, int to, int weight) {
            this.weight = weight;
            this.to = to;
            this.from = from;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.getWeight());
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", to=" + to +
                    ", from=" + from +
                    '}';
        }
    }
}
