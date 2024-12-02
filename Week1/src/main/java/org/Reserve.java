package org;

public class Reserve implements Runnable{

    int availableBirth =1;
    int wantedBirth;
    Reserve(int i){
        wantedBirth=i;
    }
    @Override
    public void run() {
        synchronized (this){
        System.out.println("Available births= "+availableBirth);
        if(availableBirth>=wantedBirth){
            String name = Thread.currentThread().getName();
            System.out.println(wantedBirth+" Berth reserved for "+name);

            try {
                Thread.sleep(1500);
                availableBirth-=wantedBirth;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            String name = Thread.currentThread().getName();
            System.out.println("Sorry no birth Available for "+name);

        }
        }
    }
}
