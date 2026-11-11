package ya.algorithm_course.third_sprint_sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Печеньки: сколько детей можно накормить, если ребёнку подходит печенька размера >= его жадности
 * (LeetCode 455 "Assign Cookies").
 * <p>
 * Жадно: сортируем оба массива и двумя указателями отдаём самому нежадному ребёнку самую маленькую подходящую
 * печеньку. O(n log n + m log m).
 */
public class Cookies {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int kids = Integer.parseInt(reader.readLine().trim());
            int[] greed = readArray(reader.readLine(), kids);
            int cookiesNumber = Integer.parseInt(reader.readLine().trim());
            int[] cookies = readArray(reader.readLine(), cookiesNumber);
            System.out.println(findContentChildren(greed, cookies));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int[] readArray(String line, int size) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return result;
    }

    static int findContentChildren(int[] greed, int[] cookies) {
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int child = 0;
        for (int cookie = 0; cookie < cookies.length && child < greed.length; cookie++) {
            if (cookies[cookie] >= greed[child]) {
                child++;
            }
        }
        return child;
    }
}
