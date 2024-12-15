package com.week2;

import com.week1.utility.Employee;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Week2ProjectUpdated {
    public static void main(String[] args) {

            Employee employee =new Employee(20,"Aamie",2,50000);
        System.out.println(employee);

        // Create an immutable List using Java 9 Collection Factory methods
        var stockActions = List.of("Sell the stock", "Buy the stock", "Hold");

        // Infinite stream generator of stock prices between 50 and 100
        var stockPrices = Stream.generate(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return ThreadLocalRandom.current().nextDouble(50, 100);
        });

        stockPrices
                .takeWhile(price -> price >= 50)  // Using Stream API method takeWhile introduce in JAVA9 Keep generating stock prices until they drop below 50
                .map(price -> String.format("Stock Price: %.2f", price))
                .peek(System.out::println)
                .map(priceString -> Double.parseDouble(priceString.split(": ")[1]))
                .map(price -> {
                    if (price > 90) {
                        return "Action: " + stockActions.get(0);
                    } else if (price < 70) {
                        return "Action: " + stockActions.get(1);
                    } else {
                        return "Action: " + stockActions.get(2);
                    }
                })
                .forEach(action -> {
                    System.out.println(action);
                    System.out.println("----------");
                });



        // Example usage of Java 11 String methods
        var message = "   Stock trading simulation complete.   ";
        System.out.println(message.strip()); // Java 11: Strip leading and trailing spaces
        System.out.println("Is the message blank? " + message.isBlank()); // Java 11: Check if blank
    }
}
