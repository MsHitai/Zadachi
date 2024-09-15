package stepik.training;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SuppressWarnings("unused")
public class StepikOneStarString {

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
