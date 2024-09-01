package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikOneStarTaskSolver.replaceZeroWithSum;

public class Trainer {

    public static void main(String[] args) {
        List<Integer> data = readInput();
        List<Integer> result = replaceZeroWithSum(data);
        System.out.println(new Gson().toJson(result));
    }

    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new Gson().fromJson(input, new TypeToken<List<Integer>>() {
        }.getType());
    }
}
