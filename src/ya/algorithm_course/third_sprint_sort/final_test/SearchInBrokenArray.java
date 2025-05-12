package ya.algorithm_course.third_sprint_sort.final_test;

/**
 * <a href="https://contest.yandex.ru/contest/23815/run-report/135944851/">...</a>
 */

/**
 * -- ПРИНЦИП РАБОТЫ --
 * В массиве использовался кольцевой буфер - значит есть точка, если разделить по которой мы получим два отсортированных
 * массива. Например, для {19, 21, 100, 101, 1, 4, 5, 7, 12} это будет между 101 и 1. Оба массива - {19, 21, 100, 101} и
 * {1, 4, 5, 7, 12} осортированы по неубыванию. Ключевой момент определить нужную половину для поиска.
 * <p>
 * Поэтому в коде есть два главных условия (после проверки, что по середине мы не попали в нужный элемент). Первое
 * проверяет отсортирована ли половина от левого индекса до середины, а второе - отсортирована ли правая половина.
 * <p>
 * В каждом из этих условий есть вложенные проверки находится ли х в отсортированной части и если да, мы запускаем поиск
 * в этой половине, иначе в другой.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Рассмотрим массив {19, 21, 100, 101, 1, 4, 5, 7, 12} и попробуем найти 101. При первом запуске left = 0, right = 8,
 * mid = (0 + 8) / 2 = 4. Проверяем находится ли искомый элемент по mid, ответ нет, так что идем дальше.
 * Проверка отсортирован ли массив в левой части от 0 до 4 - ответ нет, так как 1 меньше 101. Идем в ветку иначе.
 * Проверяем входит ли в правый диапазон 101, ответ нет, справа елементы все меньше. Переходим в ветку иначе и ищем в
 * левой половине. Рассчитываем mid = (0 + 4) / 2 = 2. Смотрим, отсортирована ли эта часть массива (19, 21, 100). Да.
 * Дальше проверяем, лежит ли 101 в этой части. Ответ нет, переходим в иначе и продолжаем поиск правее от mid + 1 до 8.
 * Находим середину, mid = (3 + 4) / 2 = 3. Проверяем, является ли элемент по индексу 3 искомым. Ответ да.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм соответствует стандартному двоичному поиску с сокращением границ поиска в два раза при каждой итерации и
 * составляет O(log n). Проверка в if-else константная, таким образом итоговая сложность O(log n).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Присутствуют 3 переменных типа int, то есть занимают фиксированное значение памяти - каждый по 4 байта.
 * Другими словами, значения не зависят от входных данных и занимают O(1) памяти. Так как у нас рекурсивный запуск функции,
 * на стеке вызовов хранятся в памяти все незавершенные функции со своим состоянием. Таких вызовов может быть O(log n)
 * Поэтому можно сказать, что итоговая пространственная сложность O(log n).
 */
public class SearchInBrokenArray {

    public static int brokenSearch(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        return binarySearch(arr, k, left, right);
    }

    public static int binarySearch(int[] arr, int x, int left, int right) {
        if (right < left || right >= arr.length || left < 0) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (arr[mid] == x) {
            return mid;
        }

        if (arr[left] <= arr[mid]) {
            if (x >= arr[left] && x <= arr[mid]) {
                return binarySearch(arr, x, left, mid);
            } else {
                return binarySearch(arr, x, mid + 1, right);
            }
        } else {
            if (x >= arr[mid] && x <= arr[right]) {
                return binarySearch(arr, x, mid + 1, right);
            } else {
                return binarySearch(arr, x, left, mid);
            }
        }
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        int[] arr2 = {5, 1};
        System.out.println(6 == brokenSearch(arr, 5));
        System.out.println(2 == brokenSearch(arr, 100));
        System.out.println(8 == brokenSearch(arr, 12));
        System.out.println(0 == brokenSearch(arr, 19));
        System.out.println(1 == brokenSearch(arr, 21));
        System.out.println(4 == brokenSearch(arr, 1));
        System.out.println(5 == brokenSearch(arr, 4));
        System.out.println(7 == brokenSearch(arr, 7));
        System.out.println(3 == brokenSearch(arr, 101));
        System.out.println(0 == brokenSearch(arr2, 5));
        System.out.println(1 == brokenSearch(arr2, 1));
    }

    public static void main(String[] args) {
        test();
    }
}
