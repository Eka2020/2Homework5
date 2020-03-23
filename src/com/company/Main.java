package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static final String ANSI_BLUE = " \u001B[34m";

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(4, true);
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 1; i <= 100; i++) {
            new Passenger(sem, i, latch).start();
        }
        try {
            latch.await();
            System.out.println(ANSI_BLUE + "Все пассажиры в автобусе");
            System.out.println(ANSI_BLUE + "Поехали!");
        } catch (InterruptedException e) {
        }
    }
}
