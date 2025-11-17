package ua.opnu;

import ua.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static ua.Util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- Завдання 1: Предикат для простого числа ---");
        // Блоковий лямбда-вираз
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };

        System.out.println("7 просте? " + isPrime.test(7));
        System.out.println("10 просте? " + isPrime.test(10));
        System.out.println("1 просте? " + isPrime.test(1));

        System.out.println("\n--- Завдання 2: Фільтрація студентів ---");
        Student[] students = {
                new Student("Іванов Іван", "IT-21", new int[]{60, 75, 90}),
                new Student("Петров Петро", "IT-21", new int[]{100, 88, 95}),
                new Student("Сидоренко Марія", "PM-22", new int[]{55, 60, 70}), // має заборгованість
                new Student("Коваленко Анна", "PM-22", new int[]{80, 80, 80})
        };

        // Предикат: повернути true, якщо студент НЕ має заборгованостей (всі оцінки >= 60)
        Predicate<Student> hasNoDebts = s -> {
            for (int mark : s.getMarks()) {
                if (mark < 60) {
                    return false; // Знайдено заборгованість
                }
            }
            return true; // Заборгованостей немає
        };

        Student[] goodStudents = filterStudents(students, hasNoDebts);
        System.out.println("Студенти без заборгованостей:");
        System.out.println(Arrays.toString(goodStudents));

        System.out.println("\n--- Завдання 3: Фільтрація за двома умовами ---");
        // Предикат 1: студент з групи IT-21
        Predicate<Student> fromGroupIT21 = s -> s.getGroup().equals("IT-21");

        // Використовуємо предикат з Завдання 2 (hasNoDebts) та новий (fromGroupIT21)
        Student[] excellentStudentsFromIT21 = filterByTwo(students, hasNoDebts, fromGroupIT21);
        System.out.println("Студенти без заборгованостей З групи IT-21:");
        System.out.println(Arrays.toString(excellentStudentsFromIT21));

        System.out.println("\n--- Завдання 4: Consumer для студентів ---");
        // Consumer: виводить ім'я студента
        Consumer<Student> printName = s -> System.out.println("Студент: " + s.getName());

        System.out.println("Список всіх студентів:");
        forEach(students, printName);

        System.out.println("\n--- Завдання 5: Метод processIf (Predicate + Consumer) ---");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Предикат: число парне
        Predicate<Integer> isEven = n -> n % 2 == 0;
        // Consumer: вивести число в консоль
        Consumer<Integer> printNumber = n -> System.out.print(n + " ");

        System.out.println("Виконуємо Consumer (друк) тільки для парних чисел:");
        processIf(numbers, isEven, printNumber);
        System.out.println(); // Новий рядок

        System.out.println("\n--- Завдання 6: Function 2^n ---");
        int[] baseNumbers = {1, 2, 3, 4, 5, 8};

        // Function: приймає n, повертає 2^n
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] powers = processArray(baseNumbers, powerOfTwo);
        System.out.println("Масив чисел: " + Arrays.toString(baseNumbers));
        System.out.println("Масив 2^n: " + Arrays.toString(powers));

        System.out.println("\n--- Завдання 7: Function stringify ---");
        int[] digits = {0, 1, 5, 9, 3, 2, 8};

        // Function: перетворює число 0-9 на рядок
        Function<Integer, String> numToString = n -> {
            switch (n) {
                case 0:
                    return "нуль";
                case 1:
                    return "один";
                case 2:
                    return "два";
                case 3:
                    return "три";
                case 4:
                    return "чотири";
                case 5:
                    return "п'ять";
                case 6:
                    return "шість";
                case 7:
                    return "сім";
                case 8:
                    return "вісім";
                case 9:
                    return "дев'ять";
                default:
                    return "невідоме число";
            }
        };

        String[] stringDigits = stringify(digits, numToString);
        System.out.println("Масив чисел: " + Arrays.toString(digits));
        System.out.println("Масив рядків: " + Arrays.toString(stringDigits));

        System.out.println("\n--- Додаткове завдання: Рефакторинг Lab 6 ---");
        // Використовуємо список (List) для Collections.sort
        List<Student> studentList = new ArrayList<>(Arrays.asList(students));

        // 1. Старий спосіб (анонімний клас)
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        System.out.println("Сортування (анонімний клас): " + studentList);

        // Перемішаємо для чистоти експерименту
        Collections.shuffle(studentList);

        // 2. Новий спосіб (лямбда-вираз)
        Collections.sort(studentList, (s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Сортування (лямбда): " + studentList);

        // Перемішаємо знову
        Collections.shuffle(studentList);

        // 3. Найкращий спосіб (посилання на метод + Comparator.comparing)
        Collections.sort(studentList, Comparator.comparing(Student::getName));
        System.out.println("Сортування (method reference): " + studentList);
    }
}