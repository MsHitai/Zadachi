package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikTwoStarLists.customSort;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        Pair<List<Integer>, String> input = readInput();
        List<Integer> data = input.first();
        String order = input.second();
        List<Integer> result = customSort(data, order);
        System.out.println(new Gson().toJson(result));
    }

    public static Pair<List<Integer>, String> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" \\| ");
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        List<Integer> data = new Gson().fromJson(parts[0], listType);
        String order = parts[1].trim();
        return new Pair<>(data, order);
    }
}
