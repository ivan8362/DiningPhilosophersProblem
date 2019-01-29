package com.LIcompany;

import java.util.Random;

public class Philosopher implements Runnable{
    private Fork leftFork;
    private Fork rightFork;
    private Random random = new Random();

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
    @Override
    public void run() {
        message("sat on the table");

        while (true) {
            message("takes the left fork");
            if (!leftFork.take()) {
                pause(5, "takes left fork. Thinks ");
                continue;
            }

            message("takes the right fork");
            boolean rightForkTaken = false;

            for (int i = 0; i < 2; i++) {
                if (!rightFork.take()) {
                    pause(5, "right fork is taken. Thinks ");
                } else {
                    rightForkTaken = true;
                    break;
                }
            }

            if(!rightForkTaken) {
                leftFork.put();
                pause(5, "puts left fork on the table. thinks");
                continue;
            }

            pause(10, "eats");

            rightFork.put();
            leftFork.put();
            pause(4,"stops eating.");
        }
    }

    private void pause(int maxSec, String message) {
        int sec = random.nextInt(maxSec - 1) + 1;
        message(message + " " + sec + " s.");

        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void message(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}
