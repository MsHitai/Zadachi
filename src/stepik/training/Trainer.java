package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikThreeStarLists.analyzeVolcano;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        List<List<Integer>> grid = readInput();
        String result = analyzeVolcano(grid);

        System.out.println(result);
    }

    public static List<List<Integer>> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<List<Integer>>>() {
        }.getType();
        return gson.fromJson(input, listType);
    }
}
