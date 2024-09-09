package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikTwoStarLists.swapElements;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        List<Integer> data = readInput();
        List<Integer> result = swapElements(data);

        System.out.println(new Gson().toJson(result));
    }

    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        return gson.fromJson(input, listType);
    }
}
