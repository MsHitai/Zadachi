package leetcode.challenges;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ValidPhoneNumber {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Learning Java\\Zadachki\\src\\leetcode\\challenges\\file.txt"))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String format1 = "[0-9]{3}-[0-9]{3}-[0-9]{4}$";
                String format2 = "^(\\([0-9]{3}\\) )[0-9]{3}-[0-9]{4}$";
                if (line.matches(format1) || line.matches(format2)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
