package sixth.sprint;

public class StringLearn {

    public static void main(String[] args) {

        String str1 = "Так много методов";
        String str2 = "д";
        String str3 = "бюв";
        String str4 = "огов";

        String str5 = "кошка";
        String str6 = "ошка";

        System.out.println(endsWith(str1, str2));
        System.out.println(endsWith(str1, str3));
        System.out.println(endsWith(str1, str4));
        System.out.println(endsWith(str5, str6));

        System.out.println(str1.endsWith(str2));
        System.out.println(str1.endsWith(str3));
        System.out.println(str1.endsWith(str4));
        System.out.println(str5.endsWith(str6));

        System.out.println(endsWith2(str1, str2));
        System.out.println(endsWith2(str1, str3));
        System.out.println(endsWith2(str1, str4));
        System.out.println(endsWith2(str5, str6));
    }

    public static boolean endsWith2(String initial, String other) {
        return initial.lastIndexOf(other) + other.length() == initial.length();
    }

    public static boolean endsWith(String initial, String other) {
        if (initial.contains(other)) {
            return initial.charAt(initial.length() - 1) == other.charAt(other.length() - 1);
        }
        return false;
    }

    private boolean isCapsLock = false;

    public void capsLock() {
        // Здесь нужно изменить значение флага isCapsLock на противоположное
        isCapsLock = true;
    }

    public void print(String str) {
        // А здесь распечатать строку в верхнем или нижнем регистре с учётом флага
        if (isCapsLock) {
            System.out.println(str.toUpperCase());
        } else {
            System.out.println(str.toLowerCase());
        }
    }

    public String fixString(String str) {
        if (str.isEmpty() || str.isBlank()) {
            System.out.println("Вы ничего не ввели!");
        }
        return str.trim();
    }
}
