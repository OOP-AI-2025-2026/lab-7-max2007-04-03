package ua;

import java.util.Arrays;

/**
 * Допоміжний клас для Завдання 2, 4 та Додаткового.
 */
public class Student {
    private String name;
    private String group;
    private int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int[] getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "ua.Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }
}