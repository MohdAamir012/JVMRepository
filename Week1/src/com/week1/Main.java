package com.week1;

import com.week1.utility.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice",2, 50000),
                new Employee(2, "Bob",2, 45000),
                new Employee(3, "Charlie",3, 60000),
                new Employee(4, "Diana", 1,70000),
                new Employee(5, "Eve",6, 80000)
        );

//===>    Using 3 threads running parallely different task
//        Thread fresherThread = new Thread(() -> {
//            System.out.println("Processing fresher employees :");
//            List <Employee> e = employees.stream()
//                    .filter(emp -> emp.getYoe() < 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.30);
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//            e.forEach(emp -> System.out.println("Updated: " + emp));
//        });
//
//        Thread seniorThread = new Thread(() -> {
//            System.out.println("Processing senior employees :");
//            List <Employee> e = employees.stream()
//                    .filter(emp -> emp.getYoe() == 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.20);
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//            e.forEach(emp -> System.out.println("Updated: " + emp));
//        });
//        Thread leadThread = new Thread(() -> {
//            System.out.println("Processing teamlead employees :");
//            List <Employee> e = employees.stream()
//                    .filter(emp -> emp.getYoe() > 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.10);
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//            e.forEach(emp -> System.out.println("Updated: " + emp));
//        });
//
//        fresherThread.setName("Fresher Thread");
//        seniorThread.setName("Senior Thread");
//        leadThread.setName("Lead Thread");
//
//        fresherThread.start();
//        seniorThread.start();
//        leadThread.start();


//===>    Run task using single thread executor
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        singleThreadExecutor.submit(()->{
//
//            System.out.println("Processing team lead employees using SingleThreadExecutor:");
//            List<Employee> updatedEmployees = employees.stream()
//                    .filter(emp -> emp.getYoe() > 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.10); // Update salary
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//
//            updatedEmployees.forEach(emp -> System.out.println("Updated: " + emp));
//        });
//        singleThreadExecutor.submit(()->{
//
//            System.out.println("Processing senior employees using SingleThreadExecutor:");
//            List<Employee> updatedEmployees = employees.stream()
//                    .filter(emp -> emp.getYoe() == 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.20); // Update salary
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//
//            updatedEmployees.forEach(emp -> System.out.println("Updated: " + emp));
//        });
//        singleThreadExecutor.submit(()->{
//
//            System.out.println("Processing freshers employees using SingleThreadExecutor:");
//            List<Employee> updatedEmployees = employees.stream()
//                    .filter(emp -> emp.getYoe() < 3)
//                    .map(emp -> {
//                        emp.setSalary(emp.getSalary() * 1.30); // Update salary
//                        return emp;
//                    })
//                    .collect(Collectors.toList());
//
//            updatedEmployees.forEach(emp -> System.out.println("Updated: " + emp));
//        });

//====> Using thread cached pool concepts
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // Task for Fresher
        cachedThreadPool.submit(() -> {
            System.out.println("Processing fresher employees:");
            List<Employee> freshers = employees.stream()
                    .filter(emp -> emp.getYoe() < 3)
                    .map(emp -> {
                        emp.setSalary(emp.getSalary() * 1.30);
                        return emp;
                    })
                    .collect(Collectors.toList());
            freshers.forEach(emp -> System.out.println("Updated Fresher: " + emp));
        });

        // Task for Senior
        cachedThreadPool.submit(() -> {
            System.out.println("Processing senior employees:");
            List<Employee> seniors = employees.stream()
                    .filter(emp -> emp.getYoe() == 3)
                    .map(emp -> {
                        emp.setSalary(emp.getSalary() * 1.20);
                        return emp;
                    })
                    .collect(Collectors.toList());
            seniors.forEach(emp -> System.out.println("Updated Senior: " + emp));
        });

        // Task for Lead (YOE > 3)
        cachedThreadPool.submit(() -> {
            System.out.println("Processing lead employees:");
            List<Employee> leads = employees.stream()
                    .filter(emp -> emp.getYoe() > 3)
                    .map(emp -> {
                        emp.setSalary(emp.getSalary() * 1.10);
                        return emp;
                    })
                    .collect(Collectors.toList());
            leads.forEach(emp -> System.out.println("Updated Lead: " + emp));
        });

        // Shutdown the executor service
        cachedThreadPool.shutdown();

        Thread.sleep(3000);
        System.out.println("Printing the all updated employes");
        employees.stream().forEach((e)-> System.out.println(e));

    }
}