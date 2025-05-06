package ya.algorithm_course.fifth_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerateUniqueTrees {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            System.out.println(numBSTs(n));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Сколько уникальных деревьев можно построить от числа n
     *
     * @param n число от 1 до 15
     * @return число уникальных деревьев по формуле чисел Каталана = C(n) = C(0)*C(n-1) + C(1)*C(n-2) + ... + C(n-1)*C(0)
     */
    public static int numBSTs(int n) {
        int[] trees = new int[n + 1];
        trees[0] = 1; //An empty tree (0 nodes) is considered 1 valid BST.

        for (int i = 1; i <= n; i++) {
            for (int root = 1; root <= i; root++) {
                int left = trees[root - 1];
                int right = trees[i - root];
                // Left subtree: root - 1 nodes, Right subtree: i - root nodes
                trees[i] += left * right; //represents the number of unique BSTs that can be formed with i nodes.
            }
        }

        return trees[n];
    }

}
