package ya.algorithm_course.seventh_sprint_dynamic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GoldenRage {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int m = Integer.parseInt(reader.readLine());
            int n = Integer.parseInt(reader.readLine());
            List<Golden> goldens = new ArrayList<>(n);
            StringTokenizer tokenizer;
            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int cost = Integer.parseInt(tokenizer.nextToken());
                int kg = Integer.parseInt(tokenizer.nextToken());
                goldens.add(new Golden(cost, kg));
            }

            System.out.println(findMaxSum(goldens, m));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long findMaxSum(List<Golden> goldens, int canFit) {
        long greedyMax = 0;
        long currentLoad = 0;
        long temp;
        long difference;
        goldens.sort(Comparator.reverseOrder());

        for (Golden golden : goldens) {
            temp = Math.min(canFit, golden.kg);
            if (currentLoad + temp < canFit) {
                greedyMax += temp * golden.cost;
                currentLoad += temp;
                if (currentLoad == canFit) {
                    return greedyMax;
                }
            } else if (currentLoad < canFit) {
                difference = canFit - currentLoad;
                greedyMax += difference * golden.cost;
                currentLoad += difference;
                if (currentLoad == canFit) {
                    return greedyMax;
                }
            }
        }

        return greedyMax;
    }

    static class Golden implements Comparable<Golden> {
        int cost;
        int kg;

        public Golden(int cost, int kg) {
            this.cost = cost;
            this.kg = kg;
        }

        @Override
        public String toString() {
            return cost + " " + kg;
        }

        @Override
        public int compareTo(Golden o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
