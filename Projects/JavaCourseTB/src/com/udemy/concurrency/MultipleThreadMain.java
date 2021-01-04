package com.udemy.concurrency;

public class MultipleThreadMain {
    public static void main(String[] args) {
        Countdown countdown1 = new Countdown();
        Countdown countdown2 = new Countdown();

        // no interference if we pass the threads their own obj to work on but not practical in real world applications
        CountdownThread t1 = new CountdownThread(countdown1);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown2);
        t1.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown {
    private int i; // different results when threads share an instance of a variable, unexpected behavior

    public void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        /* local variable vs shared instance
        * Each thread has it's own copy of a local variable separate from other threads. It's own local copy,
        * which exists in it's own Thread Stack
        *
        * But, when we declare a variable private like above, each obj instance will share the same instance of that variable
        * which exists in the applications Heap
        * So any change made by one thread will be seen by other threads when it wants to access that variable
        *
        * The for loop below is actually multiple operations of execution and it's possible for a thread to be suspended
        * between each step of the for loop iteration
        * When threads interact with one another like this over a single resource it's known as 'thread interference' aka 'race condition'
        * Problem doesn't arise if the threads were only reading the resource, only when at least one of the threads is writing/updating it
        * */
        for (i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
        }
    }
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown(); // executes when a Thread instance of this class is started
    }
}