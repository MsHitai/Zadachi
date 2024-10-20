package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static stepik.training.StepikThreeStarLists.analyzeVolcano;

public class Trainer {

    public static void main(String[] args) {
        List<List<Integer>> grid = readInput();
        String result = analyzeVolcano(grid);

        System.out.println(result);
    }

    public static List<List<Integer>> readInput() {
        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<List<Integer>>>() {
        }.getType();
        return gson.fromJson(input, listType);
    }
}
