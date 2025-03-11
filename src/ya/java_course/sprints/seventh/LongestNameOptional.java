package ya.java_course.sprints.seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LongestNameOptional {

    public static Optional<String> longestName(List<Optional<String>> maybeNames) {
        List<String> defaultLongest = new ArrayList<>();
        for (Optional<String> name : maybeNames) {
            name.ifPresent(defaultLongest::add);
        }

        int max = 0;
        String maxName = "";
        for (String s : defaultLongest) {
            if (s.length() > max) {
                max = s.length();
                maxName = s;
            }
        }


        return Optional.of(maxName);
    }

    public static void main(String[] args) {
        Optional<String> longestOptional = longestName(List.of(
                Optional.of("Max"),
                Optional.empty(),
                Optional.of("Alexey"),
                Optional.empty(),
                Optional.empty(),
                Optional.of("Alex")
        ));
        if (longestOptional.isPresent()) {
            String longestName = longestOptional.get();
            System.out.println("Самое длинное имя состоит из "
                    + longestName.length() + " символов.");
        } else {
            System.out.println("Такого имени нет.");
        }
    }
}

