package test.assignments;

import java.util.Arrays;

public class Merger {

    public static void main(String[] args) {
        Merger merger = new Merger();
        int[] array = new int[]{5, 2, 4, 6, 1, 3, 2, 6};

        merger.sort(array, 0, 7);

        System.out.println(Arrays.toString(array));
    }

    private void merge(int[] array, int beginIndex, int endIndex, int middle) {
        int leftLength = endIndex - beginIndex + 1;
        int rightLength = middle - endIndex;

        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        // Копируем значения во временные массивы
        System.arraycopy(array, beginIndex, leftArray, 0, leftLength);
        for (int j = 0; j < rightLength; j++) {
            rightArray[j] = array[endIndex + 1 + j];
        }

        int i = 0; // Индекс для массива leftArray
        int j = 0; // Индекс для массива rightArray
        int k = beginIndex; // Индекс для основного массива array

        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из leftArray, если такие есть
        while (i < leftLength) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Копируем оставшиеся элементы из rightArray, если такие есть
        while (j < rightLength) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private void sort(int[] array, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            int middle = (beginIndex + endIndex) / 2;
            sort(array, beginIndex, middle);
            sort(array, middle + 1, endIndex);
            merge(array, beginIndex, middle, endIndex);
        }
    }
}
