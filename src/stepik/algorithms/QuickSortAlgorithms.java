package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Быстрая сортировка со случайным опорным элементом и разбиением на три части (Дейкстра, "голландский флаг"):
 * [ < pivot | == pivot | > pivot ]. Равные опорному элементы больше не участвуют в рекурсии, поэтому
 * массив из одинаковых чисел сортируется за O(n), а не за O(n^2), как в разбиении Ломуто.
 * Среднее время O(n log n), глубина рекурсии O(log n): рекурсия идёт в меньшую часть, большая — в цикле.
 * Задача для практики: LeetCode 912 "Sort an Array", LeetCode 75 "Sort Colors" (то же разбиение).
 */
public class QuickSortAlgorithms {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int j = 0; j < n; j++) {
            array[j] = scanner.nextInt();
        }
        quickSort(array, 0, n - 1);
        System.out.println(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pivot = arr[ThreadLocalRandom.current().nextInt(low, high + 1)];
            int lt = low;   // arr[low..lt-1] < pivot
            int i = low;    // arr[lt..i-1] == pivot
            int gt = high;  // arr[gt+1..high] > pivot
            while (i <= gt) {
                if (arr[i] < pivot) {
                    swap(arr, lt++, i++);
                } else if (arr[i] > pivot) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            if (lt - low < high - gt) {
                quickSort(arr, low, lt - 1);
                low = gt + 1;
            } else {
                quickSort(arr, gt + 1, high);
                high = lt - 1;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
