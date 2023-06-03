package seventh.sprint;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@FunctionalInterface
interface StringsSaverTransformer {
    // как нужно преобразовать сохраняемую строку?
    String transform(String line);
}

@FunctionalInterface
interface StringsSaverOnSaveListener {
    // дополнительное действие при сохранении
    void onSave(String line);
}

@FunctionalInterface
interface StringsSaverOnRemoveListener {
    // дополнительное действие при удалении
    void onRemove(String line);
}

class StringsSaver {
    public static final int MAX_SIZE = 10_000;

    private List<String> saved = new LinkedList<>();
    private StringsSaverTransformer transformer;
    private StringsSaverOnSaveListener onSaveListener;
    private StringsSaverOnRemoveListener onRemoveListener;

    public void setTransformer(StringsSaverTransformer transformer) {
        this.transformer = transformer;
    }

    public void onSave(StringsSaverOnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    public void onRemove(StringsSaverOnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    public void save(String line) {
        if (saved.size() == MAX_SIZE) {
            String removedLine = saved.remove(0);
            if (onRemoveListener != null) {
                onRemoveListener.onRemove(line);
            }
        }
        if (transformer != null) {
            line = transformer.transform(line);
        }
        saved.add(line);
        if (onSaveListener != null) {
            onSaveListener.onSave(line);
        }
    }

    public List<String> getSaved() {
        return saved;
    }
}

public class FunctionalInterfaces {
    public static void main(String[] args) {
        StringsSaver saver = new StringsSaver();

        StringsSaverTransformer config = new StringsSaverTransformer() {
            @Override
            public String transform(String line) {
                return "[" + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES) + "] " + line;
            }
        };

        // передайте логику из старой реализации
        saver.setTransformer(config);

        StringsSaverOnSaveListener listener = new StringsSaverOnSaveListener() {
            // в этом поле задаётся максимальная длина исходного сообщения

            @Override
            public void onSave(String line) {
                if (line.contains("ERROR")) {

                    System.out.println(line);
                }
            }
        };

        // передайте логику из старой реализации
        saver.onSave(listener);

        StringsSaverOnRemoveListener removeListener = new StringsSaverOnRemoveListener() {
            @Override
            public void onRemove(String line) {
                final int lineLengthLimit = 100;

                if (line.length() > lineLengthLimit) {
                    line = line.substring(0, lineLengthLimit) + "...";
                }
            }
        };

        saver.onRemove(removeListener);

        saver.save("Пользователь залогинился.");
        saver.save("ERROR Пользователь загрузил фото.");
        saver.save("Пользователь пополнил счёт.");
        saver.save("Пользователь продлил подписку.");
        saver.save("ERROR Пользователь опубликовал пост.");
        System.out.println(saver.getSaved());
    }
}
