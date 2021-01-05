package com.udemy.concurrency;

public class DeadlockMain {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();

    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1 has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 1 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1 has lock2");
                }
                System.out.println("Thread 1 released lock2");
            }
            System.out.println("Thread 1 released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 2 has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 2 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 2 has lock1 & lock2");
                }
                System.out.println("Thread 2 released lock2");
            }
            System.out.println("Thread 2 released lock1. Exiting...");
        }
    }
}


/*
* Deadlock
* when 2 or more threads are blocked on locks and every thread that is blocked is holding a lock that another blocked
* thread wants.
*
* Q. How to prevent deadlocks?
* One solution would be to only ever try to lock on a single obj and if you can write code so that only one lock
* is required, the code won't deadlock. Unfortunately, that's not a practical solution for apps with multiple locks.
*
* Another way, is to require that all threads must first try to obtain the locks in the same order.
* deadlock above occurred when thread1 tries to obtain lock1 then lock2, and thread2 tries to obtain lock2 then lock1.
* Now, if we made both threads obtain the locks in the same order a deadlock cannot occur.
*
* Only the thread that has already obtained the outer lock can obtain the inner lock. So it is not possible for
* one of the threads to hold one lock and not the other lock.
*
* This is the key to avoiding deadlocks when 2 or more threads will be competing for 2 or more locks.
* We want to ensure that all the threads try to obtain the locks in the same order.
*
* Another solution would be to use a Lock obj rather than using synchronized blocks and using tryLock()
* */