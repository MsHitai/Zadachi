package seventh.sprint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AnonymousClass {

    public static void main(String[] args) throws Exception {
        List<String> people = new ArrayList<>(List.of(
                "Мария Зуева",
                "Анна Дарк",
                "Кирилл Филимонов",
                "Ева Пинк"
        ));

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] names = o1.split(" ");
                String[] namesOther = o2.split(" ");
                return Integer.compare(names[1].length(), namesOther[1].length());
            }
        };

        Collections.sort(people, comparator);
        System.out.println(people);

        Predicate<List<Integer>> sortedPredicate = list -> {

            if (list.size() <= 1) return true;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(i - 1)) return false;
            }
            return true;

        };

        List<Integer> list = List.of(4, 8, 15, 16, 23, 32);
        System.out.println("Отсортирован?");
        System.out.println(sortedPredicate.test(list));
    }
}
