package ya.algorithm_course.third_sprint_sort;

import java.util.Arrays;

public class MakeMergeSort {

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] result = new int[right - left];
        int i = left, j = mid, k = 0;

        while (i < mid && j < right) {
            if (arr[i] <= arr[j]) {
                result[k++] = arr[i++];
            } else {
                result[k++] = arr[j++];
            }
        }

        while (i < mid) {
            result[k++] = arr[i++];
        }
        while (j < right) {
            result[k++] = arr[j++];
        }

        return result;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left < 2 || right > arr.length) {
            return;
        }
        int middle = (left + right) / 2;

        merge_sort(arr, left, middle);
        merge_sort(arr, middle,right);

        int[] result = merge(arr, left, middle, right);
        System.arraycopy(result, 0, arr, left, result.length);
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        System.out.println(Arrays.equals(b, expected));
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        System.out.println(Arrays.equals(c, expected2));
        System.out.println(Arrays.toString(c));
    }
}
