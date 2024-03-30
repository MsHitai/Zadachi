package ya_internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Ввод - input.txt
 * Вывод - output.txt
 * Тренажер представляет из себя серию заданий: запрос к пользователю набрать некоторую строку a.
 * Вася решил записывать все нажатия клавиш пользователя в отдельный лог. В нем присутствуют маленькие латинские буквы
 * а также управляющие конструкции
 * <delete> Удаление символа после текущей позиции курсора.
 * <bspace> Удаление символа перед текущей позицией курсора.
 * <left> Курсор перемещается влево на один символ.
 * <right> Курсор перемещается вправо на один символ.
 * Если управляющая конструкция перемещает курсор за границы текущей строки или пытается удалить несуществующий символ,
 * то ничего не происходит.
 * Теперь у Васи есть строчка, которую должен был набрать пользователь, и последовательность нажатий клавиш, считанная
 * из лога. Помогите Васе выяснить, справился ли пользователь с заданием!
 * Даны две строки a и b, разделённые переводом строки, — задание пользователя и последовательность нажатий клавиш
 * (1 ≤ |a|, |b| ≤ 1000).
 * Если пользователь справился с заданием, выведите "Yes" (без кавычек), в противном случае выведите "No".
 * Пример:
 * hellochild
 * helto<left><bspace>l<delete>ochilds<bspace>
 * Ответ: Yes
 * Пример2:
 * programming
 * programming<left><left><right><delete>
 * Ответ: No
 */
public class CheckPrint {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String a = reader.readLine();
            String b = reader.readLine();
            if (checkAnswer(a, b)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkAnswer(String taskLine, String log) {
        int length = log.length();

        if (length < taskLine.length()) return false;

        char currentCharacter;
        int index = 0;
        int size = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            currentCharacter = log.charAt(i);
            if (currentCharacter != '<') {
                if (index < size) {
                    sb.insert(index, currentCharacter);
                } else {
                    sb.append(currentCharacter);
                }
                index++;
                size++;
            } else {
                int indexClosingTag = log.indexOf(">", i);
                String command = log.substring(i, indexClosingTag + 1);

                switch (command) {
                    case "<delete>" -> {
                        if (index < size) {
                            sb.deleteCharAt(index);
                            size--;
                        }
                    }
                    case "<bspace>" -> {
                        if (index > 0) {
                            sb.deleteCharAt(index - 1);
                            index--;
                            size--;
                        }
                    }
                    case "<left>" -> {
                        if (index > 0) {
                            index--;
                        }
                    }
                    case "<right>" -> {
                        if (index < size) {
                            index++;
                        }
                    }
                }
                i = indexClosingTag;
            }
        }
        return taskLine.contentEquals(sb);
    }
}
