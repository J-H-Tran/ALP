package com.udemy.paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsNIOMain {
    /*
     * So we can create, read, and write files and data sources using java.nio
     * Sometimes you don't want to read from a file or write to a file
     * You want to copy files, delete files, move files, etc.
     * In other words, you want to work with the file system, java.nio covers that!
     * It has a whole package dedicated to working with the file system: java.nio.file
     *
     * Path Interface
     * */
    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        printFile(path);

        Path filePath = FileSystems.getDefault().getPath("files","SubDirectoryFile.txt");
        printFile(filePath);

        filePath = Paths.get("D:\\JavaMasterclassBuchalka\\Projects\\OutThere.txt");
        printFile(filePath);
    }

    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
