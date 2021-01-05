package com.udemy.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.udemy.concurrency.ConcurrentMain.EOF;

public class ConcurrentMain {
    public static final String EOF = "EOF";

    // producer consumer example
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

    }
}

class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer; // buffer shared by consumers
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() { // creating buffer
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + " Adding.." + num);

                bufferLock.lock(); // acquires the lock, if it can't thread is suspended
                buffer.add(num); // write numbers to the buffer
                bufferLock.unlock(); // releases the lock, we're responsible for this. doesn't happen automatically unlike with synchronized blocks
                // if we forget to unlock(), threads waiting for a lock with be stuck in blocked
                Thread.sleep(random.nextInt(1000)); // because we sleep here we give the consumer thread a chance to remove the string before producer runs again
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color + " Adding EOF and exiting..");

        bufferLock.lock();
        buffer.add("EOF"); // write EOF to let consumers know that there won't be anymore data to process
        bufferLock.unlock();
    }
}

// normally, wouldn't be using loops like this in threads with real world applications. Use wait(), notify(), notifyAll() or system to manage threads
// just for demonstration
class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) { // accepts buffer shared from the producer and color String
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() { // processing buffer

        while (true) { // loop until reads EOF from the buffer
            bufferLock.lock();
            if (buffer.isEmpty()) { // checks to see if there's anything to read in the buffer, continues to loop until there is something to read
                bufferLock.unlock();
                continue;
            }

            if (buffer.get(0).equals(EOF)) { // checks to see if buffer is at EOF, if it is breaks out of loop
                System.out.println(color + " Exiting.."); // checking for and not removing EOF in case other threads look for EOF to stop looping
                bufferLock.unlock();
                break;
            } else {
                System.out.println(color + " Removed " + buffer.remove(0)); // print, then continue checking the buffer for data
            }
            bufferLock.unlock();
        }
    }
}
/*
 * To help developers write code that involves multiple threads the Java developers at Oracle introduced
 * java.util.Concurrent package in Java1.5 with several subpackages. The classes in interfaces in this package
 * tree are there to help developers properly synchronize code and make it easier to work with multiple threads.
 *
 * Before Java 1.5 and introduction of java.util.concurrent synchronization was really the only tool when it came to
 * dealing with thread interference but it still has some drawbacks.
 *
 * First, threads that are blocked waiting to execute synchronized code cannot be interrupted once they're blocked
 * they're stuck there until they get the lock for the obj the code is synchronizing on. This poses potential problems.
 *
 * Second, synchronized blocks must be within the same method. We cannot start a synchronized block in one method, and
 * end the synchronization block in another.
 *
 * Third, we cannot test to see if an obj's intrinsic lock is available or find out any other info about the lock's state.
 * Also, if the lock isn't available we cannot timeout after we waited for the lock for a while when we reach the
 * beginning of a synchronized block. We can either get the lock and continue exec or block at that line of code until
 * we get the lock.
 *
 * Fourth, if multiple threads are waiting to get a lock, it's not 'first come first served'. There isn't a set order
 * in which the JVM will choose the next thread that gets the lock. So the first thread that blocked could be the last
 * thread to get the lock, and vice versa.
 *
 * So, instead of using synchronization we can prevent thread interference using class that implement the java.util.concurrent
 * locks.Lock Interface
 * ---------------------------------------------------------------------------------------------------------------------
 * Reentrant lock obj keep track of how many times they locked so if the same thread gets the same lock twice it
 * has to release it twice before another thread can get the lock
 * */