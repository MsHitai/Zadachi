package stepik.training;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikOneStarTaskSolver.findMostFrequentElement;

public class Trainer {

    public static void main(String[] args) {
        List<Integer> data = readInput();
        int result = findMostFrequentElement(data);
        System.out.println(result);
    }

    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Gson gson = new Gson();
        Integer[] dataArray = gson.fromJson(input, Integer[].class);
        return Arrays.asList(dataArray);
    }
}
