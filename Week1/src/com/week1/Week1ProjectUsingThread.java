package com.week1;

import java.util.Random;

public class Week1ProjectUsingThread implements Runnable{

    private final Random random = new Random();

    private int randomStockPriceGenerator(int min,int max){
        return min +random.nextInt(max-min+1);
    }

    @Override
    public void run() {

        System.out.println("Auomode to buy and sell stock is starting!");
        while(true){

            int stockPrice=randomStockPriceGenerator(50,100);
            System.out.println("Current stock Price is RS "+stockPrice);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (stockPrice > 90) {
                System.out.println("Stock price is above RS 90. Selling stock...");
            } else if (stockPrice < 70) {
                System.out.println("Stock price is below RS 70. Buying stock...");
            } else {
                System.out.println("Holding Stocks.");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void main (String [] args){

        Week1ProjectUsingThread week1Obj= new Week1ProjectUsingThread();
        Thread stockAutomaticThread = new Thread(week1Obj);
        stockAutomaticThread.start();
    }
}
