package sixth.sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReaderPractice {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> frequencyMap = new HashMap<>();

        FileReader reader = new FileReader("D:\\Learning Java\\Zadachki\\src\\sixth\\sprint\\result.txt");
        BufferedReader br = new BufferedReader(reader);

        while (br.ready()) {// читайте файл построчно и сразу обновляйте frequencyMap.
            String line = br.readLine();
            frequencyMap.put(line, frequencyMap.getOrDefault(line, 0) + 1);
            // выведите результат в формате "<буква>: <количество>".
        }

        for (String s : frequencyMap.keySet()) {
            System.out.printf("%s: %d \n", s, frequencyMap.get(s));

        }

        br.close();
    }
}
