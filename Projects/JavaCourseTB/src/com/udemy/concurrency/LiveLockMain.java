package com.udemy.concurrency;

public class LiveLockMain {

    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1", true);
        final Worker worker2 = new Worker("Worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource, worker1);
            }
        }).start();

    }
}
/*
 * Live lock issue:
 * similar to dead lock but instead of threads being blocked they're constantly active and usually waiting for all
 * the other threads to complete their tasks.When all the threads are waiting for others to complete, none of them
 * can actually progress.
 *
 * if t1 loops as it waits for t2 to complete, and t2 loops waiting for t1 to complete.
 * it could so happen that they both begin looping and are waiting for the other to complete and they never will.
 * t1 and t2 are not blocked though.
 * */