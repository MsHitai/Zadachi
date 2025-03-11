package ya.java_course.sprints.seventh;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

class Watch {
    private ZonedDateTime currentTime;
    private int numOfZone;
    private final List<String> zones = Arrays.asList("America/New_York", "Asia/Vladivostok", "Europe/Moscow");

    public Watch() {
        numOfZone = 0;
        ZoneId zone = ZoneId.of(zones.get(numOfZone));
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 0, 0);
        currentTime = ZonedDateTime.of(dateTime, zone);
    }

    public void changeTimeZone() {
        if (numOfZone == 2) {
            numOfZone = 0;
        } else {
            numOfZone++;
        }
        ZoneId newZone = ZoneId.of(zones.get(numOfZone));
        // смените временную зону (время должно остаться прежним)
        LocalDateTime dateTime = LocalDateTime.of(2021, 1, 26, 0, 0);
        currentTime = ZonedDateTime.of(dateTime, newZone);

        /*
        currentTime = currentTime.withZoneSameLocal(newZone);*/
    }

    public void addTenHours() {
        // увеличьте текущее время на 10 часов
        currentTime = currentTime.plusHours(10);
    }

    public void addHour() {
        // увеличьте текущее время на 1 час
        currentTime = currentTime.plusHours(1);
    }

    public void addTenMinutes() {
        // увеличьте текущее время на 10 минут
        currentTime = currentTime.plusMinutes(10);
    }

    public void addMinute() {
        // увеличьте текущее время на 1 минуту
        currentTime = currentTime.plusMinutes(1);
    }

    public ZonedDateTime getCurrentTime() {
        // верните текущее время
        return currentTime;
    }
}

class HappyDoNotWatchTime {
    public static void main(String[] args) {
        Watch watch = new Watch();

        // настройка часов
        watch.changeTimeZone();
        watch.changeTimeZone();

        watch.addTenHours();
        watch.addHour();
        watch.addHour();
        watch.addHour();
        watch.addHour();
        watch.addHour();
        watch.addHour();
        watch.addHour();
        watch.addHour();

        watch.addTenMinutes();
        watch.addTenMinutes();

        watch.addMinute();
        watch.addMinute();
        watch.addMinute();
        watch.addMinute();
        watch.addMinute();
        watch.addMinute();

        System.out.println(watch.getCurrentTime());
    }
}

//Реализуйте указанные методы и выставьте
// с помощью них следующее время: 18 часов, 26 минут, московский часовой пояс.