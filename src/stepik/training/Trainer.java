package stepik.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static stepik.training.StepikOneStarString.increaseStringById;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        Pair<Integer, String> input = readInput();
        int uriId = input.first();
        String message = input.second();
        String result = increaseStringById(message, uriId);

        System.out.println(result);
    }

    public static Pair<Integer, String> readInput() {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" \\| ");
        int uriId = Integer.parseInt(input[0].trim());
        String message = input[1].trim();
        return new Pair<>(uriId, message);
    }
}
