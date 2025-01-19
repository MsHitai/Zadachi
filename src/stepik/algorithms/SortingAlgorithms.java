package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortingAlgorithms {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        mergeSort(array, array.length);
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void mergeSort(int[] array, int arrayLength) {
        if (arrayLength < 2) {
            return;
        }
        int middle = array.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arrayLength - middle];

        System.arraycopy(array, 0, left, 0, middle);
        if (arrayLength - middle >= 0) System.arraycopy(array, middle, right, 0, arrayLength - middle);
        mergeSort(left, middle);
        mergeSort(right, arrayLength - middle);

        merge(array, left, right, middle, arrayLength - middle);
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }

    private static void selectionSort(int[] array) {
        int min;
        int temp;
        int minIndex;
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = min;
            array[minIndex] = temp;
        }
    }
}
