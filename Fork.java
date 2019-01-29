package com.LIcompany;

public class Fork {
    private boolean busy = false;

    public boolean isBusy() {
        return busy;
    }

    public synchronized boolean take() {
        if(busy) {
            return false;
        }

        busy = true;

        return true;
    }

    public void put() {
        busy = false;
    }
}
