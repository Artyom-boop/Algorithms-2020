package lesson5;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final int bits;

    private final static class Del {
    }
    private final Del del = new Del();

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != del) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     *
     * Средняя
     * Время - О(n), память - О(1)
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        //noinspection unchecked
        T t = (T) o;
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null) {
            if (current.equals(t)) {
                storage[index] = del;
                break;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                return false;
            }
            current = storage[index];
        }
        size--;
        return true;
    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     * Время - О(1), память - О(1) - hasNext
     * Время - О(n), память - О(1) - next
     * Время - О(1), память - О(1) - remove
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return  new OpenAddressingSetIterator();
    }

    public class OpenAddressingSetIterator implements Iterator<T> {
        T lastElement = null;
        int index = 0;
        int lastIndex = index;
        int count = 0;


        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (count >= size)
                throw new IllegalStateException();
            while (storage[index] == null || storage[index] == del)
                index++;
            count++;
            //noinspection unchecked
            T element = (T) storage[index];
            lastElement = element;
            lastIndex = index;
            index++;
            return element;
        }

        @Override
        public void remove() {
            if (lastElement == null)
                throw new IllegalStateException();
            storage[lastIndex] = del;
            size--;
            count--;
            lastElement = null;
        }
    }
}
