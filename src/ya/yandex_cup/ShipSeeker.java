package ya.yandex_cup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShipSeeker {

    public static void main(String[] args) {
        List<Coordinate> coordinates = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\admin\\YandexDisk\\Project\\Zadachki\\input.txt"))) {
            while (reader.ready()) {
                int x = Integer.parseInt(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                coordinates.add(new Coordinate(x, y));
            }
            System.out.println(determineWhichWayToSwim(coordinates));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static String determineWhichWayToSwim(List<Coordinate> coordinates) {
        StringBuilder sb = new StringBuilder();
        Coordinate leftAngle = coordinates.get(0);
        Coordinate swimmer = coordinates.get(2);
        if (swimmer.y < leftAngle.y) {
            sb.append("S");
        } else {
            sb.append("N");
        }
        if (swimmer.x < leftAngle.x) {
            sb.append("W");
        } else {
            sb.append("E");
        }
        return sb.toString();
    }

    private record Coordinate(int x, int y) {
    }
}
