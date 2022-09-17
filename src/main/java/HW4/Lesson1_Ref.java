package HW4;
// Даны два числа А и Б, с помощь команд К1 и К2 получить из числа А число Б или вывести не возможно.
import java.util.Scanner;

public class Lesson1_Ref {
    static int solve(int start, int end, int com1, int com2) {
        int[] ways = new int[end + 1];
        ways[start] = 1;

        for (int index = start + com1; index <= end; index++) {
// При делении без остатка элемента последовательности на множетель, колличество решений будет равно
// количеству решений для предыдущего элемента плюс количество решений элемента под номером
// текущего деленного на множетель.
            if (index % com2 == 0) {
                ways[index] = ways[index - com1] + ways[index / com2];
            } else {
                ways[index] = ways[index - com1];
            }
        }
// Иначе колличество решений будет равно количеству решений для предыдущего элемента.
        System.out.println(print(ways)); // Выводится в консоль количество решений для каждого эллемента последовательности.
        return ways[end]; // Метод solve вычисляет колличество решений.
    }

    static String print(int[] items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
// Этот метод собирает строку из элементов массива в скобках и их значений.
            sb.append(String.format("(%d)%d ", i, items[i]));
        }
        return sb.toString();
    }

    static StringBuilder st = new StringBuilder(); // Вводим переменную, чтобы собрать строку с решением.

    public static void main(String[] args) {
        int[] arr = new int[4];
        arr = inputData();
        printAnswer1(solve(arr[0], arr[1], arr[2], arr[3])); // Выводим строку с элементами и количеством решений для этого элемента.
        System.out.println();

        printAnswer2(waysGetEndValue(arr[0], arr[1], arr[2], arr[3]));
    }

    // Метод вычисляет единственное оптимальное решение с помощью рекурсии.
    static boolean waysGetEndValue(int a, int b, int c, int d) {
        boolean result = false; // Объявляем булевскую переменную.
        if (a == b) { // Если условие выполняется. выходим из метода.
//            System.out.println("a = b");
            result = true;
        } else { // Иначе запускаем рекурсию в зависимости от подходящего условия.
            if (a <= b / d && b % d == 0 && (!result)) { // Пока не найден результат.
                result = waysGetEndValue(a, b / d, c, d);
                if (result) {
//                    System.out.println("k2 ");
                    st.append("k2 "); // Собираем строку с ответом.
                }
            }
            if (a < b && (!result)) {
                result = waysGetEndValue(a, b - c, c, d);
                if (result) {
//                    System.out.println("k1 ");
                    st.append("k1 ");
                }
            }
        }

        return result;
    }

    static int[] inputData() {
        // Метод отвечает за ввод данных.
        Scanner iScanner = new Scanner(System.in);
        int[] array = new int[4];
        System.out.print("Input alternately 2 numbers and 2 commands: ");
        for (int i = 0; i < 4; i++) {
            array[i] = iScanner.nextInt();
        }
        return array;
    }

    static void printAnswer2(boolean answer) {
        // Метод отвечает за вывод ответа.
        if (answer) {
            System.out.print("Answer: ");
            System.out.println(st); // Выводим решение
        } else {
            System.out.println("False");
        }
    }

    static void printAnswer1(int answer) {
        // Метод отвечает за вывод ответа.
        System.out.println(answer);
    }
}
