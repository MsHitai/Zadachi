package stepik.training;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SuppressWarnings("unused")
public class StepikOneStarString {

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
