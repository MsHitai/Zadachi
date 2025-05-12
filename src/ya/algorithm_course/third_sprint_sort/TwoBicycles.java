package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TwoBicycles {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int days = Integer.parseInt(reader.readLine());
            int[] money = new int[days];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < days; i++) {
                money[i] = Integer.parseInt(tokenizer.nextToken());
            }
            int cost = Integer.parseInt(reader.readLine());
            int one = findEarliestDay(money, cost, 0);
            int two = -1;
            if (one != -1) {
                two = findEarliestDay(money, cost * 2, one);
            }
            System.out.println(String.join(" ", String.valueOf(one), String.valueOf(two)));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int whenCanBuyTwo(int[] arr, int cost, int left, int right) {
        int best = Integer.MAX_VALUE - 1;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (cost <= arr[mid]) {
                if (mid < best) {
                    best = mid;
                }
                right--;
            } else {
                left++;
            }
        }
        return best + 1;
    }

    private static int findEarliestDay(int[] arr, int cost, int start) {
        int left = start;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= cost) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result != -1 ? result + 1 : result;
    }

    public static int binarySearch(int[] arr, int x, int left, int right) {
        if (right <= left) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == x) {
            return mid;
        } else if (x <= arr[mid]) {
            return binarySearch(arr, x, left, mid);
        } else {
            return binarySearch(arr, x, mid + 1, right);
        }
    }
}
