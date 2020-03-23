package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Passenger extends Thread {
    public static final String ANSI_BLUE = " \u001B[34m";
    public static final String ANSI_YELLOW = " \u001B[33m";
    public static final String ANSI_PURPLE = " \u001B[35m";
    public static final String ANSI_RED = "\u001B[31m";
    private Semaphore semaphore;
    private int passNumber;
    private CountDownLatch cdl;

    public Passenger(Semaphore semaphore, int passNumber, CountDownLatch cdl) {
        this.semaphore = semaphore;
        this.passNumber = passNumber;
        this.cdl = cdl;
    }

    @Override
    public synchronized void run() {
        try {
            semaphore.acquire();
            System.out.println(ANSI_BLUE + "Пассавир " + passNumber + ANSI_YELLOW + " покупает в кассе билет");
            sleep(2000);
            System.out.println(ANSI_BLUE + "Пассавир " + passNumber + ANSI_RED + " идет на посадку");
            semaphore.release();
            sleep(2000);
            System.out.println(ANSI_BLUE + "Пассавир " + passNumber + ANSI_PURPLE + "занимает все место в автобусе");
            cdl.countDown();
            sleep(2000);
            } catch (InterruptedException e) {
        }
    }
}

