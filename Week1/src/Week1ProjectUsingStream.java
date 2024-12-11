import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Week1ProjectUsingStream {
        public static void main(String[] args) {

            // Infinite stream function of stock prices between 50 and 100
            Stream<Double> stockPrices = Stream.generate(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                return ThreadLocalRandom.current().nextDouble(50, 100);
            });


            stockPrices
                    .map(price -> String.format("Stock Price: %.2f", price))
                    .peek(System.out::println)
                    .map(priceString -> Double.parseDouble(priceString.split(": ")[1])) // Extract price back
                    .map(price ->
                            price > 90 ? "Action: Sell the stock" :
                                    price < 70 ? "Action: Buy the stock" :
                                            "Action: Hold"
                    )
                    .forEach(action -> {
                        System.out.println(action);
                        System.out.println("----------");
                    });
        }
    }