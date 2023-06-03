package sixth.sprint;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GamesForAll {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();

        List<String> words = readWordsFromFile("D:\\Learning Java\\Zadachki\\src\\sixth\\sprint\\words.txt");

        // если слов меньше, чем участников, то выведите сообщение:
        // "Недостаточно слов в файле. Добавьте слова и обновите файл."
        // и завершите выполнение программы
        if (words.size() < playersNumber) {
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return;
        } else {

            // воспользуйтесь статическим методом Collections.shuffle(List<?> list),
            // чтобы поменять порядок слов случайным образом
            Collections.shuffle(words);

            int wordsNumber = words.size() / playersNumber;


            for (int i = 0; i < playersNumber; i++) {
                String filename = String.format("player%s.txt", i + 1);
                List<String> subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);

                writeListToFile(subList, filename);
            }

            System.out.println("Карточки готовы!");
        }
    }

    private static List<String> readWordsFromFile(String filename) throws FileNotFoundException {
        // добавьте построчное чтение из файла с помощью BufferedReader
        // в случае ошибки выведите сообщение: "Произошла ошибка во время чтения файла."
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while(br.ready()) {
                String line = br.readLine();
                names.add(line);
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }
        return names;
    }

    private static void writeListToFile(List<String> list, String filename) throws IOException {
        // добавьте запись слов в файл с помощью FileWriter
        // в случае ошибки выведите сообщение: "Произошла ошибка во время записи файла."
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(list.toString());
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }
    }
}
