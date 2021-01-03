package com.udemy.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipesNIOMain {

    public static void main(String[] args) {
/*
* Using pipe to send data from one thread to another.
*
* There are other ways for threads to communicate and pass data so it's a good idea to benchmark
* the different ways and then choose the one that's most efficient.
*
* Sometimes using java.nio pipe may not actually make sense.
* */
        try { // need 2 threads, Write & Read
            Pipe pipe = Pipe.open();

            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink(); // get SinkChannel
                        ByteBuffer buffer = ByteBuffer.allocate(56); // allocate capacity for buffer

                        for (int i = 0; i < 10; i++) { // loop here so thread terminates at some point
                            String currentTime = "The time is: " + System.currentTimeMillis();

                            buffer.put(currentTime.getBytes()); // Write to buffer

                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);  // Read from buffer, Write to SinkChannel
                            }

                            buffer.flip();                  // flip here for next iteration of loop to start W op
                            Thread.sleep(100);         // sleep to allow reader thread chance to read SourceChannel
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source(); // get SourceChannel
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i = 0; i < 10; i++) { // loop here so thread terminates at some point
                            int bytesRead = sourceChannel.read(buffer); // Write to buffer
                            byte[] timeString = new byte[bytesRead];

                            buffer.flip();
                            buffer.get(timeString);                 // Read from buffer
                            System.out.println("Reader Thread: " + new String(timeString));

                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            // start threads. First writer then reader
            new Thread(writer).start();
            new Thread(reader).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
