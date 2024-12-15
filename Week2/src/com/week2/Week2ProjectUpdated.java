package com.week2;

import com.week1.utility.Employee;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Week2ProjectUpdated {
    public static void main(String[] args) {

        // Here importing the class from different module week1 and printing it in other module week2
        Employee employee = new Employee(20, "Aamie", 2, 50000);
        System.out.println(employee);

        // Here used the var and Factory method like of
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
                .takeWhile(price -> price >= 50)  // Using Stream API method takeWhile introduced in JAVA9 Keep generating stock prices until they drop below 50
                .map(price -> String.format("Stock Price: %.2f", price))
                .peek(System.out::println)
                .map(priceString -> Double.parseDouble(priceString.split(": ")[1]))
                .map(price -> {
                    Optional<String> action = getStockAction(price, stockActions); // Here using Optional Api to get hte actions to be performed
                    return action.orElse("Action: No action available"); // here using orElse method to return value if action is NUll to prevent NUll pointer exception
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

    // Optional API method to determine stock action
    private static Optional<String> getStockAction(double price, List<String> stockActions) {
        if (price > 90) {
            return Optional.of("Action: " + stockActions.get(0));  // Sell
        } else if (price < 70) {
            return Optional.of("Action: " + stockActions.get(1));  // Buy
        } else {
            return Optional.of("Action: " + stockActions.get(2));  // Hold
        }
    }
}
