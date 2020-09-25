package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     время - O(N) Память - O(N)
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        List<String> list = reader(inputName);
        int listSize = list.size();
        int[] listInt = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            listInt[i] = (int) (Double.parseDouble(list.get(i)) * 10);
        }
        List<Integer> lessZero = new ArrayList<>();
        List<Integer> graderZero = new ArrayList<>();
        for (int value : listInt) {
            if (value >= 0) {
                graderZero.add(value);
            } else {
                lessZero.add(Math.abs(value));
            }
        }
        int[] graderZeroArr = countingSort(graderZero, 5000);
        int[] lessZeroArr = countingSort(lessZero, 2730);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (int i = lessZeroArr.length - 1; i >= 0; i--)
            writer.write("-" + (double) lessZeroArr[i] / 10 + "\n");
        for (int value : graderZeroArr) writer.write((double) value / 10 + "\n");
        writer.close();
    }

    private static int[] countingSort(List<Integer> elements, int limit) {
        int[] count = new int[limit + 1];
        for (int element: elements) {
            count[element]++;
        }
        for (int j = 1; j <= limit; j++) {
            count[j] += count[j - 1];
        }
        int[] out = new int[elements.size()];
        for (int j = elements.size() - 1; j >= 0; j--) {
            out[count[elements.get(j)] - 1] = elements.get(j);
            count[elements.get(j)]--;
        }
        return out;
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     время - O(N) Память - O(N)
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        List<String> list = reader(inputName);
        int listSize = list.size();
        int max = 0;
        int count = 1;
        int maxNumber = 0;
        int[] arrInt = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            arrInt[i] = (Integer.parseInt(list.get(i)));
        }
        Sorts.quickSort(arrInt);
        for (int i = 1; i < listSize; i++) {
            if (max < count) {
                max = count;
                maxNumber = arrInt[i - 1];
            }
            if (arrInt[i] == arrInt[i - 1]) {
                count++;
            } else {
                count = 1;
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputName));
        for (String s : list) {
            if (Integer.parseInt(s) != maxNumber)
                 writer.write(s + "\n");
        }
        for (int i = 0; i < max; i++)
            writer.write(maxNumber + "\n");
        writer.close();
    }

    private static List<String> reader(String inputName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String str;
        List<String> list = new ArrayList<>();
        while((str = reader.readLine()) != null ){
            if(!str.isEmpty()){
                list.add(str);
            }
        }
        reader.close();
        return list;
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
