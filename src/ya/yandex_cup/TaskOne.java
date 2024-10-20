package ya.yandex_cup;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class TaskOne {

    public static void main(String[] args) {
        Map<Integer, Double> patients = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\admin\\YandexDisk\\Project\\Zadachki\\input.txt"))) {
            while (reader.ready()) {
                String[] record = reader.readLine().split(" ");
                if (record[0].equals("!")) {
                    return;
                }
                getAction(record[0], patients, record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAction(String s, Map<Integer, Double> patients, String[] record) {
        switch (s) {
            case "+", "~" -> patients.put(Integer.parseInt(record[1]), Double.parseDouble(record[2]));
            case "-" -> patients.remove(Integer.parseInt(record[1]));
            case "?" -> {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
                double sum = 0;
                for (Integer id : patients.keySet()) {
                    sum += patients.get(id);
                }
                double average = sum / patients.size();
                BigDecimal result = new BigDecimal(average);
                try {
                    writer.write(result.setScale(9, RoundingMode.HALF_UP) + "\n");
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
