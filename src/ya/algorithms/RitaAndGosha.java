package ya.algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RitaAndGosha {

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            int[] values = new int[n];
            String[] strings = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(strings[i]);
            }
            int target = Integer.parseInt(reader.readLine());
            int[] result = findTips(values, target);
            if (result == null) {
                System.out.println("None");
            } else {
                String res = Arrays.stream(result)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));
                System.out.println(res);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int[] findTips(int[] values, int target) {
        Set<Integer> complements = new HashSet<>();
        for (int value : values) {
            int currentComplement = target - value;
            if (complements.contains(currentComplement)) {
                return new int[]{value, currentComplement};
            }
            complements.add(currentComplement);
        }
        return null;
    }

}
