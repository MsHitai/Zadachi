package ya.algorithm_course.first_sprint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumTwoStringNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        reader.readLine();
        String number = String.join("", reader.readLine().split(" "));
        String addition = reader.readLine();
        System.out.println(sumTwoStringNumbers(number, addition));
    }

    private static String sumTwoStringNumbers(String firstNum, String secondNum) {
        String zeros = "0".repeat(Math.abs(firstNum.length() - secondNum.length()));
        if (firstNum.length() > secondNum.length()) {
            secondNum = zeros + secondNum;
        } else if (secondNum.length() > firstNum.length()) {
            firstNum = zeros + firstNum;
        }
        StringBuilder sb = new StringBuilder();
        int j = firstNum.length() - 1;
        int carry = 0;
        int current;
        int tempFirst;
        int tempSecond;
        for (int i = firstNum.length() - 1; i >= 0; i--) {
            tempFirst = Integer.parseInt(String.valueOf(firstNum.charAt(i)));
            tempSecond = Integer.parseInt(String.valueOf(secondNum.charAt(j)));
            int sum = tempFirst + tempSecond + carry;
            current = sum % 10;
            carry = sum / 10;
            sb.append(" ").append(current);
            j--;
        }
        if (carry != 0) {
            sb.append(" ").append(carry);
        }
        sb.reverse();
        return sb.toString();
    }
}
