package tinkoff;

import java.util.Scanner;

/**
 * Сколько раз можно составить слово "sheriff" из букв строки (каждую букву используем один раз).
 * Ответ — min по буквам (сколько есть / сколько нужно в слове): буква 'f' нужна дважды.
 * Аналог LeetCode 1189 "Maximum Number of Balloons". Время O(n), память O(1).
 */
public class Sheriff {

    private static final String SHERIFF = "sheriff";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(maxTimes(scanner.nextLine()));
    }

    static int maxTimes(String line) {
        int[] have = new int[26];
        int[] need = new int[26];
        for (char c : line.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                have[c - 'a']++;
            }
        }
        for (char c : SHERIFF.toCharArray()) {
            need[c - 'a']++;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (need[i] > 0) {
                result = Math.min(result, have[i] / need[i]);
            }
        }
        return result;
    }
}
