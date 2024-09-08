package stepik.training;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static stepik.training.StepikTwoStarLists.createRectangle;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        Pair<Integer, Integer> input = readInput();
        int width = input.first();
        int height = input.second();
        int[][] result = createRectangle(width, height);

        System.out.println(new Gson().toJson(result));
    }

    public static Pair<Integer, Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" \\| ");
        int width = Integer.parseInt(parts[0].trim());
        int height = Integer.parseInt(parts[1].trim());
        return new Pair<>(width, height);
    }
}
