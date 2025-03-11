package ya.java_course.sprints.seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

class FilteredSaver {
    private List<String> saved = new ArrayList<>();
    private List<Predicate<String>> filters = new ArrayList<>();
    private Consumer<String> onSaveListener;

    public void setOnSaveListener(Consumer<String> onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    public void addFilter(Predicate<String> filter) {
        filters.add(filter);
    }

    public void save(String line) {
        for (Predicate<String> filter : filters) {
            if (!filter.test(line)) {
                return;
            }
        }
        if (onSaveListener != null) {
            onSaveListener.accept(line);
        }
        saved.add(line);
    }

    public List<String> getSaved() {
        return saved;
    }
}

public class LyambdaExpression {


    public static void main(String[] args) {
        FilteredSaver saver = new FilteredSaver();

        Consumer<String> consumer = (String line) -> {
            System.out.println("СОХРАНЕНО: " + line);
        };

        Predicate<String> listener = (String line) -> line.contains("ВАЖНО");

        Predicate<String> listener2 = (String line1) -> line1.endsWith("!");

        saver.setOnSaveListener(consumer);
        saver.addFilter(listener);
        saver.addFilter(listener2);

        saver.save("Привет!");  // не сохранится
        saver.save("ВАЖНО - это важное слово"); // не сохранится
        saver.save("ВАЖНО! Не забывай его использовать для заметок!"); // сохранится и выведется на экран
        saver.save("Но и память тренировать тоже очень ВАЖНО!");    // сохранится и выведется на экран
        System.out.println(saver.getSaved());   // список из двух сохранённых сообщений
    }
}
