package ya.java_course.sprints.seventh;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class CosmosJump {

    public static final int SECONDS_IN_DAY = 60 * 60 * 24;

    public static void main(String[] args) {
        LocalDateTime firstStart = LocalDateTime.of(2099, 10, 10, 12, 5);
        LocalDateTime firstFinish = LocalDateTime.of(2099, 10, 10, 14, 15);

        LocalDateTime secondStart = LocalDateTime.of(2099, 10, 10, 12, 0);
        LocalDateTime secondFinish = LocalDateTime.of(2099, 10, 11, 15, 30);

        LocalDateTime thirdStart = LocalDateTime.of(2099, 10, 10, 23, 10);
        LocalDateTime thirdFinish = LocalDateTime.of(2099, 10, 11, 10, 25);


        printGap(firstStart, firstFinish);
        printGap(secondStart, secondFinish);
        printGap(thirdStart, thirdFinish);

        // this is not from the task
        Instant moment = Instant.now();
        System.out.println("Timestamp: " + moment);

        // сохраняем её как московское время:
        ZoneId zone = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(moment, zone);

        System.out.println(zonedDateTime);
        // this is not from the task
    }

    private static void printGap(LocalDateTime start, LocalDateTime finish) {
        // используйте паттерн "dd.MM.yyyy, HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        String formatto = start.format(formatter);
        String formatto2 = finish.format(formatter);
        System.out.println("Вход в гиперпространство:");
        // вывод должен быть в корректном формате
        System.out.println(formatto);
        System.out.println("Выход из гиперпространства:");
        // вывод должен быть в корректном формате
        System.out.println(formatto2);
        // найдите продолжительность
        Duration duration = Duration.between(start, finish);
        Duration ofDay = Duration.ofSeconds(SECONDS_IN_DAY);

        // сравните продолжительность в секундах с количеством секунд в сутках
        // воспользуйтесь константой SECONDS_IN_DAY
        if (duration.compareTo(ofDay) > 0) {
            // выведите продолжительность в днях
            System.out.println("Дней на гиперпрыжок: " + duration.toDays());
        } else {
            // выведите продолжительность в минутах
            System.out.println("Минут на гиперпрыжок: " + duration.toMinutes());
        }
        System.out.println();
    }
}
