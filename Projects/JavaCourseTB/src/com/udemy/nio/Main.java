package com.udemy.nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
         * Channel can be used for R+W but NOT FileChannel,
         * A FileChannel created from FileInputStream only Reads.
         * A FileChannel created from FileOutputStream only Writes
         * For RandomAccessFile it depends on what param we pass to constructor, "rwd" reads
         * */
        try {
//            FileInputStream file = new FileInputStream("data.txt");
//            FileChannel channel = file.getChannel();
            Path dataPath = FileSystems.getDefault().getPath("data.txt");
            Files.write(dataPath, "\nLine 5".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);  // writes bytes and doesn't process Strings directly
                    // arg: StandardOpenOption.APPEND used if we want to write to a file that already exists and append bytes to it
                    // not specifying the third arg will create a new file if one doesn't exist, if exists will overwrite current data with new data
                    // first 2 args are required and additional are of type StandardOpenOption
            List<String> lines = Files.readAllLines(dataPath);  // reads entire file into memory

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
