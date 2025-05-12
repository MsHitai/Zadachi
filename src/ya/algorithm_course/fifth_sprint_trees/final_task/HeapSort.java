package ya.algorithm_course.fifth_sprint_trees.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://contest.yandex.ru/contest/24810/run-report/138170778/">...</a>
 /**
 * -- ПРИНЦИП РАБОТЫ --
 * В теории нам давалась формула, где на 0 индексе находился фиктивный элемент, т.е. корень кучи находился на 1 индексе.
 * Я решила реализовать с 0 индексом и нашла другие формулы для нахождения левого и правого поддерева и родителя в
 * википедии - <a href="https://en.wikipedia.org/wiki/Binary_heap#Heap_implementation">...</a>. В остальном, алгоритм
 * сортировки совпадает с тем, который дан в теории.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим массив из трех участников - alla 4 100, gena 6 1000, gosha 2 90. Сортировка от большего количества очков,
 * меньшего штрафа, либо по алфавиту. Ожидаемый порядок - gena 6 1000, alla 4 100, gosha 2 90. Запускаем метод heapSort.
 * <p>
 * Первый шаг в пирамидальной сортировке - создание пустой бинарной неубывающей кучи. И по очереди добавляем туда
 * участников. Первый участник в списке Алла. Для кучи, где голова находится на 0 индексе, для первого элемента не
 * происходит проверок, участник просто добавляется. Далее идет Гена. Пытаемся добавить его в конец кучи (длина нового
 * списка 2, поэтому индекс конца кучи будет длина минус 1, т.е. 1), но перед этим проверяем с родителем, не "больше"
 * ли этот участник. Вычисляем индекс родителя: (1 - 1) / 2 = 0. Проверяем, Гена больше Аллы и кладем ее на вершину кучи.
 * Далее рассмотрим участника Гошу. Пытаемся положить его по индексу 3 - 1 = 2. Вычисляем индекс родителя: (2 - 1) / 2 = 0.
 * По нулевому индексу у нас лежит "максимальный" участник, поэтому менять местами участников не будем.
 * <p>
 * Следующий шаг - извлечение наиболее приоритетных элементов и удаление их из кучи. Метод popMax сначала берет первого
 * участника (корень кучи), который сейчас у нас является максимальным элементом, Гена. Потом удаляет его из кучи.
 * На 0 элементе у нас теперь Гоша, а на первом Алла. Смотрим на левое и правое поддерево. Индекс корня = 0. Левый
 * ребенок по индексу = 2 * 0 + 1 = 1. Правый ребенок = 2. Так как, 2 не меньше длины списка, наибольший индекс у нас
 * будет 1 (левого ребенка). Сравниваем "детей". Левый, Алла, с количеством решенных задач 4, должна стоять выше Гоши,
 * с количеством решенных задач 2, поэтому меняем их местами. Теперь куча отсортирована, по очереди достаем последних
 * участников и выдаем результат = Гена, Алла, Гоша. Порядок совпадает с ожидаемым.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм соответствует стандартному алгоритму полимидальной сортировки. Сложность которого составляет в худшем случае
 * O (n log n) - выделение памяти под новую кучу O(1), вставка n-элементов подряд в бинарную кучу O(log n), извлечение из
 * кучи с последующим удалением также составляет O(log n). Итого получаем:
 * O(1) + O(log n) + O(log n) = O(log n).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Для удобства сравнения, я использую класс Person с методом isGreater, который занимает хоть и большее количество
 * памяти, чем примитивные типы, но относится к константному. Проверки внутри isGreater также занимают O(1) памяти.
 * Так как у нас рекурсивный запуск функции siftUp и siftDown, на стеке вызовов хранятся в памяти все незавершенные
 * функции со своим состоянием. Таких вызовов может быть O(log n) в худшем случае. Также выделяем память под массив,
 * считывая данные, копируем его в список (Arrays.asList(array)), что занимает O(n) памяти по количеству элементов.
 * Выделяем память под кучу и конечный результат (sortedArray). Таким образом, вместе со считыванием данных, обработкой и
 * выводом результатов, итоговая пространственная сложность O(n).
 */
public class HeapSort {

    public static void siftDown(List<Person> heap, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left >= heap.size()) {
            return;
        }

        int indexLargest = left;
        if (right < heap.size() && heap.get(right).isGreater(heap.get(left))) {
            indexLargest = right;
        }

        if (heap.get(indexLargest).isGreater(heap.get(index))) {
            Person temp = heap.get(index);
            heap.set(index, heap.get(indexLargest));
            heap.set(indexLargest, temp);
            siftDown(heap, indexLargest);
        }
    }

    public static void siftUp(List<Person> heap, int index) {
        if (index == 0) {
            return;
        }

        int parentIndex = (index - 1) / 2;

        if (heap.get(index).isGreater(heap.get(parentIndex))) {
            Person temp = heap.get(parentIndex);
            heap.set(parentIndex, heap.get(index));
            heap.set(index, temp);
            siftUp(heap, parentIndex);
        }

    }

    public static void heapAdd(List<Person> heap, Person key) {
        heap.add(key);
        int index = heap.size() - 1;
        siftUp(heap, index);
    }

    public static Person popMax(List<Person> heap) {
        Person result = heap.getFirst();
        heap.set(0, heap.getLast());
        heap.removeLast();
        siftDown(heap, 0);
        return result;
    }

    public static List<Person> heapSort(List<Person> array) {
        List<Person> heap = new ArrayList<>();

        for (Person person : array) {
            heapAdd(heap, person);
        }

        List<Person> sortedArray = new ArrayList<>();
        while (!heap.isEmpty()) {
            Person max = popMax(heap);
            sortedArray.add(max);
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            Person[] participants = new Person[n];
            StringTokenizer tokenizer;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                Person person = new Person();
                person.name = tokenizer.nextToken();
                person.score = Integer.parseInt(tokenizer.nextToken());
                person.fine = Integer.parseInt(tokenizer.nextToken());
                participants[i] = person;
            }
            List<Person> result = heapSort(Arrays.asList(participants));
            System.out.println(result.stream()
                    .map(p -> p.name)
                    .collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static class Person {
        String name;
        int score;
        int fine;

        @Override
        public String toString() {
            return name + '\n';
        }

        public boolean isGreater(Person other) {
            if (this.score > other.score) {
                return true;
            } else if (this.score == other.score) {
                if (this.fine < other.fine) {
                    return true;
                } else if (this.fine == other.fine) {
                    return this.name.compareTo(other.name) < 0;
                }
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return score == person.score
                    && fine == person.fine
                    && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score, fine);
        }
    }
}
