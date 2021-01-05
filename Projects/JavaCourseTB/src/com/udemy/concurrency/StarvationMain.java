package com.udemy.concurrency;

public class StarvationMain {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (lock) {
                    System.out.format(threadColor+"%s: runCount = %d\n", Thread.currentThread().getName(), runCount++);
                    // execute critical section of code
                }
            }
        }
    }
}
/*
 * Thread Starvation
 * This doesn't describe a behavior that threads will never progress because they never acquire a lock.
 * Instead, they rarely get the chance to run and progress. Starvation often occurs due to thread priority when we
 * assign a high priority to a thread we are suggesting to the OS that it should try and run the thread before
 * other waiting threads.
 *
 * Key takeaway:
 * Although the lock is released frequently, it's often that the highest priority thread will hog all the time until
 * it's finished executing. The next thread that runs won't necessarily be the next highest priority or the first
 * one that was blocked. The last thread to terminate won't necessarily be the thread with lowest priority.
 *
 * In real world apps the tasks that a thread is performing might take a while. If run() is querying data other threads
 * could block there for seconds/minutes because other threads are always chosen to run when the lock becomes available.
 * The apps performance will definitely be adversely affected.
 *
 * We can see what starvation can do when synchronized blocks aren't first come first served. Setting priority can
 * increase likelihood of starvation and threads can block for very long even when a lock is frequently released.
 *
 * for deadlocks, order in which locks are acquired is important
 * for starvation, order in which threads gets to run is important
 * */