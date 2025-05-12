package ya.algorithm_course.fourth_sprint_hashes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class InterestGroups {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            Set<String> groups = new LinkedHashSet<>();
            for (int i = 0; i < n; i++) {
                groups.add(reader.readLine());
            }
            System.out.println(String.join("\n", groups));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
