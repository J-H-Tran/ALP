package com.udemy.concurrency;

import java.util.Random;

public class ThreadMessagesMain {
    // working with 2 threads: one that produces messages and another that consumes messages
    public static void main(String[] args) {
        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message {
    private String messageStr;
    private boolean isEmpty = true;

    public synchronized String read() {
        while (isEmpty) {
            try { // waiting for the other thread to change isEmpty to false, exits loop then notify the other thread before returning the message
                wait(); // thread will release obj's lock which gives another thread the opportunity to run
            } catch (InterruptedException e) { }
        }
        isEmpty = true; // true when there's no message to read
        notifyAll(); // wakes up the other thread to continue write()
        return messageStr;
    }
    // we want the consumer to read each message before we write another one
    public synchronized void write(String message) {
        while (!isEmpty) {
            try { // thread waits until isEmpty is set to true by the other thread, sets value of message before notifying the other thread
                wait();
            } catch (InterruptedException e) { }
        }
        isEmpty = false;
        this.messageStr = message;
        notifyAll(); // wakes up the other thread to continue read()
    }
    /*
    * read() and write() is synchronized because when a thread is running one of these methods we don't want
    * another thread to be able to run either method. When read() is running write() shouldn't be, vice versa.
    *
    * Deadlock - When one thread is holding the lock (when looping) of an obj and the other one is blocked and waiting for the
    * other thread to release the lock
    *
    * To handle a deadlock we can use wait(), notify(), and notifyAll()
    * When a thread calls wait() it will suspend execution and release whatever lock it's holding until another thread
    * issues a notification, using notify() or notifyAll(), that something important has happened.
    *-------------------------------------------------------------------------------------------------------------------
    * Q. Why loop, why not use an if statement and if that fails call wait()?
    * A. We always want to call wait() within a for loop that's testing for whatever condition we're waiting on
    *   because when a thread is notified to wake up there's no guarantee that it's being woken up because the
    *   condition it's waiting on has changed. So it's possible the OS has woken it up for some other reason or,
    *   it could have woken up because wait() threw an InterruptedException.
    *
    * So, we always want to call wait() within a loop so that when it returns, because of a notification of some sort,
    * we'll go back to the beginning of the loop and check whatever condition we're interested in and then we call wait()
    * if the condition has not changed. NEVER assume that a thread has woken up because the condition it's waiting on has changed.
    *
    * Q. Why use notifyAll() instead of notify()?
    * A. Because we can't notify a specific thread. notify() does not accept any parameters.
    *   It's conventional therefore, to use notifyAll() unless we're dealing with a situation where there are a significant
    *   number of threads that all perform a similar task waiting for a lock. In this case, we don't want to wake up
    *   every thread that could lead to a huge performance hit. So we call notify() so that only one thread is woken up
    *   since all the threads are waiting to do a similar task.
    *
    * There are a few atomic operations in Java that happen all at once. A thread can't be suspended in the middle of doing them
    * - Reading & Writing reference variables, ie. myObject1.equals(myObject2) is atomic so a thread can't be suspended
    *   in the middle of executing this statement
    * - A thread can't be suspended if Reading & Writing primitive variables except types long and double
    * - A thread can't be suspended when Reading & Writing volatile variables
    *
    * There are some collections that are NOT synchronized but there's a method available to handle that, Collections.synchronize()
    * We still have to synchronize iterating over the list though.
    * */
}

class Writer implements Runnable { // producer class
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messagesStrArr[] = {
                "Humpty dumpty sat on a wall,",
                "Humpty dumpty had a great fall,",
                "All the king's horses and all the king's men,",
                "Couldn't put Humpty together again."
        };

        Random random = new Random();

        for (int i = 0; i < messagesStrArr.length; i++) {
            message.write(messagesStrArr[i]);

            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }

        message.write("Finished");
    }
}

class Reader implements Runnable {
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        // loops as long as the message is not "Finished"
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);

            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}