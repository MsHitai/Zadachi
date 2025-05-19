package ya.algorithm_course.sixth_sprint_graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DFSInOutTime {

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
            System.out.println(printInOutTimes(nodes, pairs));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String printInOutTimes(int n, List<List<Integer>> nodes) {
        List<Integer> entry = new ArrayList<>(Arrays.asList(new Integer[n]));
        List<Integer> exit = new ArrayList<>(Arrays.asList(new Integer[n]));
        String[] color = new String[n];
        Arrays.fill(color, "white");
        int time = 0;
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> neighbors = buildDirectedLists(nodes, n);
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if ("white".equals(color[node])) {
                color[node] = "grey";
                stack.push(node);
                entry.set(node, time);
                time += 1;

                List<Integer> row = neighbors.get(node);
                for (int i = row.size() - 1; i >= 0; i--) {
                    if ("white".equals(color[row.get(i)])) {
                        stack.push(row.get(i));
                    }
                }
            } else if ("grey".equals(color[node])) {
                color[node] = "black";
                exit.set(node, time);
                time += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entry.size(); i++) {
            sb.append(entry.get(i)).append(" ").append(exit.get(i)).append("\n");
        }

        return sb.toString();
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
}
