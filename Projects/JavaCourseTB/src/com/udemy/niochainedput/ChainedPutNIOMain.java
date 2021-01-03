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
            long int1Pos = outputBytes.length; // tracking buffer position right at each putInt()
            buffer.putInt(245);         // next 4 bytes holds Integer 245
            long int2Pos = int1Pos + Integer.BYTES;
            buffer.putInt(-98765);      // next 4 bytes holds Integer -98765

            byte[] outputBytes2 = "Nice to meet you".getBytes(); // initialize a second byte array with String 16 bytes long
            buffer.put(outputBytes2);   // next 16 bytes filled with byte data from second String
            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
            buffer.putInt(1000);        // next 4 bytes holds Integer 1000, buffer position 40

//            buffer.put(outputBytes).putInt(245).putInt(-98765).put(outputBytes2).putInt(1000); // put chaining is possible because put() also returns the buffer position
            buffer.flip();              // reset buffer position to 0
            binChannel.write(buffer);   // write() writes to file & reads from buffer. Read data from buffer and write into data.dat file

            // Read data from data.dat file and output to console
            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");  // specify read mode
            FileChannel channel = ra.getChannel();  // gets a Channel object to act as bridge between buffer and data entities

            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(int3Pos);  // specify start position in the channel from data source, bridge to data.dat
            channel.read(readBuffer);   // read() from file and write to buffer 4 bytes

            readBuffer.flip();
            System.out.println("int3 = " + readBuffer.getInt()); // read from buffer and output to console

            readBuffer.flip();
            channel.position(int2Pos);
            channel.read(readBuffer);

            readBuffer.flip();
            System.out.println("int2 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(int1Pos);
            channel.read(readBuffer);

            readBuffer.flip();
            System.out.println("int1 = " + readBuffer.getInt());

            RandomAccessFile copyFile = new RandomAccessFile("datacopy.dat", "rw");
            FileChannel copyChannel = copyFile.getChannel();
            channel.position(0);    // need to set the channel position to 0 before calling transferFrom()
//            long numTransferred = copyChannel.transferFrom(channel, 0, channel.size()); // position here is relative to current position
            long numTransferred = channel.transferTo(0, channel.size(), copyChannel);
            System.out.println("Number of bytes transferred = " + numTransferred);

            channel.close();
            copyChannel.close();
            ra.close();

            // calculate all start positions
            /*byte[] outputString = "Hello, World!".getBytes();
            long str1Pos = 0;
            long newInt1Pos = outputString.length;
            long newInt2Pos = newInt1Pos + Integer.BYTES;
            byte[] outputString2 = "Nice to meet you".getBytes();
            long str2Pos = newInt2Pos + Integer.BYTES;
            long newInt3Pos = str2Pos + outputString2.length;

            // initialize buffer
            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);

            intBuffer.putInt(245);          // Write to buffer

            intBuffer.flip();
            binChannel.position(newInt1Pos);// At start pos for 1st Integer
            binChannel.write(intBuffer);    // Read from buffer, writes to the channel

            intBuffer.flip();
            intBuffer.putInt(-98765);       // Write to buffer

            intBuffer.flip();
            binChannel.position(int2Pos);   // At next pos for 2nd Integer
            binChannel.write(intBuffer);    // Read from buffer, writes to the channel

            intBuffer.flip();
            intBuffer.putInt(1000);         // Write to buffer

            intBuffer.flip();
            binChannel.position(newInt3Pos);// At next pos for 3rd Integer
            binChannel.write(intBuffer);    // Read from buffer, writes to the channel

            binChannel.position(str1Pos);
            binChannel.write(ByteBuffer.wrap(outputString)); // wrap() takes care of flipping the buffer for us
            binChannel.position(str2Pos);
            binChannel.write(ByteBuffer.wrap(outputString2));*/

            /*ByteBuffer readBuffer = ByteBuffer.allocate(100);   // initialize readBuffer with capacity of 100
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
            System.out.println("int3 = " + readBuffer.getInt());*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
