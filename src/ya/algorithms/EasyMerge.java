package ya.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EasyMerge {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array1 = new int[n];
        int[] array2 = new int[n];
        for (int i = 0; i < n; i++) {
            array1[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            array2[i] = scanner.nextInt();
        }
        int[] result = mergeArrays(array1, array2, n);
        String res = Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(res);
    }

    private static int[] mergeArrays(int[] array1, int[] array2, int n) {
        int j = 0;
        int[] result = new int[2 * n];
        for (int i = 0; i < array1.length; i++) {
            result[j++] = array1[i];
            result[j++] = array2[i];
        }
        return result;
    }
}
