package ya.algorithm_course.third_sprint.final_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * <a href="https://contest.yandex.ru/contest/23815/run-report/135998577/">...</a>
 */

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Для этого решения использовала идею разбиения Ломуто - <a href="https://ru.wikipedia.org/wiki/%D0%91%D1%8B%D1%81%D1%82%D1%80%D0%B0%D1%8F_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0">...</a>
 * В качестве опорного элемента сначала выбирается случайны элемент, а потом pivot ставится в конец отрезка, согласно
 * идее Ломуто. Согласно алгоритму Ломуто, когда находится элемент меньший или равный опорному, индекс увеличивается и
 * элемент вставляется перед опорным, по нашей логике сортировка должна быть не по возрастанию, а по убыванию согласно
 * максимальному кол-ву очков, меньшего штрафа и алфавитного порядка.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим массив из трех участников - alla 4 100, gena 6 1000, gosha 2 90. Сортировка от большего количества очков,
 * меньшего штрафа, либо по алфавиту. Ожидаемый порядок - gena 6 1000, alla 4 100, gosha 2 90. Запускаем метод quickSort.
 * <p>
 * Левый индекс равен 0, правый 2. Рассчитываем индекс для опорного элемента = 0 + (случайное число 0.0 до 1.0) * (2 - 0 + 1) = 0.
 * Берем участника по этому индексу и ставим его на последнее место в массиве. Участник под индексом 0 - это Алла.
 * Меняем местами ее с Гошей и получаем массив - Гоша, Гена, Алла. Запоминаем левый индекс в переменной storeIndex и
 * идем циклом от левого индекса до правого, находя бОльший элемент. Первый проход - Гоша и Алла, у Гоши меньше очков,
 * в условие не заходим. Далее, Гена, у него максимальное кол-во очков, заходим в условие и меняем местами элементы -
 * storeIndex равен left, то есть 0, так что Гена встает на первое место в массиве. StoreIndex увелчивается на единицу.
 * (Получаем порядок Гена, Гоша, Алла).
 * <p>
 * Выходя из цикла происходит снова обмен элементов, storeIndex после увелечения равен 1, на 1 индекс ставим Аллу,
 * получаем массив Гена, Алла, Гоша. Вышли из метода partition, пошли в первый рекурсивный случай с left = 0,
 * pivotIndex = 1 - 1 = 0. Проверка "левый индекс меньше правого" не проходит, не заходим внутрь метода.
 * Второй рекурсивный случай, pivotIndex = 1 + 1 = 2, right = 2. Снова индексы равны, выходим из метода.
 * Смотрим результат - порядок верный.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм соответствует стандартному алгоритму quickSort in-place, сложность которого в среднем случае O(n log n), в
 * худшем случае O(n^2), если выбирается индекс не случайным образом. В нашем случае эта вероятность минимизирована, т.к.
 * используется формула с применением случайного значения с помощью Math.random().
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Для удобства сравнения, я использую класс Person с методом isGreater, который занимает хоть и большее количество
 * памяти, чем примитивные типы, но относится к константному. Храним pivotIndex и temp в методе swap, которые также
 * занимают O(1) памяти. Проверки внутри isGreater также занимают O(1) памяти. Так как у нас рекурсивный запуск функции,
 * на стеке вызовов хранятся в памяти все незавершенные функции со своим состоянием. Таких вызовов может быть O(log n) в
 * среднем, и O(n) в худшем случае. По отношению к quickSort принято считать средний случай, поэтому итоговая пространственная
 * сложность - O(log n).
 */
public class EffectiveQuicksort {

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
            quickSort(participants, 0, n - 1);
            System.out.println(Arrays.stream(participants).map(p -> p.name).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void quickSort(Person[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(Person[] arr, int left, int right) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        Person pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i].isGreater(pivotValue)) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(Person[] arr, int left, int right) {
        Person temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    static class Person {
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
            return score == person.score && fine == person.fine && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score, fine);
        }
    }
}
