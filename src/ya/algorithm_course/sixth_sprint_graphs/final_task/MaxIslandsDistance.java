package ya.algorithm_course.sixth_sprint_graphs.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://contest.yandex.ru/contest/25070/run-report/138997098/">...</a>
 /**
 * -- ПРИНЦИП РАБОТЫ --
 * Алгоритм берет за основу метод обхода лабиринта из теории, с разницей, что каждая клетка определяется, как узел графа,
 * а соседняя земля (по карте вправо, влево, вверх, либо вниз) смежным узлом. Если таковой не находится, значит это
 * отдельный компонент связности. Идя по каждой клетке карты, проверяем, если это земля и мы этот узел еще не посещали,
 * то смотрим, есть ли у этого узла смежный узел и считаем его длину, помечая после посещения узлы, чтобы больше в них не
 * заходить.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Возьмем пример первого тест-кейса в задаче:
 * #  .  #
 * .  #  .
 * #  .  #
 * Идем по карте и заходим в первую клетку. Это участок земли, который еще не посещали, смотрим есть ли у узла соседи
 * и прибавляем счетчик количества островов на единицу. Проходим по направлениям вправо и вниз (остальные варианты будут
 * за пределами массива), вокруг вода, значит узел отдельная компонента связности, отправляем размер острова 1. Повторяем
 * для остальных участков земли, итого максимальный размер не изменился, а количество островов стало 5.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * У нас карта это двумерный массив, обход по которому составляет O (n * m) - количество столбцов умноженное на количество
 * строк. Далее мы заходим в каждый узел земли один раз, сразу помечая его посещенным, и далее смотрим в 4 направления,
 * смотря на возможных соседей. Отбрасывая константу 4, сложность этого действия - O(E) - где Е, количество смежных узлов.
 * Итого получается сложность алгоритма, без считывания данных, O (n * m) + O(E).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Размер карты O (n * m), в самом худшем случае, если все клетки будут землей, в очереди в bfs будет O (n * m) элементов,
 * не учитывая константное выделение памяти под список результата с двумя переменными, кастомный класс Coordinate и
 * промежуточные переменные для счетчиков и массивов движения, итоговая пространственная сложность будет O (n * m).
 */
public class MaxIslandsDistance {

    public static final String LAND = "#";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            List<List<String>> graph = new ArrayList<>();
            String[] line;
            for (int i = 0; i < n; i++) {
                line = reader.readLine().split("");
                List<String> list = new ArrayList<>(Arrays.asList(line).subList(0, m));
                graph.add(list);
            }

            System.out.println(findMaxDistance(n, m, graph).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<Integer> findMaxDistance(int n, int m, List<List<String>> map) {
        List<Integer> islandsToDistance = new ArrayList<>();

        boolean[][] visited = new boolean[n][m];
        int islandCount = 0;
        int maxIslandSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map.get(i).get(j).equals(LAND) && !visited[i][j]) {
                    islandCount++;
                    int size = bfs(i, j, map, visited, n, m);
                    maxIslandSize = Math.max(maxIslandSize, size);
                }
            }
        }

        islandsToDistance.add(islandCount);
        islandsToDistance.add(maxIslandSize);

        return islandsToDistance;
    }

    private static int bfs(int startX, int startY, List<List<String>> map, boolean[][] visited, int n, int m) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(startX, startY));
        visited[startX][startY] = true;
        int size = 0;

        int[] horizontalMoves = {1, -1, 0, 0};
        int[] verticalMoves = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            size++;

            for (int move = 0; move < 4; move++) {
                int newX = current.getX() + horizontalMoves[move];
                int newY = current.getY() + verticalMoves[move];

                if (newX >= 0 && newX < n && newY >= 0
                        && newY < m
                        && map.get(newX).get(newY).equals(LAND)
                        && !visited[newX][newY]) {
                    queue.add(new Coordinate(newX, newY));
                    visited[newX][newY] = true;
                }
            }
        }
        return size;
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
