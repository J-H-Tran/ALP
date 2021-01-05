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
 *
 * Not really a one size fits all solution. Keep in mind that any time threads have to wait for other threads to
 * complete something and they don't block while they wait, there is a potential for a live lock.
 *
 * Another potential problem when using threads Slip Condition (a specific kind of race condition aka thread interference)
 * occurs when a thread can be suspended between reading a condition and acting on that condition
 * If we haven't synchronized the code properly the following can happen:
 * 1. T1 checks the status and gets OK, suspends
 * 2. T2 checks the status and gets OK, reads EOF from buffer, sets status to EOF, terminates
 * 3. T2 runs again, attempts to read from buffer, throws Exception/crashes
 *
 * Because the threads can interfere with each other when checking and setting the condition, T1 tried to do something
 * based on obsolete information, when it checked the stats it was OK.
 * By the time it acted on the condition it checked previously, the status had been updated by T2. T1 is unaware
 * and an error occurred.
 *
 * similar solution to this as other kinds of thread interference:
 * - Use synchronized blocks or locks to synchronize the critical sections of code.
 *
 * Overview:
 * - if code is already synchronized then the placement of the synchronization may be the problem
 * - when using multiple locks, the order in which the locks can be acquired can also result in slipped condition
 * */