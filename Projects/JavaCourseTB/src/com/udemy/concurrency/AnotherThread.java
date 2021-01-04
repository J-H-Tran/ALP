package com.udemy.concurrency;

import static com.udemy.concurrency.ThreadColor.ANSI_BLUE;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        // code that we want it to execute goes here
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());

        /*
        * Note: It is never guaranteed that the thread will sleep for the interval we specify
        * since it's possible it could be interrupted by another thread
        * Also, the JVM has to call the underlying OS to put the thread to sleep and it's possible that
        * the OS may not support the granularity that we may be asking for, ie. does not support [ns]
        * */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another thread woke me up");
            return; // terminates this thread instance after it's been interrupted
        }
        System.out.println(ANSI_BLUE + "3 seconds have passed and I'm awake");
    }
}
