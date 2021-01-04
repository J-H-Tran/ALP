package com.udemy.concurrency;

import java.util.Random;

public class AnalyzeThreadMain {
    public static void main(String[] args) {
        MessageT message = new MessageT();
        (new Thread(new WriterT(message))).start();
        (new Thread(new ReaderT(message))).start();
    }
}

class MessageT {
    private String messageStr;
    private boolean isEmpty = true;

    public synchronized String read() {
        while (isEmpty == true) { // thread is waiting until write() the data to messageStr & sets isEmpty to false & ultimately calls notifyAll() to wake up the Reader thread
            // at which point Reader's run() can execute, there is now data in messageStr to be printed out to the console by the Reader thread (reads in data from Writer thread)
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        isEmpty = true;
        notifyAll(); // wakes up Writer thread and executes its run()
        return messageStr; // at this point messageStr has already been updated by Writer thread after nofityAll(), messageStr that is returned is still previous value
    }
    
    public synchronized void write(String message) {
        while (isEmpty == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        isEmpty = false;
        this.messageStr = message;
        notifyAll();
    }
}

class WriterT implements Runnable {
    private MessageT message;

    public WriterT(MessageT message) {
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
            } catch (InterruptedException e) { }
        }
        message.write("Finished"); // after all the Strings have been read and output to the console by Reader thread
    }
}

class ReaderT implements Runnable {
    private MessageT message;

    public ReaderT(MessageT message) {
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        for (String latestMessageT = message.read(); !latestMessageT.equals("Finished"); latestMessageT = message.read()) {
            System.out.println(latestMessageT); // only gets updated when Writer calls notifyAll()
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) { }
        }
    }
}