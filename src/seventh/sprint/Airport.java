package seventh.sprint;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Airport {

    public static void main(String[] args) {
        Instant now = Instant.now();

        // укажите корректный формат вывода даты 05.10.2021; 04:18:41. +03:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy; HH:mm:ss. ZZZZZ");

        // создайте экземпляр ZoneId для Москвы
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        ZonedDateTime moscowDateTime = ZonedDateTime.ofInstant(now, moscowZone);

        printTime(formatter, moscowDateTime, "Москва");

        convertAndPrintTime(formatter, moscowDateTime, "Осло", "Europe/Oslo");
        convertAndPrintTime(formatter, moscowDateTime, "Чикаго", "America/Chicago");
        convertAndPrintTime(formatter, moscowDateTime, "Шанхай", "Asia/Shanghai");
        convertAndPrintTime(formatter, moscowDateTime, "Аддис-Абеба", "Africa/Addis_Ababa");
    }

    private static void convertAndPrintTime(DateTimeFormatter formatter, ZonedDateTime moscowDateTime, String cityName, String region) {
        ZoneId newZone = ZoneId.of(region); // создайте ZoneId из region
        ZonedDateTime newDateTime = moscowDateTime.withZoneSameInstant(newZone); // измените временную зону у moscowDateTime
        // withZoneSameInstant(newZone)
        printTime(formatter, newDateTime, cityName);
    }

    private static void printTime(DateTimeFormatter formatter, ZonedDateTime dateTime, String cityName) {
        System.out.println(cityName + ":");
        // выведите dateTime в указанном в formatter формате
        String format = dateTime.format(formatter);
        System.out.println(format);
    }
}
