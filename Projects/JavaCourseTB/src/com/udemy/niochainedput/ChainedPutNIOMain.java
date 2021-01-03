package com.udemy.niochainedput;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChainedPutNIOMain {

    public static void main(String[] args) {

        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            // Write a bunch of data to output file data.dat
            ByteBuffer buffer = ByteBuffer.allocate(100);   // creates buffer with capacity/limit of 100
            byte[] outputBytes = "Hello World!".getBytes(); // initialize byte array with byte data of 'Hello World!' String

            buffer.put(outputBytes);    // first 12 bytes of buffer filled with byte array data
            buffer.putInt(245);         // next 4 bytes holds Integer 245
            buffer.putInt(-98765);      // next 4 bytes holds Integer -98765

            byte[] outputBytes2 = "Nice to meet you".getBytes(); // initialize a second byte array with String 16 bytes long
            buffer.put(outputBytes2);   // next 16 bytes filled with byte data from second String
            buffer.putInt(1000);        // next 4 bytes holds Integer 1000, buffer position 40

            buffer.flip();              // reset buffer position to 0
            binChannel.write(buffer);   // write() writes to file & reads from buffer. Read data from buffer and write into data.dat file

            // Read data from data.dat file and output to console
            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");  // specify read mode
            FileChannel channel = ra.getChannel();  // gets a Channel object to act as bridge between buffer and data entities

            ByteBuffer readBuffer = ByteBuffer.allocate(100);   // initialize readBuffer with capacity of 100
            channel.read(readBuffer);   // reads data from file and Write to buffer, in this case the entire file at once. Smart enough to stop reading when EOF is reached

            readBuffer.flip();          // readBuffer position 0, switch to Read from buffer
            byte[] inputString = new byte[outputBytes.length]; // initialize empty byte array with length of String outputBytes, which it's data is already present in buffer
            readBuffer.get(inputString); // Reads buffer and writes readBuffer data into byte array, buffer position 12
            System.out.println("inputString = " + new String(inputString));
            System.out.println("int1 = " + readBuffer.getInt()); // position at 12, but after this line executes read position will be 16 (4 bytes for Integer)
            System.out.println("int2 = " + readBuffer.getInt()); // after read buffer position is 20

            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("inputString2 = " + new String(inputString2));
            System.out.println("int3 = " + readBuffer.getInt());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
