package ya.algorithm_course.eighth_sprint_strings.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://contest.yandex.ru/contest/26133/run-report/139886032/">...</a>
 * /**
 * -- ПРИНЦИП РАБОТЫ --
 * Пытаемся построить исходный текст из слов словаря, используя динамическое программирование вперед.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Возьмем третий тест кейс - текст abacaba и слова abac, caba, aba. Ставим базовый случай, мы можем использовать
 * пустую строку для построения исходного текста, поэтому по dp[0] ставим true - да можем построить подстроку до i (0)
 * из текущей подстроки (""). Начинаем цикл и перебираем слова в словаре. Для экономии памяти и времени, не будем строить
 * подстроки, а воспользуемся расчетом конечного индекса и методом из класса String startsWith, который смотрит, есть
 * ли переданный префикс от указанного индекса.
 * <p>
 * Первое слово abac. Высчитываем конец = 0 + 4 = 4. Смотрим, 4 меньше длины (включительно) и строка от индекса 0 имеет
 * подстроку abac. Заполняем массив динамики по индексу 4, как true. Следующее слово aba, также высчитываем конец текущей
 * подстроки от i до конца слова и получаем 3. От индекса 0, как раз строка имеет префикс aba. Записываем true в массив
 * динамики по индексу 3. Для caba ответ false, так как такого префикса от индекса 0 нет.
 * <p>
 * Идем дальше до следующей позиции true в массиве динамики. Это индекс 3. Идем по словам словаря и высчитываем для каждого
 * индекс окончания подстроки. Мы пропустим abac и aba, так как начиная с индекса 3 слово начинается с 'c' и поэтому
 * никак не может иметь префикс, начинающийся с 'а'. А вот слово caba рассмотрим. Индекс окончания равен длине строки, а
 * префикс от текущего i как раз равен caba, поэтому ставим по индексу 7 true. Это и будет наш ответ.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Для каждого индекса в тексте мы перебираем все слова в словаре. Для каждого слова мы проверяем есть ли такая подстрока
 * в тексте. Поэтому сложность алгоритма составляет O(n * k * m), где n - длина входного текста, k - количество слов в
 * словаре и m - максимальная длина слова в словаре, которая по условию задачи нам известна (100), так что можно считать,
 * эту длину константой и тогда итоговая сложность составляет O(n * k) (не берем в расчет чтение входных данных).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Мы храним словарь, храним массив динамики по длине входного текста, сам текст и несколько переменных. Итого можно
 * сказать, что пространственная сложность будет O(n + k), где n длина текста, а k - количество слов в словаре.
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
        boolean[] dp = new boolean[input.length() + 1];
        dp[0] = true;

        for (int i = 0; i <= input.length(); i++) {
            if (!dp[i]) {
                continue;
            }
            for (String word : words) {
                int end = i + word.length();
                if (end <= input.length() && input.startsWith(word, i)) {
                    dp[end] = true;
                }
            }
        }

        return dp[input.length()];
    }

}
