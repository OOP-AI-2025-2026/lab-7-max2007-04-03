package ua;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Util {

    // --- Метод для Завдання 2 ---
    /**
     * Фільтрує масив студентів за заданим предикатом.
     */
    public static Student[] filterStudents(Student[] input, Predicate<Student> p) {
        // Використовуємо ArrayList для динамічного додавання
        ArrayList<Student> resultList = new ArrayList<>();
        for (Student s : input) {
            if (p.test(s)) {
                resultList.add(s);
            }
        }
        // Конвертуємо список назад у масив
        return resultList.toArray(new Student[0]);
    }

    // --- Метод для Завдання 3 ---
    /**
     * Фільтрує масив за двома предикатами (умова AND).
     */
    public static <T> T[] filterByTwo(T[] input, Predicate<T> p1, Predicate<T> p2) {
        ArrayList<T> resultList = new ArrayList<>();
        for (T item : input) {
            // Елемент проходить, якщо обидві умови істинні
            if (p1.test(item) && p2.test(item)) {
                resultList.add(item);
            }
        }
        // Використовуємо `toArray(new T[0])` для коректного створення масиву типу T
        return resultList.toArray(Arrays.copyOf(input, 0));
    }

    // --- Метод для Завдання 4 (аналогічний до того, що в теорії) ---
    /**
     * Виконує дію (Consumer) для кожного елемента масиву.
     */
    public static <T> void forEach(T[] input, Consumer<T> action) {
        for (T item : input) {
            action.accept(item);
        }
    }

    // --- Метод для Завдання 5 ---
    /**
     * Виконує дію (Consumer) для елементів, що задовольняють умову (Predicate).
     */
    public static <T> void processIf(T[] input, Predicate<T> p, Consumer<T> c) {
        for (T item : input) {
            if (p.test(item)) {
                c.accept(item);
            }
        }
    }

    // --- Метод для Завдання 6 (аналогічний до того, що в теорії) ---
    /**
     * Обробляє масив int[] за допомогою Function.
     */
    public static int[] processArray(int[] input, Function<Integer, Integer> function) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++)
            result[i] = function.apply(input[i]);
        return result;
    }

    // --- Метод для Завдання 7 ---
    /**
     * Перетворює масив int[] на масив String[] за допомогою Function.
     */
    public static String[] stringify(int[] input, Function<Integer, String> function) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }

}