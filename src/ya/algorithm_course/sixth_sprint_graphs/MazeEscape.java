package ya.algorithm_course.sixth_sprint_graphs;

import java.util.*;

public class MazeEscape {
    public static class Pair<U, V> {
        public U first;
        public V second;
        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    // The method to find the shortest distance to escape from maze
    public static Pair<Integer, Pair<Integer, Integer>> escapeFromMaze(int n, int m, int[][] field, Pair<Integer, Integer> startPoint) {
        int[][] prev = new int[n][m];
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(prev[i], -1);
            Arrays.fill(dist[i], n * m + 10);
        }

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        int[] horizontalMoves = {1, 0, -1, 0};
        int[] verticalMoves = {0, 1, 0, -1};

        queue.add(startPoint);
        dist[startPoint.first][startPoint.second] = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currentPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int new_i = currentPoint.first + horizontalMoves[i];
                int new_j = currentPoint.second + verticalMoves[i];

                if (new_i >= 0 && new_i < n && new_j >= 0 && new_j < m
                        && field[new_i][new_j] != 1 && dist[new_i][new_j] == n * m + 10) {
                    queue.add(new Pair<>(new_i, new_j));
                    prev[new_i][new_j] = currentPoint.first * m + currentPoint.second;
                    dist[new_i][new_j] = dist[currentPoint.first][currentPoint.second] + 1;

                    if (field[new_i][new_j] == 2) {
                        return new Pair<>(dist[new_i][new_j], new Pair<>(new_i, new_j));
                    }
                }
            }
        }

        return new Pair<>(n * m + 10, new Pair<>(-1, -1));
    }

    public static void main(String[] args) {
        int n = 5, m = 5;
        int[][] field = {
                {0, 0, 1, 0, 2},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        Pair<Integer, Integer> startPoint = new Pair<>(0, 0);

        Pair<Integer, Pair<Integer, Integer>> result = escapeFromMaze(n, m, field, startPoint);

        System.out.println("Distance: " + result.first);
        System.out.println("Escape point: (" + result.second.first + ", " + result.second.second + ")");
    }
}

