package com.udemy.concurrency;

public class MultipleThreadMain {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();
//        Countdown countdown1 = new Countdown();
//        Countdown countdown2 = new Countdown();

        // no interference if we pass the threads their own obj to work on but not practical in real world applications
        // how to handle then when threads work on a single obj? make the method synchronized
        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t1.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown {
    private int i; // different results when threads share an instance of a variable, unexpected behavior

    public void doCountdown() { // to make a method synchronized we add the synchronized keyword
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

//        synchronized (color) { // doesn't work since it's a local variable not shared by the threads, ea thread has it's own color obj
        synchronized (this) { // synchronizing on the obj shared by the 2 threads, this way only one thread can execute the for loop at a time
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
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
/* Q. How to properly handle the race condition between threads?
*
* 2 ways:
* 1) synchronize methods
* 2) synchronize a block of statements rather than entire methods
*
* Synchronization - the process of controlling when threads execute code and therefore, when they can access the heap
* When a method is synchronized only one thread can execute that method at a time
* so when the thread is executing the method all other threads that want to call the method, or any other synchronized method
* in that class, will be suspended until the first thread running the method exits. Then another thread will be able to run that method, etc.
*
* If a class has multiple synchronized methods, only one of those methods can be run at a time and only on a single thread.
* Since only one thread can execute a synchronized method at a time, threads cannot interleave when running a synchronized
* method. This prevents 'thread interference' within synchronized methods.
*
* It's possible to see 'thread interference' if a non-synchronized method also accesses an instance variable that is accessed
* by a synchronized method.
*
* We can't synchronize the constructor. It doesn't make sense anyway because only one thread can construct an instance
* and until the constructor has finished executing the instance won't be available for other threads to use.
*-----------------------------------------------------------------------------------------------------------------------
* Every Java obj has an 'intrinsic lock' aka 'monitor'
* so we can synchronize a block of statements that work with an obj by forcing threads to acquire the obj's lock before
* they execute the statement block.
*
* Only one thread can hold the lock at a time so other threads that want the lock will be suspended until the running thread
* releases the lock. Then only one of the waiting threads can get the lock and continue executing.
*
* Only objects have an intrinsic lock so we cannot synchronize a primitive value.
* The only exception to this rule are String obj
*
* When using a local obj variable the obj references are stored in the thread stack but the obj values are stored in the heap
* Since the threads will each create their own copy of the local 'String color' the obj references will be different and
* there won't be any interference even though the obj values are in the heap.
*
* Essentially, the thread stack will only ever contain primitive values and obj references (and function references).
*
* We can also synchronize static methods and also use static obj, when we do this the lock that is used is owned
* by the class obj associated with the obj's class.
*-----------------------------------------------------------------------------------------------------------------------
* Synchronization is 'reentrant'. This means that if a thread acquires an obj's lock, and within the synchronized code,
* it calls a method that's using the same obj to synchronize some code. Then the thread can keep executing because,
* it already has the obj's lock. A thread can acquire a lock it already owns.
*
* Critical section - refers to the code that is referencing a shared resource, like a variable.
* Only one thread at a time should be able to execute a critical section.
*
* Thread safe - A developer has synchronized all the critical sections within the code so that we don't have to worry
* about thread interference.
*
* When synchronizing code, we should only synchronize code that absolutely needs to be synchronized. We don't want threads
* to be unnecessarily suspended/blocked because that can affect performance and user experience.
*
* Methods that can only be called within synchronized code:
* wait(), notify(), notifyAll()
* consumer producer example
* */