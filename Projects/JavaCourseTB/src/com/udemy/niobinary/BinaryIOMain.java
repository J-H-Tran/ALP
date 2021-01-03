package com.udemy.niobinary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BinaryIOMain {

    public static void main(String[] args) {
    /*
     * Next thing we need is a buffer because we're writing to a file we have the data so
     * we can create the byte buffer that matches the length of the data.
     *
     * In the read case, we don't know the length of the data so we create a buffer of a given length and then
     * parse what it receives.
     * Note: We don't have to use the same buffer to do all R+W actions we can create as many buffers as we want
     * Buffer - container for data we want to R or W
     * Buffer capacity - number of elements it can contain
     * Buffer position - the index of the next element that should be R or W, can be greater than buffer capacity
     * Buffer mark - used by buffer.rest(), when called, the buffer's position is reset to the mark position
     *
     * If we want to rewind the buffer to a certain point we need to mark it first then call reset
     * */
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            byte[] outputBytes = "Hello World!".getBytes(); // byte array of string we're going to output
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes); // creates byte buffer from byte array, wraps byte array into the buffer (buffer is backed by byte array)
            // modifications to the buffer will reflect in the array and vice versa
            // when we call the wrap() on an array we are saying that
            // 1) At runtime we want to use the byte array as the buffer
            // 2) sets the position of the buffer to zero
            // 3) buffer's capacity weill be set to the byte array's length
            // 4) buffer's mark will be undefined, can be set later
            int numBytes = binChannel.write(buffer);
            System.out.println("numBytes written: " + numBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES); // calling .allocate() passing it size for our buffer to be so we can write any int to our buffer
                                                            // when we created the buffer, buffer position was set to 0
                                                            // calling putInt() writes the int into the buffer, changing the buffer position
                                                            // in write() R starts at buffer position (already moved), and we read nothing
                                                            // so we have to explicitly reset the position to 0
                                                            // if we want binChannel.write() to read from the beginning
                                                            // we do this with flip(), resets position to 0 and discards any previously set mark
            // Would be great if the IO methods moved buffer position to 0 automatically but that's not the case
            // As a design decision, because we may want to do multiple R or W to a buffer before W to the channel, in which case we would't reset the position to 0 after every W
            // flip() was not needed when using wrap() because it already resets position to 0. wrap() is a convenience method when writing byte arrays
            // with int long short using putInt() we have to explicitly take care of resetting buffer
            // possible to write to specific index in the buffer by specifying start index as 2nd arg in put()
            intBuffer.putInt(245); // R op, moves buffer position
            intBuffer.flip(); // reset buffer position to 0
            numBytes = binChannel.write(intBuffer); // W op, moves buffer position
            System.out.println("numBytes written: " + numBytes);
                                                             // don't need to flip() when doing multiple W one after the other but when switching to R ops, must do hence, flip
            intBuffer.flip(); // common mistake to forget to call this when R+W to reset buffer position to 0 when switching between Read ops and Writing ops
            intBuffer.putInt(-98765);
            intBuffer.flip(); // R from buffer then W to channel, flip() needed
            numBytes = binChannel.write(intBuffer);
            System.out.println("numBytes written: " + numBytes);

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();
            outputBytes[0] = 'a';
            outputBytes[1] = 'b';
            buffer.flip();
            long numBytesRead = channel.read(buffer); // reads 'Hello World!' from file back into byte array
            if (buffer.hasArray()) {
                System.out.println("byte buffer = " + new String(buffer.array()));
//                System.out.println("byte buffer = " + new String(outputBytes));
            }

            //Absolute Read
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            //Relative Read
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer); // writes the buffer
//
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt()); // reads the buffer
//
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

            channel.close();
            ra.close();
//            System.out.println("outputBytes = " + new String(outputBytes));
//
//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            byte[] b = new byte[outputBytes.length];
//            ra.read(b);
//            System.out.println(new String(b));
//
//            long int1 = ra.readInt();
//            long int2 = ra.readInt();
//            System.out.println(int1);
//            System.out.println(int2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
