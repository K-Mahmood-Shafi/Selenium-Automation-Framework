package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoreJavaShowcase {

    public static void main(String[] args) {
        variablesAndLoops();
        arraysAndCollections();
        methodsAndOverloading();
        constructorsAndObjects();
    }

    static void variablesAndLoops() {
        int age = 22;
        double salary = 35000.50;
        String name = "Mahmood";

        System.out.println("Name: " + name + ", Age: " + age + ", Salary: " + salary);

        System.out.println("For loop from 1 to 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }

        System.out.println("While loop example:");
        int count = 3;
        while (count > 0) {
            System.out.println("Count = " + count);
            count--;
        }
    }

    static void arraysAndCollections() {
        int[] marks = { 80, 90, 75, 88 };
        System.out.println("Array values: " + Arrays.toString(marks));

        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        System.out.println("Total = " + total + ", Average = " + (double) total / marks.length);

        List<String> tools = new ArrayList<>();
        tools.add("Java");
        tools.add("Selenium");
        tools.add("TestNG");
        tools.add("Maven");

        System.out.println("Tools list: " + tools);
        System.out.println("Contains Selenium? " + tools.contains("Selenium"));
    }

    static void methodsAndOverloading() {
        System.out.println("Sum(int,int) = " + sum(5, 10));
        System.out.println("Sum(double,double) = " + sum(5.5, 10.25));
        System.out.println("Sum(int,int,int) = " + sum(1, 2, 3));
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static double sum(double a, double b) {
        return a + b;
    }

    static int sum(int a, int b, int c) {
        return a + b + c;
    }

    static void constructorsAndObjects() {
        Student s1 = new Student("Ali", 21, "Automation");
        Student s2 = new Student("Sara", 22, "Manual Testing");

        s1.printDetails();
        s2.printDetails();
    }


    static class Student {
        private String name;
        private int age;
        private String specialization;

        public Student(String name, int age, String specialization) {
            this.name = name;
            this.age = age;
            this.specialization = specialization;
        }

        public void printDetails() {
            System.out.println("Student: " + name + ", Age: " + age + ", Specialization: " + specialization);
        }
    }
}

