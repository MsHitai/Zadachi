package stepik.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static stepik.training.StepikOneStarString.splitStringIntoCases;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        Pair<String, String> input = readInput();
        int cases = Integer.parseInt(input.first());
        String message = input.second();
        String result = splitStringIntoCases(message, cases);

        System.out.println(result);
    }

    public static Pair<String, String> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split(" \\| ");
        return new Pair<>(splitInput[0], splitInput[1]);
    }
}
