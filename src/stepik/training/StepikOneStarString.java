package stepik.training;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("unused")
public class StepikOneStarString {

    public static boolean isPalindromeString(String value) {
        StringBuilder sb = new StringBuilder(value);
        sb.reverse();
        return sb.toString().equals(value);
    }

    public static String tagNames(String message) {
        String[] tagNames = message.split(">");
        String format = "<%s><%s><%s></%s></%s></%s>";
        return String.format(format, tagNames[0], tagNames[1], tagNames[2], tagNames[2], tagNames[1], tagNames[0]);
    }

    public static String getTags(String message) {
        String[] array = message.split("\\.");
        String tag = array[0];
        String classes = Arrays.stream(array)
                .skip(1)
                .collect(Collectors.joining(" "));
        String template = "<%s class=\"%s\"></%s>";

        return String.format(template, tag, classes, tag);
    }

    public static String sortCharactersInWords(String message) {
        String[] words = message.split(" ");
        List<String> sortedLetters = new ArrayList<>();
        String sortWord;
        for (String word : words) {
            String[] letters = word.split("");
            sortWord = Arrays.stream(letters).sorted().collect(Collectors.joining());
            sortedLetters.add(sortWord);
        }
        return String.join(" ", sortedLetters);
    }

    public static String removeVowels(String message) {
        Set<String> vowels = Set.of("а","у","о","ы","э","я","ю","ё","и","е");
        StringBuilder sb = new StringBuilder();
        String[] letters = message.split("");
        for (String letter : letters) {
            if (!vowels.contains(letter)) {
                sb.append(letter);
            }
        }
        return sb.toString();
    }

    public static String splitStringIntoCases(String message, int cases) {
        if (cases == 1) {
            return message;
        } else if (message.length() % cases != 0) {
            return "Слово не может быть равномерно разделено.";
        } else {
            int steps = message.length() / cases;
            StringJoiner joiner = new StringJoiner(", ");
            for (int i = 0; i < message.length(); i = i + steps) {
                joiner.add(message.substring(i, (i + steps)));
            }
            return joiner.toString();
        }
    }

    public static boolean hasDuplicateWords(String message) {
        String[] words = message.split(" ");
        Map<String, Integer> count = new LinkedHashMap<>();
        for (String word : words) {
            count.put(word.toLowerCase(), count.getOrDefault(word.toLowerCase(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > 1) {
                return true;
            }
        }
        return false;
    }

    public static String findFirstRepeatingChar(String message) {
        Map<String, Integer> count = new LinkedHashMap<>();
        for (int i = 0; i < message.length(); i++) {
            String value = String.valueOf(message.charAt(i));
            count.put(value, count.getOrDefault(value, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > 1) {
                return key;
            }
        }
        return "";
    }

    public static String extractLowerCaseLetters(String message) {
        StringJoiner sj = new StringJoiner(", ");
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLowerCase(message.charAt(i))) {
                sj.add(String.valueOf(message.charAt(i)));
            }
        }
        return sj.toString();
    }

    public static String lettersCount(String message) {
        int count;
        List<Integer> result = new ArrayList<>();
        String[] words = message.split("]");
        for (String word : words) {
            count = 0;
            for (int j = 0; j < word.length(); j++) {
                if (word.length() == 1) {
                    continue;
                }
                if (word.charAt(j) != '[') {
                    count++;
                }
            }
            result.add(count);
        }
        return result.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public static int countSigns(String message, char target) {
        //one line solution: return (int) message.chars().filter(c -> c == target).count();
        int count = 0;
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasAdjacentDuplicates(String message) {
        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i - 1) == message.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static String truncateWords(String message, int wordsCount) {
        String[] words = message.split(" ");
        if (words.length <= wordsCount) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wordsCount; i++) {
            sb.append(words[i]);
            if (i == wordsCount - 1) {
                sb.append("...");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static String increaseStringById(String message, int uriId) {
        String slashDelimiter = "/";
        String[] slashes = message.split(slashDelimiter);
        int id = Integer.parseInt(slashes[slashes.length - 2]);
        slashes[slashes.length - 2] = String.valueOf(uriId + id);
        return String.join(slashDelimiter, slashes);
    }

    public static String increaseStringNumber(String message, int increase) {
        //another variant Integer.parseInt(message.replaceAll("[A-Za-z\\/]",""))
        String page = message.substring(0, message.indexOf("-") + 1);
        int given = Integer.parseInt(message.substring(message.indexOf("-") + 1));
        int result = increase + given;
        return page + result;
    }

    public static String countLettersWithStars(String value) {
        Map<Character, Integer> count = new LinkedHashMap<>();

        for (int i = 0; i < value.length(); i++) {
            count.put(value.charAt(i), count.getOrDefault(value.charAt(i), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            String letter = String.valueOf(entry.getKey());
            int quantity = entry.getValue();
            String stars = "*".repeat(quantity);
            sb.append(letter).append(":").append(stars).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
