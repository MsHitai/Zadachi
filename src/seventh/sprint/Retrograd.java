package seventh.sprint;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Retrograd {

    public static void main(String[] args) {
        // время начала работы над задачей — 9:00
        LocalTime taskStart = LocalTime.of(9, 0);
        // время окончания работы над задачей — 11:30
        LocalTime taskFinish = LocalTime.of(11, 30);

        // опишите формат вывода в виде часы:минуты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // найдите продолжительность между двумя единицами времени
        Duration duration = Duration.between(taskStart, taskFinish);

        String formatStart = taskStart.format(formatter);
        String formatFinish = taskFinish.format(formatter);

        // taskStart должен быть выведен в указанном формате
        System.out.println("В прошлый раз задача была начата в " + formatStart + "," );
        // taskFinish должен быть выведен в указанном формате
        System.out.println("а закончена в " + formatFinish + ".");

        LocalTime now = LocalTime.now();
        String formatNow = now.format(formatter);
        // now должен быть выведен в указанном формате
        System.out.println("Сейчас " + formatNow + ".");

        // прибавьте к текущему моменту вычисленную продолжительность
        LocalTime finishTime = now.plus(duration);

        String formatFinishTime = finishTime.format(formatter);

        // finishTime должен быть выведен в указанном формате
        System.out.println("Значит, задача будет выполнена к " + formatFinishTime + ".");

        // this is not from the task
        Instant now2 = Instant.now();

        // сохраняем московское время:
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        ZonedDateTime moscowDateTime = ZonedDateTime.ofInstant(now2, moscowZone);

        // меняем регион на Нью-Йорк
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZonedDateTime newYorkDateTime = moscowDateTime.withZoneSameLocal(newYorkZone);

        System.out.println(moscowDateTime);
        System.out.println(newYorkDateTime);
    }
}
