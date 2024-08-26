package stepik.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Scanner;

public class ListSolutions {

    public static void main(String[] args) {
        List<Integer> data = readInput();
        boolean result = checkEven(data);


        System.out.println(result);
    }

    private static boolean checkEven(List<Integer> data) {
        return data.stream().allMatch(num -> num % 2 == 0);
    }

    public static List<Integer> readInput() {
        List<Integer> data = null;

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Gson gson = new Gson();
            data = gson.fromJson(input, new TypeToken<List<Integer>>(){}.getType());
        }
        scanner.close();

        return data;
    }
}
