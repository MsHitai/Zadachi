package stepik.training;

import java.util.Scanner;

import static stepik.training.StepikOneStarTaskSolver.mapNumberToValue;

public class Trainer {

    public static void main(String[] args) {
        String message = readInput();
        String result = mapNumberToValue(message);

        System.out.println(result);
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
