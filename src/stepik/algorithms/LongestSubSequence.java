package stepik.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://stepik.org/lesson/13257/step/6?thread=solutions&unit=3442">...</a>
 */
public class LongestSubSequence {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        List<Integer> result = findLongestSubSequence(array);
        System.out.println(result.size());
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static List<Integer> findLongestSubSequence(int[] array) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> prev = new ArrayList<>();
        int n = array.length;
        int inf = 1_000_000_000;
        int[] length = new int[n + 2];
        Arrays.fill(length, -inf);
        length[0] = inf;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n + 1;
            while ((left + 1) < right) {
                int middle = (left + right) / 2;
                if (length[middle] >= array[i]) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
            length[right] = array[i];
            prev.add(List.of(right, i, array[i]));
        }

        int i = n + 1;
        while (length[i] == -inf) {
            i--;
        }
        int count = i;

        for (int k = prev.size() - 1; k >= 0; k--) {
            List<Integer> counts = prev.get(k);
            if (counts.get(0) == count) {
                result.add(counts.get(1) + 1);
                count--;
            }
        }

        Collections.reverse(result);
        return result;
    }
}
