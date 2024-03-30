package ya_internship;

import java.util.Scanner;

/**
 * При регистрации на портале каждый эмо бой обязан придумать себе никнейм. Никнейм должен быть не короче 8 символов,
 * содержать в себе хотя бы 1 цифру, и хотя бы по 1 заглавной и прописной английской букве.
 * Формат ввода
 * Вводится никнейм — последовательность букв и цифр без пробелов. Длина строки не превосходит 100 символов.
 * Формат вывода
 * Выведите «YES», если ник подходит для эмо боя, и «NO» — в противном случае.
 * Пример: altushka Ответ: NO
 * Пример: EmObOy2005 Ответ: YES
 */
public class EmoBoy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputNickname = scanner.nextLine();
        if (checkNickname(inputNickname)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkNickname(String nickname) {
        int countDigits = 0;
        int countUpperLetters = 0;
        int countLowerLetters = 0;
        if (nickname.length() < 8) {
            return false;
        }
        for (int i = 0; i < nickname.length(); i++) {
            if (isDigit(nickname.charAt(i))) {
                countDigits++;
            } else {
                if (Character.isUpperCase(nickname.charAt(i))) {
                    countUpperLetters++;
                } else if (Character.isLowerCase(nickname.charAt(i))) {
                    countLowerLetters++;
                }
            }
        }
        if (countUpperLetters == 0 || countLowerLetters == 0 || countDigits == 0) {
            return false;
        } else {
            return countUpperLetters >= 1 && countLowerLetters >= 1 && countDigits >= 1;
        }
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
