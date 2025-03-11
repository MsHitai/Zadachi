package ya.java_course.sprints.fifth;

import java.time.LocalDate;
import java.util.*;

public class trackOfCorrespondence {


    static Comparator<Letter> letterComparator = new Comparator<Letter>() {
        @Override
        public int compare(Letter o1, Letter o2) {
            return o1.dateReceived.compareTo(o2.dateReceived);
        }
    };

    //private static Set<Letter> letters = new TreeSet<>(letterComparator);

    private static Set<Letter> letters = new LinkedHashSet<>();

    public static void main(String[] args) {
        // информация о письмах (в порядке занесения в систему)
        letters.add(new Letter("Джон Смит", LocalDate.of(2021, 7, 7), "текст письма №1 ..."));
        letters.add(new Letter("Аманда Линс", LocalDate.of(2021, 6, 17), "текст письма №2 ..."));
        letters.add(new Letter("Джо Кью", LocalDate.of(2021, 7, 5), "текст письма №3 ..."));
        letters.add(new Letter("Мишель Фернандес", LocalDate.of(2021, 8, 23), "текст письма №4 ..."));

        printOrderedById(letters);
        printOrderedByDateReceived(letters);
    }

    private static void printOrderedById(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по ID: ");

        for (Letter letter : letters) {
            System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.dateReceived);
        }
    }

    private static void printOrderedByDateReceived(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по дате получения: ");

        Set<Letter> letters2 = new TreeSet<>(letterComparator);
        letters2.addAll(letters);
        // реализуйте этот метод
        for (Letter letter : letters2) {
            System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.dateReceived);
        }
    }

    static class Letter {
        public String authorName;      // имя отправителя
        public LocalDate dateReceived; // дата получения письма
        public String text;            // текст письма

        public Letter(String senderName, LocalDate dateReceived, String text) {
            this.authorName = senderName;
            this.dateReceived = dateReceived;
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Letter letter = (Letter) o;
            return Objects.equals(authorName, letter.authorName) && Objects.equals(dateReceived, letter.dateReceived) && Objects.equals(text, letter.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(authorName, dateReceived, text);
        }

    }
}
