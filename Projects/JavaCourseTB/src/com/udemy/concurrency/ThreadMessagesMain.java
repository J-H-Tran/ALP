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
    private String message;
    private boolean isEmpty = true;

    public synchronized String read() {
        while (isEmpty) {

        }
        isEmpty = true; // true when there's no message to read
        return message;
    }
    // we want the consumer to read each message before we write another one
    public synchronized void write(String message) {
        while (!isEmpty) {

        }
        isEmpty = false;
        this.message = message;
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
    * issues a notification, using notify() or notifyAll(), that something important has happened
    * */
}

class Writer implements Runnable { // producer class
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messages[] = {
                "Humpty dumpty sat on a wall,",
                "Humpty dumpty had a great fall,",
                "All the king's horses and all the king's men,",
                "Couldn't put Humpty together again."
        };

        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);

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