package stepik.training;


import lombok.Getter;

import java.util.Scanner;

/**
 * <a href="https://stepik.org/lesson/1246037/step/2?unit=1259855">...</a>
 */
public class MessageRepeat {
    public static void main(String[] args) {
        var pair = readInput();
        String message = pair.getFirst();
        int k = pair.getSecond();
        String result = "";
        if (k > 0) {
            result = message.repeat(k);
        } else if (k < 0) {
            if (message.length() % Math.abs(k) == 0) {
                result = message.substring(0, message.length() / Math.abs(k));
            } else {
                result = "NO SOLUTION";
            }
        }


        System.out.println(result);
    }

    private static Pair<String, Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().trim().split(" \\| ");
        String message = input[0];
        int k = Integer.parseInt(input[1]);
        return new Pair<>(message, k);
    }
}

@Getter
class Pair<F, S> {
    private final F first;
    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

}

