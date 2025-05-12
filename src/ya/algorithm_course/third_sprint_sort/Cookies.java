package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Cookies {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int kids = Integer.parseInt(reader.readLine());
            int[] greedinessPoint = new int[kids];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < kids; i++) {
                greedinessPoint[i] = Integer.parseInt(tokenizer.nextToken());
            }
            int cookiesNumber = Integer.parseInt(reader.readLine());
            Integer[] cookies = new Integer[cookiesNumber];
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < cookiesNumber; i++) {
                cookies[i] = Integer.parseInt(tokenizer.nextToken());
            }
            List<Integer> cookiesSorted = Arrays.stream(cookies).sorted().toList();
            System.out.println(howManyKidsSatisfied(greedinessPoint, cookiesSorted));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int howManyKidsSatisfied(int[] greeds, List<Integer> cookies) {
        int count = 0;
        List<Integer> copy = new ArrayList<>(cookies);

        for (int greed : greeds) {
            int index = hasCostInArray(copy, greed);
            if (index != -1) {
                copy.remove(index);
                count++;
            }
        }

        return count;
    }

    private static int hasCostInArray(List<Integer> arr, int cost) {
        int left = 0;
        int right = arr.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) >= cost) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
