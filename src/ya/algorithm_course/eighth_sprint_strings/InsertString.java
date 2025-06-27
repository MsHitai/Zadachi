package ya.algorithm_course.eighth_sprint_strings;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InsertString {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String input = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            List<Insertion> words = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] ins = reader.readLine().split(" ");
                Insertion insertion = new Insertion(ins[0], Integer.parseInt(ins[1]));
                words.add(insertion);
            }

            System.out.println(insertStrings(words, input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String insertStrings(List<Insertion> insertions, String input) {
        Map<Integer, String> indexToInsert = insertions.stream()
                .collect(Collectors.toMap(Insertion::getIndex, Insertion::getInsert));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (indexToInsert.get(i) != null) {
                sb.append(indexToInsert.get(i));
                indexToInsert.remove(i);
            }
            sb.append(input.charAt(i));
        }

        if (!indexToInsert.isEmpty()) {
            sb.append(indexToInsert.values().stream().toList().getFirst());
        }

        return sb.toString();
    }

    @Getter
    static class Insertion {
        private final String insert;
        private final int index;

        public Insertion(String insert, int index) {
            this.insert = insert;
            this.index = index;
        }

        @Override
        public String toString() {
            return insert + " " + index;
        }
    }
}
