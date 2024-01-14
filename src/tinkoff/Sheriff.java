package tinkoff;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Sheriff {

    private static final Map<Character, Integer> DICTIONARY = new LinkedHashMap<>() {{
        put('s', 0);
        put('h', 0);
        put('e', 0);
        put('r', 0);
        put('i', 0);
        put('f', 0);
    }};

    private static final String SHERIFF = "sheriff";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String test1 = scanner.nextLine();

        System.out.println(maxTimes(test1));
    }

    private static int maxTimes(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (DICTIONARY.containsKey(line.charAt(i))) {
                DICTIONARY.put(line.charAt(i), DICTIONARY.get(line.charAt(i)) + 1);
            }
        }

        int min = DICTIONARY.get('s');

        for (Integer value : DICTIONARY.values()) {
            if (value <= min) {
                min = value;
            }
        }

        int count = 0;
        for (Map.Entry<Character, Integer> entry : DICTIONARY.entrySet()) {
            if (entry.getValue() >= min && entry.getKey() != 'f') {
                count++;

            }
        }

        if (DICTIONARY.get('f') == min * 2) {
            count++;
        }

        if (count == SHERIFF.length() + 1) {
            return min;
        } else if (min != 0 && count % min == 0) {
            return min;
        } else {
            return 0;
        }
    }
}
