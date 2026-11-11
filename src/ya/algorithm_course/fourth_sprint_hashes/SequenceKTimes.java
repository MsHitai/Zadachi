package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Частый поиск: найти все подстроки длины n, встречающиеся не менее k раз, и вывести позиции их первых вхождений.
 * <p>
 * Скользящий полиномиальный хеш (Rabin–Karp): хеш следующего окна получаем из предыдущего за O(1).
 * Итого O(|s|) времени и памяти. Тот же приём — LeetCode 187 "Repeated DNA Sequences" и 1044.
 * Модуль ~5.6e15 и основание 345 выбраны так, чтобы hash * base помещался в long (без переполнения).
 */
public class SequenceKTimes {

    private static final long BASE = 345;
    private static final long MOD = 5_608_713_984_039_443L;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int length = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());
            String input = reader.readLine();
            System.out.println(getPositions(input, length, k).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    static List<Integer> getPositions(String s, int n, int k) {
        List<Integer> result = new ArrayList<>();
        if (n > s.length()) {
            return result;
        }
        Map<Long, Integer> counter = new HashMap<>();
        Map<Long, Integer> firstPosition = new HashMap<>();

        long hash = 0;
        long power = 1; // BASE^(n-1) — вес первого символа окна
        for (int i = 0; i < n; i++) {
            hash = (hash * BASE + s.charAt(i)) % MOD;
            if (i > 0) {
                power = power * BASE % MOD;
            }
        }

        for (int start = 0; ; start++) {
            int count = counter.merge(hash, 1, Integer::sum);
            firstPosition.putIfAbsent(hash, start);
            if (count == k) {
                result.add(firstPosition.get(hash));
            }
            if (start + n >= s.length()) {
                break;
            }
            hash = (hash + MOD - s.charAt(start) * power % MOD) % MOD;
            hash = (hash * BASE + s.charAt(start + n)) % MOD;
        }
        return result;
    }
}
