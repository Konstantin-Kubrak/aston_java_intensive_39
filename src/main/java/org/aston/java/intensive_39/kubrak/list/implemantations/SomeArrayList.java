package org.aston.java.intensive_39.kubrak.list.implemantations;

import org.aston.java.intensive_39.kubrak.list.interfaces.SomeList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, представляющий из себя несинхронизированный параметризованный массив с изменяемым размером,
 * далее именуемый списочным массивом.
 *
 * <p>Каждый {@code SomeArrayList} экземпляр имеет <i>вместимость (capacity)</i>.
 * Вместимость является размером массива, использующегося для хранения элементов в списке.
 * Вместимость всегда больше размера списочного массива.
 * С добавлением новых элементов в списочный массив вместимость автоматически увеличивается.
 *
 * @param <T> тип элементов, хранимых в данном списочном массиве.
 * @author Konstantin_Kubrak
 */
public class SomeArrayList<T> implements SomeList<T> {

    private static final int DEFAULT_CAPACITY = 8;

    /**
     * Текущее количество элементов в коллекции, по умолчанию 0.
     */
    private int fillCount;

    /**
     * Вместимость коллекции.
     */
    private int capacity;

    /**
     * Массив, хранящий элементы коллекции.
     */
    private T[] arrayOfElements;

    /**
     * Конструктор класса с начальной вместимостью коллекции по умолчанию {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#DEFAULT_CAPACITY}.
     */
    public SomeArrayList() {

        this.arrayOfElements = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * Конструктор класса с указанием начальной вместимости коллекции.
     *
     * @param initialCapacity изначальная вместимость коллекции.
     */
    public SomeArrayList(int initialCapacity) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal capacity " + initialCapacity);
        }
        this.arrayOfElements = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
    }

    /**
     * Метод вставляет элемент в конец коллекции.
     *
     * @param t элемент, который необходимо вставить в коллекцию
     */
    @Override
    public void put(T t) {

        checkIfCapacityExtensionRequired();
        arrayOfElements[fillCount] = t;
        fillCount++;
    }

    /**
     * Метод вставляет элемент в коллекцию на указанную позицию, переданную в аргументах,
     * смещая уже имеющиеся элементы вправо.
     *
     * @param t        элемент, который необходимо вставить в коллекцию
     * @param position индекс, указывающий позицию в коллекции, где будет произведена замена
     */
    @Override
    public void insert(T t, int position) {

        checkIfCapacityExtensionRequired();
        System.arraycopy(arrayOfElements, position, arrayOfElements, position + 1, size() - position);
        arrayOfElements[position] = t;
        fillCount++;
    }

    /**
     * Метод возвращает элемент коллекции, находящийся под указанным в параметрах индексом.
     *
     * @param position индекс, указывающий позицию в коллекции, с которой будет взят элемент.
     * @return возвращает элемент коллекции.
     */
    @Override
    public T get(int position) {

        return arrayOfElements[position];
    }

    /**
     * Метод удаляет элемент в коллекции под указанным в параметрах индексом,
     * производя смещение элементов коллекции влево, занимая освободившееся место.
     *
     * @param position индекс, указывающий позицию в коллекции, где будет произведено удаление
     */
    @Override
    public void delete(int position) {

        System.arraycopy(arrayOfElements, position + 1, arrayOfElements, position, size() - position);
        fillCount--;
    }

    /**
     * Метод удаляет все элементы коллекции, замещая их на null.
     */
    @Override
    public void deleteAll() {

        Arrays.fill(arrayOfElements, null);
        fillCount = 0;
    }

    /**
     * Метод заменяет элемент в коллекции под определённым индексом на другой элемент, переданный в аргументах.
     *
     * @param t        элемент, который необходимо вставить в коллекцию.
     * @param position индекс, указывающий позицию в коллекции, где будет произведена замена.
     */
    @Override
    public void set(T t, int position) {

        arrayOfElements[position] = t;
    }

    /**
     * Метод сообщает текущий размер коллекции, используя счётчик элементов.
     *
     * @return возвращает текущее количество элементов коллекции {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#fillCount}.
     */
    @Override
    public int size() {

        return fillCount;
    }

    /**
     * Метод сообщает, пустая коллекция или нет.
     * Использует счётчик элементов коллекции {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#fillCount}.
     *
     * @return возвращает true, если коллекция пустая, false если коллекция содержит элементы.
     */
    @Override
    public boolean isEmpty() {

        return fillCount == 0;
    }

    /**
     * Метод сообщает индекс, под которым объект хранится в коллекции.
     * При помощи цикла просматривается массив, при нахождении объекта возвращается
     * индекс объекта (номер текущей итерации цикла), в противном случае возвращается -1.
     *
     * @param t элемент, индекс которого необходимо найти в коллекции.
     * @return возвращает индекс, под которым элемент хранится в коллекции.
     */
    @Override
    public int getIndex(T t) {

        int index = -1;
        for (int i = 0; i < fillCount; i++) {
            if (arrayOfElements[i].equals(t)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Метод сообщает, имеется ли в коллекции переданный в параметрах элемент.
     * Используя метод поиска индекса {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#getIndex(T)},
     * определяется, присутствует ли объект в коллекции.
     *
     * @param t элемент, который необходимо найти в коллекции.
     * @return возвращает результат поиска: true или false
     */
    @Override
    public boolean contains(T t) {

        return getIndex(t) > -1;
    }

    /**
     * Метод сортирует элементы коллекции используя реализацию быстрого поиска {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#quickSort(int, int, Comparator)}.
     */
    @Override
    public void sort() {

        quickSort(0, fillCount - 1, null);
    }

    /**
     * Метод сортирует элементы коллекции используя реализацию быстрого поиска {@link org.aston.java.intensive_39.kubrak.list.implemantations.SomeArrayList#quickSort(int, int, Comparator)}.
     * с учётом переданного в параметрах компаратора.
     *
     * @param comparator, используемый в сортировке.
     */
    @Override
    public void sort(Comparator<T> comparator) {

        quickSort(0, fillCount - 1, comparator);
    }


    private void quickSort(int leftIndex, int rightIndex, Comparator<T> comparator) {

        if (leftIndex < rightIndex) {
            int divideIndex = partition(leftIndex, rightIndex, comparator);

            quickSort(leftIndex, divideIndex - 1, comparator);
            quickSort(divideIndex, rightIndex, comparator);
        }
    }

    private int partition(int from, int to, Comparator<T> comparator) {

        int leftIndex = from;
        int rightIndex = to;

        T pivot = (T) this.arrayOfElements[from + (to - from) / 2];
        while (leftIndex <= rightIndex) {
            if (Objects.nonNull(comparator)) {
                while (comparator.compare((T) this.arrayOfElements[leftIndex], pivot) < 0) {
                    leftIndex++;
                }
                while (comparator.compare((T) this.arrayOfElements[rightIndex], pivot) > 0) {
                    rightIndex--;
                }
            } else {
                while (((Comparable<T>) this.arrayOfElements[leftIndex]).compareTo(pivot) < 0) {
                    leftIndex++;
                }
                while (((Comparable<T>) this.arrayOfElements[rightIndex]).compareTo(pivot) > 0) {
                    rightIndex--;
                }
            }
            if (leftIndex <= rightIndex) {
                T tmp = (T) this.arrayOfElements[leftIndex];
                this.arrayOfElements[leftIndex] = this.arrayOfElements[rightIndex];
                this.arrayOfElements[rightIndex] = tmp;
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    /**
     * Внутренний метод класса, увеличивающий вместимость коллекции
     * Если текущий внутренний массив заполнен, то создаётся новый, в 1.5 больше, и в него копируются элементы из старого массива.
     */
    private void checkIfCapacityExtensionRequired() {

        if (fillCount >= (capacity * 0.75)) {
            this.capacity = (int) (arrayOfElements.length * 1.5 + 1);
            T[] newArrayOfElements = (T[]) new Object[capacity];
            System.arraycopy(arrayOfElements, 0, newArrayOfElements, 0, fillCount);
            arrayOfElements = newArrayOfElements;

        }
    }
}
