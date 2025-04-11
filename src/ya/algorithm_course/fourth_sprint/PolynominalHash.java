package ya.algorithm_course.fourth_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class PolynominalHash {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int base = Integer.parseInt(reader.readLine());
            int module = Integer.parseInt(reader.readLine());
            String string = reader.readLine();
            long hash = getPolynominalHash(string, base, module);
            System.out.println(generateSameStringHash(hash, base, module));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String generateSameStringHash(long hash, int base, int module) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 21;
        long secondHash = 0;
        String result = null;
        Random random = new Random();
        while (secondHash != hash) {
            result = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            secondHash = getPolynominalHash(result, base, module);
        }

        return result;
    }

    private static long getPolynominalHash(String value, int base, int module) {
        long hash = 0;
        if (value.isEmpty()) {
            return hash;
        }
        int current;
        for (int i = 0; i < value.length(); i++) {
            current = value.charAt(i);
            hash = (hash * base + current) % module;
        }

        return hash;
    }
}
