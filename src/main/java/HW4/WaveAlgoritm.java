package HW4;
// Реализация волнового алгоритма следования из точки А в точку Б
import java.util.*;

public class WaveAlgoritm {

    static int[] inputPoint() { // Задаем координаты точек
        int[] point = new int[2];
        for (int i = 0; i < 2; i++) {
            Scanner iScanner = new Scanner(System.in);
            point[i] = iScanner.nextInt();
        }
        return point;
    }

    static int[][] createPole() { // Создаем поле с заданными размерами
        System.out.println("Input pole size m & n:");
        Scanner iScanner = new Scanner(System.in);
        int m = iScanner.nextInt();
        int n = iScanner.nextInt();
        int[][] pole = new int[m][n];
        return pole;
    }

    static void step(int[][] pole, int[] pointA, int k) { // Метод делает один шаг из точки А в точку Б
        for (int i = pointA[0]; i < pole.length; i++) {
            for (int j = pointA[1]; j < pole[i].length; j++) {
                if (pole[i][j] == k) {
                    if (i > 0 && pole[i - 1][j] == 0) {
                        pole[i - 1][j] = k + 1;
                    }
                    if (j > 0 && pole[i][j - 1] == 0) {
                        pole[i][j - 1] = k + 1;
                    }
                    if (i < pole.length - 1 && pole[i + 1][j] == 0) {
                        pole[i + 1][j] = k + 1;
                    }
                    if (j < pole[i].length - 1 && pole[i][j + 1] == 0) {
                        pole[i][j + 1] = k + 1;
                    }
                }
            }
        }

    }

    static void printPole(int[][] pole) { // Метод выводит в консоль поле
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println();
        }
    }
    static Map<Integer, int[]> resultPath(int pole[][], int pointA[], int pointB[], int k) {
        // Формируем и выводим путь, где собран пошагово путь из точки А в точку Б
        Map<Integer, int[]> path = new HashMap<>();
        path.put(k, new int[]{pointB[0], pointB[1]});
        int i = pointB[0];
        int j = pointB[1];
        while (k > 1) {
            if (i > 0 && pole[i - 1][j] == k - 1) {
                i = i - 1;
                j = j;
                path.put(--k, new int[]{i, j});
            } else if (j > 0 && pole[i][j - 1] == k - 1) {
                i = i;
                j = j - 1;
                path.putIfAbsent(--k, new int[]{i, j});
            } else if (i < pole.length - 1 && pole[i + 1][j] == k - 1) {
                i = i + 1;
                j = j;
                path.putIfAbsent(--k, new int[]{i, j});
            } else if (j < pole[i].length - 1 && pole[i][j + 1] == k - 1) {
                i = i;
                j = j + 1;
                path.putIfAbsent(--k, new int[]{i, j});
            }
        }
        for (Integer key : path.keySet()) {
            System.out.println(key + " " + Arrays.toString(path.get(key)));
        }
        return path;
    }
    static void resultPole(int pole[][], Map<Integer, int[]> path) {
        // Метод, который формируем и выводим поле, где показан пошагово путь из точки А в точку Б
        int m = pole.length;
        int n = pole[0].length;
        int[][] result = new int[m][n];

        int[] coordinates;

        for (Integer key : path.keySet()) {
            coordinates = path.get(key);
            result[coordinates[0]][coordinates[1]] = key;
        }
        printPole(result);
    }

    public static void main(String[] args) {
        // Вводи координаты точек А и Б
        int[] pointA = new int[2];
        System.out.println("Input coordinates point A");
        pointA = inputPoint();
        int[] pointB = new int[2];
        System.out.println("Input coordinates point B");
        pointB = inputPoint();

        int[][] pole = createPole(); // Создаем поле
        pole[pointA[0]][pointA[1]] = 1; // Присваиваем точке А 1, первый шаг

        System.out.println();

        // Заполняем поле от точки А до точки Б волновым алгоритмом
        int k = pole[pointA[0]][pointA[1]];
        while (pole[pointB[0]][pointB[1]] == 0) {
            step(pole, pointA, k);
            k++;
        }

        printPole(pole);
        System.out.println();

        System.out.println(pole[pointB[0]][pointB[1]]);
        System.out.println();

        // Формируем и выводим путь, где собран пошагово путь из точки А в точку Б
        Map<Integer, int[]> path = new HashMap<>();
        path = resultPath(pole, pointA, pointB, k);

        System.out.println();
        // Формируем и выводим поле, где показан пошагово путь из точки А в точку Б
        resultPole(pole, path);
    }

}
