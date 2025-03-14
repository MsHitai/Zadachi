package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ChaoticWeather {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        int length = Integer.parseInt(reader.readLine());
        int[] weather = new int[length];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < length; i++) {
            weather[i] = Integer.parseInt(tokenizer.nextToken());
        }
        System.out.println(chaoticWeatherPeriod(weather, length));
    }

    private static int chaoticWeatherPeriod(int[] weather, int length) {
        int count = 0;
        if (length == 1) {
            return 1;
        }
        for (int i = 0; i < weather.length; i++) {
            if (i == 0) {
                if (weather[i] > weather[i + 1]) {
                    count++;
                }
                continue;
            }
            if (i == weather.length - 1) {
                if (weather[i] > weather[i - 1]) {
                    count++;
                }
                continue;
            }
            if (weather[i] > weather[i - 1] && weather[i] > weather[i + 1]) {
                count++;
            }
        }
        return count;
    }
}
