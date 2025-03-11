package ya.java_course.sprints.seventh;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateFormatting {

    public static void main(String[] args) {
        String input = "14 часов 09 минут. Месяц: 02, День: 14, Год: 1966.";

        printCorrectDateTime(input);

        LocalDate secretDate = LocalDate.of(2020, 1, 10);
        LocalTime secretTime = LocalTime.of(12, 30);

        int result = decode(secretDate, secretTime);
        System.out.println(result);
    }

    private static void printCorrectDateTime(String input) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH часов mm минут. Месяц: MM, День: dd, Год: yyyy."); // определите входной формат
        LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter); // сконвертируйте исходную строку в LocalDateTime

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm"); // 14_02_1966|14:09
        String output = dateTime.format(outputFormatter);
        System.out.println(output); // выведите результат на экран
    }

    private static int decode(LocalDate secretDate, LocalTime secretTime) {
        // объедините secretDate и secretTime
        LocalDateTime newTime = LocalDateTime.of(secretDate, secretTime);

        // вычтите 2 месяца, 25 дней и 100 минут
        LocalDateTime secretMoment = newTime.minusMonths(2).minusDays(25).minusMinutes(100);
        // найдите произведение порядкового номера дня в году и часов из secretMoment
        return secretMoment.getDayOfYear() * secretMoment.getHour();
    }
}
