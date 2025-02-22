package ya.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class AverageWindow {

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream("input.txt"))) {
            int n = readInt(reader);
            int[] queries = new int[n];
            for (int i = 0; i < n; i++) {
                queries[i] = readInt(reader);
            }
            int k = readInt(reader);
            System.out.println(movingAverage(queries, k));
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    private static String movingAverage(int[] queries, int k) {
        StringBuilder result = new StringBuilder();
        long currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += queries[i];
        }
        formatAverage(currentSum, k, result);

        for (int i = 0; i < queries.length - k; i++) {
            currentSum -= queries[i];
            currentSum += queries[i + k];
            formatAverage(currentSum, k, result);
        }

        if (!result.isEmpty()) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    private static void formatAverage(long sum, int k, StringBuilder sb) {
        if (sum % k == 0) {
            sb.append(sum / k).append(' ');
        } else {
            double avg = (double) sum / k;
            sb.append(avg).append(' ');
        }
    }

    /**
     * Быстрое чтение цифр из большого файла
     *
     * @param in {@link InputStream}
     * @return {@link int}
     * @throws IOException при ошибке чтения из файла
     */
    private static int readInt(InputStream in) throws IOException {
        int result = 0;
        boolean digitEncountered = false;

        for (int c; (c = in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                digitEncountered = true;
                result = result * 10 + c - '0';
            } else if (digitEncountered) break;
        }

        return result;
    }
}
