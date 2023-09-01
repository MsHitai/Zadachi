package test.assignments;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите число: ");
        int firstNum = scan.nextInt();

        System.out.println("Введите второе число: ");
        int secondNum = scan.nextInt();
        scan.nextLine();
        System.out.println("Выберите действие ");
        String operator = scan.nextLine();

        switch (operator) {
            case "+":
                int result = firstNum + secondNum;
                System.out.println("Получилось " + result);
                break;
            case "-":
                int result2 = firstNum - secondNum;
                System.out.println("Получилось " + result2);
                break;
            case "*":
                int result3 = firstNum * secondNum;
                System.out.println("Получилось " + result3);
                break;
            case "/":
                int result4 = firstNum / secondNum;
                System.out.println("Получилось " + result4);
                break;
            default:
                System.out.println("Этого действия пока нет");
        }
    }
}
