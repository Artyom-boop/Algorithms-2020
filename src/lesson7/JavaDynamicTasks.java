package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * время - O(N*M) Память - O(N*M)
     */
    public static String longestCommonSubSequence(String first, String second) {
        int n = first.length();
        int m = second.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1))
                    f[i][j] = f[i - 1][j - 1] + 1;
                else
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
        StringBuilder res = new StringBuilder();
        int i = n;
        int j = m;
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                res.append(first.charAt(i - 1));
                i--;
                j--;
            } else {
                if (f[i - 1][j] == f[i][j])
                    i--;
                else
                    j--;
            }
        }
        res.reverse();
        return res.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     *
     * время - O(N*logN) Память - O(N)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int[] p = new int[list.size()];
        int[] m = new int[list.size() + 1];
        int l = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int lo = 1;
            int hi = l;
        while (lo <= hi){
                int mid = (int) Math.ceil(((lo + hi) / 2));
                if (list.get(m[mid]) <= list.get(i)) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        int newL = lo;
        p[i] = m[newL - 1];
        m[newL] = i;
        if (newL > l)
            l = newL;
        }
        int[] s = new int[l];
        int k = m[l];
        for (int i = l - 1; i >= 0; i-- ) {
            s[i] = list.get(k);
            k = p[k];
        }
        List<Integer> res = new ArrayList<>();
        for (int i = s.length - 1; i >= 0; i--)
            res.add(s[i]);
        return res;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
