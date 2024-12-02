package org;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice",2, 50000),
                new Employee(2, "Bob",2, 45000),
                new Employee(3, "Charlie",3, 60000),
                new Employee(4, "Diana", 1,70000),
                new Employee(5, "Eve",6, 80000)
        );

        Thread fresherThread = new Thread(() -> {
            System.out.println("Processing fresher employees :");
            employees.stream()
                    .filter(emp -> emp.getYoe() <= 2)
                    .forEach(emp -> {
                        emp.setSalary(emp.getSalary() * 1.25);
                        System.out.println("Updated: " + emp);
                    });
        });

        Thread seniorThread = new Thread(() -> {
            System.out.println("Processing senior employees :");
            employees.stream()
                    .filter(emp -> emp.getYoe() == 3)
                    .forEach(emp -> {
                        emp.setSalary(emp.getSalary() * 1.20);
                        System.out.println("Updated: " + emp);
                    });
        });

        Thread leadThread = new Thread(() -> {
            System.out.println("Processing teamlead employees :");
            employees.stream()
                    .filter(emp -> emp.getYoe() > 3)
                    .forEach(emp -> {
                        emp.setSalary(emp.getSalary() * 1.10);
                        System.out.println("Updated: " + emp);
                    });
        });

        fresherThread.setName("Fresher Thread");
        seniorThread.setName("Senior Thread");
        leadThread.setName("Lead Thread");

        fresherThread.setPriority(6);
        seniorThread.setPriority(7);
        leadThread.setPriority(8);

        fresherThread.start();
        seniorThread.start();
        leadThread.start();

        try {
            leadThread.join();
            seniorThread.join();
            fresherThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing the all updated employes");
        employees.stream().forEach((e)-> System.out.println(e));
    }
}