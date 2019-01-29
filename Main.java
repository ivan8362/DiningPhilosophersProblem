package com.LIcompany;

import java.util.ArrayList;

public class Main {
    private static final int COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Fork> forks = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            forks.add(new Fork());
        }

//        ArrayList<Philosopher> philosophers = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            Philosopher philosopher = new Philosopher(forks.get(i), forks.get((i+1) % COUNT));
//            philosophers.add(philosopher);

            Thread thread = new Thread(philosopher, "Philosopher-" + (i));
            threads.add(thread);
        }

        for (int i = 0; i < COUNT; i++) {
            threads.get(i).start();
        }
    }
}
