package HW4;
// Написать программу вычисления n-ого треугольного числа.
import java.util.Scanner;

public class Triangle_Ref {
    public static void main(String[] args) {
        int number = inputNumber();
        int result = solution(number);
        printResult(result);
    }
    static int inputNumber() { // Вводим число
        Scanner iScanner = new Scanner(System.in);
        System.out.print("Input TriangleNumber: ");
        int number = iScanner.nextInt();
        return number;
    }

    static int solution(int number) { // вычисляем значение n-ого треугольного числа.
        int result = number * (number + 1) / 2;
        return result;
    }

    static void printResult(int result) { // Выводим в консоль ответ

        System.out.println("Answer: " + result);
    }
}
