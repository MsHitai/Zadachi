package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SequenceKTimes {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] line = reader.readLine().split(" ");
            int goalLength = Integer.parseInt(line[0]);
            int kTimes = Integer.parseInt(line[1]);
            String input = reader.readLine();
            System.out.println(getPositions(input, goalLength, kTimes).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
            System.out.println(findIndexesOfCommonSubstrings(input, goalLength, kTimes));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String findIndexesOfCommonSubstrings(String input, int goalLength, int kTimes) {
        StringBuilder sb = new StringBuilder();
        Map<Long, Count> counts = new HashMap<>();
        int base = 31;
        int modulus = (int) (Math.pow(10, 9) + 7);
        int n = input.length();
        long[] hashes = new long[n];
        hashes[0] = input.charAt(0) % modulus;
        for (int i = 1; i < n; i++) {
            hashes[i] = (hashes[i - 1] * base + input.charAt(i)) % modulus;
        }
        long[] powers = new long[n + 1];
        powers[0] = 1;
        for (int i = 1; i <= n; i++) {
            powers[i] = (powers[i - 1] * base) % modulus;
        }
        long hash;

        for (int i = 0; i < n; i++) {
            if (i + goalLength < n) {
                long part1 = hashes[i + goalLength - 1];
                long part2 = (hashes[i] * powers[(i + goalLength - 1) - i]) % modulus;
                hash = (part1 - part2) % modulus;
                if (!counts.containsKey(hash)) {
                    Count count = new Count(1, i);
                    counts.put(hash, count);
                } else {
                    Count count = counts.get(hash);
                    count.count++;
                }
            }
        }

        for (Long h : counts.keySet()) {
            if (counts.get(h).count >= kTimes) {
                sb.append(counts.get(h).firstIndex).append(" ");
            }
        }

        return sb.toString();
    }

    private static List<Integer> getPositions(String str, int n, int k) {
        long base = 345;
        long mod = 5608713984039443L;
        Map<Long, Integer> counter = new HashMap<>();
        Map<Long, Integer> hashToPos = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        long hash = getHash(str.substring(0, n), base, mod);
        long power = getPower(n - 1, base, mod);

        counter.put(hash, 1);
        hashToPos.put(hash, 0);

        for (int i = 1; i + n <= str.length(); i++) {
            hash = (hash + mod - ((long) str.charAt(i - 1) * power % mod)) % mod;
            hash = (hash * base) % mod;
            hash = (hash + str.charAt(i + n - 1)) % mod;

            counter.put(hash, counter.getOrDefault(hash, 0) + 1);

            if (counter.get(hash) == 1) {
                hashToPos.put(hash, i);
            }

            if (counter.get(hash) == k) {
                result.add(hashToPos.get(hash));
            }
        }

        return result;
    }

    private static long getHash(String str, long base, long mod) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * base % mod + str.charAt(i)) % mod;
        }
        return hash;
    }

    private static long getPower(int n, long base, long mod) {
        long power = 1;
        for (int i = 0; i < n; i++) {
            power = (power * base) % mod;
        }
        return power;
    }

    public static class Count {
        int count;
        int firstIndex;

        public Count(int count, int firstIndex) {
            this.count = count;
            this.firstIndex = firstIndex;
        }
    }
}