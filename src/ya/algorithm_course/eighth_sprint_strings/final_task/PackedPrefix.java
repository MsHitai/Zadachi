package ya.algorithm_course.eighth_sprint_strings.final_task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://contest.yandex.ru/contest/26133/run-report/139882056/">...</a>
 * /**
 * -- ПРИНЦИП РАБОТЫ --
 * Для определения общей подстроки мы пользуемся методом сравнения отсортированных строк, где сравниваем первую и
 * последнюю (самую отличающуюся лексикографически) строки и до первого отличного символа строим префикс. Как только
 * встречаем отличающийся символ, возвращаем префикс в ответ. Самая сложная часть задачи правильно распаковать данные.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Данные приходят запакованные и нужно их правильно распаковать. Так как может быть сильная вложенность запаковки, я
 * завела объект Element, который хранит свою подстроку и свой множитель. Так как подряд могут идти несколько множителей
 * для хранения их я использую стек. Разберем на примере 2[r2[t]]. Идем посимвольно по этой строке и будем руководствоваться
 * следующими принципами - когда мы видем цифру, мы ее конвертируем в целое число и кладем в стек множителей, когда мы
 * видем открывающуюся скобку - значит у нас начинается подстрока, заводим объект Element, передаем ему последнюю подстроку
 * и достаем последний множитель из стека, также обновляем подстроку для следующего элемента подстроки.
 * Если скобка у нас закрывающаяся - время умножать подстроку и добавлять к хранимой подстроке элемента. Если встречаем
 * букву - присоединяем ее к текущей подстроке.
 * <p>
 * Итак первый символ 2, кладем в стек. Далее [ - заводим новый объект элемента и передаем ему последнюю подстроку, которая
 * пока пустая, также передаем множитель, забирая его из стека. Обновляем подстроку, она осталась пустой. Смотрим далее,
 * символ r, присоединяем к подстроке. Далее идет цифра 2, кладем в стек множителей, и скобка [, пора создавать новый
 * элемент. Передаем ему текущую подстроку r и обновляем подстроку для следующего чтения. Далее у нас символ, мы его
 * присоединяем к обновленной подстроке и получаем "t". Встретили закрывающуюся скобку. Достаем элемент из стека и
 * присоединяем его подстроке текущую подстроку, повторенную множитель Элемента раз. Получаем rtt. Идем дальше, снова
 * закрывающая скобка, достаем элемент и его пустой подстроке добавляем rtt, повторенный столько раз, сколько его множитель,
 * а это 2. Итого строка получилась rttrtt. Строка закончилась, добавляем итоговую подстроку в результат.
 * <p>
 * Допустим вторая строка нам была дана без запаковки и соответствует rtt. Мы ее просто добавим к подстроке и положим в
 * результат, отправим результат в метод поиска общей подстроки, отсортируем лексикографически. Получим первое слово rtt,
 * а второе rttrtt, пойдем до самой короткой длины и будем добавлять префикс, пока не будет разницы. Длина первого слова
 * закончится раньше, чем мы встретим разницу, вовзращаем rtt, как общий префикс.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Поиск общего префикса составляет O(n), где n - длина самого короткого слова из первого и последнего считанного после
 * сортировки. Сортировка библиотечная O(m * log m), где m - длина списка слов. Мы сначала идем по списку всех слов,
 * а потом посимвольно считываем слово, распаковывая его. Этот метод составит O (m + k), где m - длина списка слов, а k -
 * длина полностью распакованной строки. Итоговая сложность алгоритма поиска общего префикса с распаковкой составляет
 * O(m * log m) + O(n) + O (m + k).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Мы храним два стека, что занимает константу памяти, промежуточные переменные. Самая большая память уходит на список
 * result, где мы храним распакованные строки и это будет O (k), где k - максимальная длина полностью распакованной строки.
 */
public class PackedPrefix {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int n = Integer.parseInt(reader.readLine());
            List<String> packedWords = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                packedWords.add(reader.readLine());
            }

            List<String> words = unpackWords(packedWords);
            System.out.println(findCommonPrefixLength(words));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> unpackWords(List<String> packedWords) {
        List<String> result = new ArrayList<>();
        StringBuilder sb;

        Stack<Element> characters = new Stack<>();
        Stack<Integer> multipliers = new Stack<>();

        for (String word : packedWords) {
            sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (Character.isDigit(c)) {
                    if (!Character.isDigit(word.charAt(i + 1))) {
                        multipliers.push(Integer.parseInt(String.valueOf(c)));
                    } else {
                        String multiplier = String.valueOf(word.charAt(i) + word.charAt(i + 1));
                        multipliers.push(Integer.parseInt(multiplier));
                    }
                } else if (c == '[') {
                    characters.push(new Element(sb, multipliers.pop()));
                    sb = new StringBuilder();
                } else if (c == ']') {
                    Element el = characters.pop();
                    sb = el.substring.append(sb.toString().repeat(el.multiplier));
                } else {
                    sb.append(c);
                }
            }
            result.add(sb.toString());
        }

        return result;
    }

    private static String findCommonPrefixLength(List<String> words) {
        Collections.sort(words);
        String first = words.getFirst();
        String last = words.getLast();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) == last.charAt(i)) {
                sb.append(first.charAt(i));
            } else {
                break;
            }
        }

        return sb.toString();
    }

    static class Element {
        StringBuilder substring;
        int multiplier;

        public Element(StringBuilder substring, int multiplier) {
            this.substring = substring;
            this.multiplier = multiplier;
        }
    }
}
