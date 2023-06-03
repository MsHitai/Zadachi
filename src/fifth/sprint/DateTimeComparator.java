package fifth.sprint;

import java.util.Comparator;

public class DateTimeComparator implements Comparator<DateTime> {

    @Override
    public int compare(DateTime dt1, DateTime dt2) {
        int seconds1 = dt1.seconds + dt1.minutes / 60 + dt1.hours / 3600;
        int seconds2 = dt2.seconds + dt2.minutes / 60 + dt2.hours / 3600;
        if (dt1.year != dt2.year) {
            return Integer.compare(dt1.year, dt2.year);
        } else if (dt1.month != dt2.month) {
            return Integer.compare(dt1.month, dt2.month);
        } else if (dt1.day != dt2.day) {
            return Integer.compare(dt1.day, dt2.day);
        } else {
            return Integer.compare(seconds1, seconds2);
        }
    }
}
