package ya.algorithm_course.sixth_sprint_graphs.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * <a href="https://contest.yandex.ru/contest/25070/run-report/138904722/">...</a>
 /**
 * -- ПРИНЦИП РАБОТЫ --
 * Мой алгоритм работает по принципу алгоритма поиска Минимального остовного дерева Прима, только вместо выбора в пользу
 * минимального ребра между двумя ребрами, выбирается ребро с максимальным весом из двух на каждом шаге алгоритма. Для
 * поиска максимального я использую очередь с приоритетом, в которую передаю компаратор, который сортирует по убыванию.
 * В алгоритме использую класс Edge, которые имплементирует Comparable, чтобы не передавать кастомные методы сравнения
 * двух ребер графа.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Возьмем пример поиска максимального остовного дерева для второго тест-кейса в задаче:
 * 3 узла 3 ребра
 * от до вес
 * 1  2  1
 * 1  2  2
 * 2  3  1
 * Для удобства работы с индексами, сразу при чтении убираю единицу от чисел, чтобы не закладывать в списки фиктивный
 * элемент на нулевой индекс. Завожу список потенциально добавленных узлов и еще недобавленных. Завожу приоритетную
 * очередь и список, где будет храниться результат. Заполняем список недобавленных узлов от 0 до n - 1, чтобы в конце
 * проверить, все ли узлы обработаны, потому что в M(ax)ST должны попасть все узлы, поэтому если компонент связи больше,
 * чем одна, M(ax)ST построить невозможно и нужно выдать ошибку. Далее для корректной работы алгоритма нужно правильно
 * составить списки связности (buildUndirectedLists), которые я храню в мапе, хотя в целом можно было бы сделать и
 * список списков, но при заполнении мапы код немного короче.
 * <p>
 * При чтении данных я создала объекты только с односторонней связью, в методе получения связанных списков, я дополнительно
 * создаю обратную связь - создаю объект Edge, для которого from становится to предыдущего узла, а вес ребра такой же.
 * Так как ребро не меняется, меняются исходящие вершины.
 * <p>
 * В алгоритме Прима не важно с какой вершины начинать, поэтому я начинаю поиск с первой вершины (нулевой индекс). В
 * методе добавления, сначала добавляется вершина 1 и убирается из списка не добавленных, потом рассматриваются все
 * вершины, исходящие из этого узла и, если ребро не добавлено, т.е. есть в списке notAdded, оно добавляется в очередь.
 * В нашем случае есть два одинаковых ребра 2, с разным весом. В очередь попадают оба ребра, но так как сортировка идет
 * в обратном порядке, первым оказывается ребро с большим весом, т.е. 2. Следующим шагом мы смотрим, пока у нас есть, что
 * добавить и есть элемент в очереди мы добавляем его в результат, добавляем его в список added, методом, который удаляет
 * повторный элемент из notAdded, таким образом у нас в notAdded больше нет вершины 2.
 * <p>
 * Смотрим следующий элемент в очереди, ребро до вершины 2 с меньшим весом, его нет в списке notAdded, поэтому мы его
 * пропускаем. Идем в следующее ребро 3 с весом 1, добавляем его в результат и удаляем из notAdded. Проверяем, что
 * notAdded пуст и выдаем сумму M(ax)ST, 2 + 1 = 3.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Временная сложность алгоритма Прима с очередью с приоритетом O(|E| * log|V|), где |E| - количество ребер в графе, а
 * |V| - количество вершин.
 * <b>Промежуточные и вспомогательные вычисления</b>:
 * Считывание данных - O(n), где n - количество данных. Добавление в notAdded - O(E) - где Е количество узлов.
 * Построение списков смежности также O(E). В методе addVertex происходит обход всех исходящих ребер, что является O(V),
 * где V - количество ребер. В методе while уже работает алгоритм Прима. В самом конце идет сумма всех чисел в списке,
 * где хранится результат, сложность которого будет O(E) - 1, так как MST не имеет цикл, будет на одно ребро | вес меньше.
 * Итого общая временная сложность будет:
 * O(n) + 2 * O(E) + O(V) + O(|E| * log|V|) + O(E) - 1.
 * Отбрасывая константы итоговая сложность равна O(|E| * log|V|).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * При считывании данных выделяется константная память под список ребер O(|E|), при поиске MST два множества добавленных
 * и не добавленных вершин O(|V|), очередь с приоритетами, которая в худшем случае может содержать O(|E|) ребер,
 * список результат, мапа для списков смежности, кастомный объект Edge. Добавление и удаления из сетов константное,
 * проверка по compareTo константная. Не учитывая константы O(|E|) + O(|V|) будет итоговой пространственной сложностью.
 */
public class MaxSpanningTree {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
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

            Integer result = printMaxWeight(pairs, n);
            if (result != -1) {
                System.out.println(result);
            } else {
                System.out.println("Oops! I did it again");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Integer printMaxWeight(List<Edge> nodes, int n) {
        Set<Integer> added = new HashSet<>();
        Set<Integer> notAdded = new HashSet<>();
        Queue<Edge> queue = new PriorityQueue<>(Comparator.reverseOrder());

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            notAdded.add(i);
        }

        Map<Integer, List<Edge>> indexToEdges = buildUndirectedLists(nodes);

        addVertex(0, added, notAdded, queue, indexToEdges.getOrDefault(0, Collections.emptyList()));

        while (!notAdded.isEmpty() && !queue.isEmpty()) {
            Edge node = queue.poll();
            if (notAdded.contains(node.getTo())) {
                result.add(node.getWeight());
                addVertex(node.getTo(), added, notAdded, queue, indexToEdges.getOrDefault(node.getTo(), Collections.emptyList()));
            }
        }

        if (!notAdded.isEmpty()) {
            return -1;
        }

        return result.stream().mapToInt(i -> i).sum();
    }

    private static void addVertex(int v, Set<Integer> added, Set<Integer> notAdded, Queue<Edge> queue, List<Edge> edge) {
        added.add(v);
        notAdded.remove(v);

        for (Edge i : edge) {
            if (notAdded.contains(i.getTo())) {
                queue.add(i);
            }
        }
    }

    private static Map<Integer, List<Edge>> buildUndirectedLists(List<Edge> nodes) {
        Map<Integer, List<Edge>> result = new HashMap<>();
        for (Edge edge : nodes) {
            result.computeIfAbsent(edge.getFrom(), v -> new ArrayList<>()).add(edge);
            result.computeIfAbsent(edge.getTo(), v -> new ArrayList<>()).add(new Edge(edge.getTo(), edge.getFrom(), edge.getWeight()));
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
