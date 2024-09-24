package stepik.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static stepik.training.StepikOneStarString.*;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        String message = readInput();
        String result = upperCamelToSnake(message);

        System.out.println(result);
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
