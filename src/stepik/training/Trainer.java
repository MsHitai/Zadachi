package stepik.training;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static stepik.training.StepikTwoStarLists.createTriangle;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        int height = readInput();
        int[][] result = createTriangle(height);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(result);
        System.out.println(jsonResult);
    }

    public static int readInput() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }
}
