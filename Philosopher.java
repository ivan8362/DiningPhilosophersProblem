package com.LIcompany;

import java.util.Random;

public class Philosopher implements Runnable{
    private static final int ROUNDS = 20;
    private Fork leftFork;
    private Fork rightFork;
    private Random random = new Random();

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
    @Override
    public void run() {
        int sec;
        message("sat on the table");

        while (true) {
            message("takes the left fork");
            if (!leftFork.take()) {
                sec = random.nextInt(4) + 1;
                message("takes left fork. Thinks " + sec + " s.");

                pause(sec);

                continue;
            }

            message("takes the right fork");
            boolean rightForkTaken = false;

            for (int i = 0; i < 2; i++) {
                if (!rightFork.take()) {
                    sec = random.nextInt(4) + 1;
                    message("right fork is taken. Thinks " + sec + " s.");
                    pause(sec);
                } else {
                    rightForkTaken = true;
                    break;
                }
            }

            if(!rightForkTaken) {
                sec = random.nextInt(4) + 1;
                message("puts left f on the table");
                leftFork.put();
                pause(sec);
                continue;
            }

            sec = random.nextInt(9) + 1;
            message("eats " + sec + " s.");
            pause(sec);

            sec = random.nextInt(4) + 1;
            message("stops eating. ");
            rightFork.put();
            leftFork.put();
            pause(sec);

        }
    }

    private void message(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }

    private void pause(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
