package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static stepik.training.StepikTwoStarLists.sumOfMissPairs;

@SpringBootApplication
public class Trainer {

    public static void main(String[] args) {
        List<Integer> data = readInput();
        String result = sumOfMissPairs(data);
        System.out.println(result);
    }

    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        return new Gson().fromJson(input, listType);
    }
}
