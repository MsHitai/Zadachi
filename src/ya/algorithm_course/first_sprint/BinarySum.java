package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinarySum {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String firstNum = reader.readLine();
        String secondNum = reader.readLine();
        System.out.println(sumTwoBinaryNumbers(firstNum, secondNum));
    }

    private static String sumTwoBinaryNumbers(String firstNum, String secondNum) {
        if (firstNum.length() != secondNum.length()) {
            String zeros = "0".repeat(Math.abs(firstNum.length() - secondNum.length()));
            if (firstNum.length() < secondNum.length()) {
                firstNum = zeros + firstNum;
            } else {
                secondNum = zeros + secondNum;
            }
        }
        StringBuilder sb = sumFirstAndSecondBinary(firstNum, secondNum);

        sb.reverse();
        return sb.toString();
    }

    private static StringBuilder sumFirstAndSecondBinary(String firstNum, String secondNum) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int first;
        int second;
        int current;
        int sum;
        for (int i = secondNum.length() - 1; i >= 0; i--) {
            first = Integer.parseInt(String.valueOf(firstNum.charAt(i)));
            second = Integer.parseInt(String.valueOf(secondNum.charAt(i)));
            sum = first + second + carry;
            current = sum % 2;
            carry = sum / 2;

            sb.append(current);
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb;
    }
}
