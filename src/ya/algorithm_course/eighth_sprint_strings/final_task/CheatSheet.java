package ya.algorithm_course.eighth_sprint_strings.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://contest.yandex.ru/contest/26133/run-report/139957549/">...</a>
 * /**
 * -- ПРИНЦИП РАБОТЫ --
 * Пытаемся построить исходный текст из слов словаря, используя динамическое программирование и бор.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Возьмем третий тест кейс - текст abacaba и слова abac, caba, aba. Ставим базовый случай, мы можем использовать
 * пустую строку для построения исходного текста, поэтому по если i == 0 мы можем смотреть посимвольно текст, заглядывая
 * в бор на наличие символа.
 * <p>
 * Первое слово abac. Символ а есть в боре, получаем узел от "а" в боре, это не слово, идем дальше, так доходим до 3го
 * символа а, у узла это слово (aba), ставим true по индексу 2 в массиве динамики. На символе с, заходим в узел, это тоже
 * слово, поэтому сразу проставим true и в 3 по индексу ячейку массива динамики. Слова abaca нет в боре, поэтому выйдем из
 * цикла по break.
 * <p>
 * Идем дальше до следующей позиции i - 1 = true в массиве динамики. Это индекс 3. С этого индекса будет подстрока caba,
 * по конечному символу флаг isWord стоит true, есть такое слово в боре, заносим по конечному индексу (длина - 1) true в
 * массив динамике. Это будет наш ответ.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * После оптимизации мы больше не перебираем каждое слово в словаре для каждого индекса. Теперь мы один раз проходим по
 * словарю, строим бор, а потом уже обращаемся к узлам этого бора при проходе по тексту. Теперь сложность алгоритма
 * составляет O(n * k), где n - длина входного текста и k - максимальная длина слова. Итоговая сложность составляет
 * O(n * k) (не берем в расчет чтение входных данных).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Мы храним словарь, бор (O(m * k)), храним массив динамики (O(n)) по длине входного текста, сам текст и несколько переменных.
 * Итого можно сказать, что пространственная сложность будет O(n + m * k), где n длина текста, m - количество слов в словаре,
 * а k - средняя длина слов в словаре.
 */
public class CheatSheet {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String input = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            List<String> words = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                words.add(reader.readLine());
            }
            boolean canSplit = canSplit(input, words);
            if (canSplit) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean canSplit(String input, List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }

                curr = curr.children.get(c);
            }

            curr.isWord = true;
        }

        boolean[] dp = new boolean[input.length()];
        for (int i = 0; i < input.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                TrieNode curr = root;
                for (int j = i; j < input.length(); j++) {
                    char c = input.charAt(j);
                    if (!curr.children.containsKey(c)) {
                        break;
                    }

                    curr = curr.children.get(c);
                    if (curr.isWord) {
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[input.length() - 1];
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            this.children = new HashMap<>();
        }
    }
}
