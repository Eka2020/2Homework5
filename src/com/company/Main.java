package com.company;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static final String ANSI_BLUE = " \u001B[34m";
    public static final String ANSI_GREEN = " \u001B[36m";
    public static final String ANSI_Green = "\u001B[32m";

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(4, true);
        CountDownLatch latch = new CountDownLatch(100);

        System.out.println(ANSI_GREEN + "Текущая дата: " + new SimpleDateFormat("dd.MM.yyyy")
                .format(Calendar.getInstance().getTime()));
        System.out.println(ANSI_Green + "В автовокзале " + latch.getCount() + " пассажиров ожидают отправки в город Ош");
        System.out.println(ANSI_Green + "Билеты можно приобрести в 4 кассах автовокзала");


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
