package com.udemy.concurrency;

import static com.udemy.concurrency.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        // code that we want it to execute goes here
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());
    }
}
