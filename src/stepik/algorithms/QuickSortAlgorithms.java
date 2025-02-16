package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuickSortAlgorithms {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int j = 0; j < n; j++) {
            array[j] = scanner.nextInt();
        }
        quickSort(array, 0, n - 1);
        String sortedString = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(sortedString);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static void lomutoPartition(int[] arr, int low, int high) {
        int pivotValue = arr[low];
        int storeIndex = low + 1;

        for (int i = low + 1; i <= high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, low, storeIndex - 1);
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int storeIndex = low;

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, high);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
