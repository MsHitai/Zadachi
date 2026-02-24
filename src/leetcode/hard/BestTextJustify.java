package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class BestTextJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int start = 0;

        while (start < words.length) {
            // Шаг 1: находим слова для текущей строки
            int end = findEndOfLine(words, start, maxWidth);

            // Шаг 2: форматируем строку
            String line = formatLine(words, start, end, maxWidth, end == words.length - 1);
            result.add(line);

            start = end + 1;
        }

        return result;
    }

    // Находим последнее слово, которое помещается в строку
    private int findEndOfLine(String[] words, int start, int maxWidth) {
        int end = start;
        int currentLength = words[start].length();

        while (end + 1 < words.length) {
            // +1 для пробела между словами
            if (currentLength + 1 + words[end + 1].length() > maxWidth) {
                break;
            }
            currentLength += 1 + words[end + 1].length();
            end++;
        }
        return end;
    }

    // Форматируем строку с учётом правил выравнивания
    private String formatLine(String[] words, int start, int end, int maxWidth, boolean isLastLine) {
        StringBuilder sb = new StringBuilder();

        // Случай 1: последняя строка или одно слово — левое выравнивание
        if (isLastLine || start == end) {
            for (int i = start; i <= end; i++) {
                if (i > start) sb.append(" ");
                sb.append(words[i]);
            }
            // Дополняем пробелами до maxWidth
            sb.append(" ".repeat(maxWidth - sb.length()));
            return sb.toString();
        }

        // Случай 2: полное выравнивание для остальных строк
        int totalWordLength = 0;
        for (int i = start; i <= end; i++) {
            totalWordLength += words[i].length();
        }

        int totalSpaces = maxWidth - totalWordLength;
        int gaps = end - start; // количество промежутков между словами
        int baseSpaces = totalSpaces / gaps;
        int extraSpaces = totalSpaces % gaps;

        sb.append(words[start]);
        for (int i = start + 1; i <= end; i++) {
            int spacesToAdd = baseSpaces + (i - start <= extraSpaces ? 1 : 0);
            sb.append(" ".repeat(spacesToAdd)).append(words[i]);
        }

        return sb.toString();
    }

}
