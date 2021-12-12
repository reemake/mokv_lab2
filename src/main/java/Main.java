import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    /**
     * Процедура генерации массива вещественных чисел в заданном диапазоне
     * @param leftLimit левая граница диапазона
     * @param rightLimit правая граница диапазона
     * @param amountOfNumbers количество чисел
     * @return сгенерированный массив
     */
    static Double[] generateArray(double leftLimit, double rightLimit, int amountOfNumbers) {
        Double[] arr = new Double[amountOfNumbers];
        Random random = new Random(1);
        for (int i = 0; i < amountOfNumbers; i++) {
            arr[i] = random.nextDouble(rightLimit - leftLimit) + leftLimit;
        }
        return arr;
    }

    /**
     * Процедура вычисления точного значения суммы
     * @param arr массив чисел двойной точности
     * @return точное значение суммы
     */
    static double sumExact(Double []arr) {
        double sum = 0.0d;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i];
        return sum;
    }

    /**
     * Процедура вычисления приближенного значения суммы чисел, взятых в случайном порядке
     * @param arr неотсортированный массив чисел двойной точности, преобразуемый в процессе выполнения процедуры в массив чисел одинарной точности
     * @return приближенное значение суммы чисел, взятых в случайном порядке
     */
    static float sumUnsorted(Double []arr) {
        float sum = 0.0f;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i].floatValue();
        return sum;
    }

    /**
     * Процедура вычисления приближенного значения суммы чисел, взятых в порядке возрастания
     * @param arr неотсортированный массив чисел двойной точности, преобразуемый в процессе выполнения процедуры в массив чисел одинарной точности, отсортированный в порядке возрастания
     * @return приближенное значение суммы чисел, взятых в порядке возрастания
     */
    static float sumSortedAsc(Double []arr) {
        Arrays.sort(arr);

        float sum = 0.0f;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i].floatValue();
        return sum;
    }

    /**
     * Процедура вычисления приближенного значения суммы чисел, взятых в порядке убывания
     * @param arr неотсортированный массив чисел двойной точности, преобразуемый в процессе выполнения процедуры в массив чисел одинарной точности, отсортированный в порядке убывания
     * @return приближенное значение суммы чисел, взятых в порядке убывания
     */
    static float sumSortedDesc(Double []arr) {
        Arrays.sort(arr, Collections.reverseOrder());

        float sum = 0.0f;
        for (int i = 0; i < arr.length; i++)
            sum += arr[i].floatValue();
        return sum;
    }

    /**
     * Основной метод для проведения вычислительного эксперимента с вычислением погрешностей различных сумм
     * @param leftLimit левая граница диапазона чисел, генирирующихся для исходного массива чисел
     * @param rightLimit правая граница диапазона чисел, генирирующихся для исходного массива чисел
     * @param amountOfSummands количество слагаемых
     * @param counter переменная-счетник для заполнения таблицы результатов
     * @param data двумерный массив, отвечающий за ячейки таблицы результатов
     */
    static void experimentSum(double leftLimit, double rightLimit, int amountOfSummands, int counter, String[][] data) {

        /** Генерация массива случайных чисел и создание его копии */
        Double[] arr = generateArray(leftLimit, rightLimit, amountOfSummands);
        Double[] arrCopy = arr.clone();

        /** Расчет точной суммы и трех приближенных */
        double sumExact = sumExact(arrCopy);
        float sumUnsorted = sumUnsorted(arrCopy);
        float sumSortedAsc = sumSortedAsc(arrCopy);
        float sumSortedDesc = sumSortedDesc(arrCopy);

        /** Расчет относительных погрешностей для каждой из сумм */
        double deltaUnsorted = Math.abs(sumUnsorted - sumExact);
        double deltaSortedAsc = Math.abs(sumSortedAsc - sumExact);
        double deltaSortedDesc = Math.abs(sumSortedDesc - sumExact);

        /** Заполнение данных таблицы */
        data[counter][0] = String.valueOf(amountOfSummands);
        data[counter][1] = String.valueOf(deltaUnsorted);
        data[counter][2] = String.valueOf(deltaSortedAsc);
        data[counter][3] = String.valueOf(deltaSortedDesc);

        /* Вывод в таблицы консоль */
        //System.out.println(amountOfTerms + "\t\t" + deltaUnsorted + "\t\t" + deltaSortedAsc + "\t\t" + deltaSortedDesc);

        /* Сгенерированный массив и значения сумм */
        //System.out.println("\nСгенерированный массив:\n" + Arrays.toString(arr));
        //System.out.println("\nТочное значение суммы = \t\t\t\t\t\t\t\t\t\t\t" + sumExact);
        //System.out.println("Приближенное значение суммы (числа взяты в случайном порядке) =\t\t" + sumUnsorted);
        //System.out.println("Приближенное значение суммы (числа взяты в порядке возрастания) =\t" + sumSortedAsc);
        //System.out.println("Приближенное значение суммы (числа взяты в порядке убывания) =\t\t" + sumSortedDesc);
    }

    public static void main(String[] args) {

        /** Диапазон значений, из которого генерируются числа для массива */
        double a = 0.0d;
        double b = 20.0d;

        /** Переменные цикла */
        int begin = 50;
        int end = 1000;
        int step = 50;

        /** Инициализация шапки и ячеек таблицы */
        String[] col = {"Число слагаемых в сумме", "deltaUnsorted", "deltaSortedAsc", "deltaSortedDesc"};
        String[][] data = new String[(end-begin)/step+1][col.length];

        /** Цикл, вызывающий процедуру вычислительного эксперимента для заданного количества слагаемых */
        for (int n = begin, i = 0; n <= end; n += step, i++) {
            experimentSum(a, b, n, i, data);
        }

        /** Создание таблицы результатов вычислительного эксперимента */
        Table table = new Table(col, data);
    }
}
