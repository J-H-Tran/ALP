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

//        Path filePath = FileSystems.getDefault().getPath("files","SubDirectoryFile.txt");
        Path filePath = Paths.get(".","files","SubDirectoryFile.txt");  // current working directory, file directory, filename
        printFile(filePath);

        filePath = Paths.get("D:\\JavaMasterclassBuchalka\\Projects\\OutThere.txt");
        printFile(filePath);

        filePath = Paths.get(".");
        System.out.println(filePath.toAbsolutePath());

        // generally, good practice to normalize a file path before using it.
        Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "SubDirectoryFile.txt");
        System.out.println(path2.normalize().toAbsolutePath());
        printFile(path2.normalize());

        /*
        * Why did was java.nio.file introduced in Java8 when Java7 had java.io.File class?
        *
        * Although useful there were some issues with the java.io.File class
        * Many methods didn't throw exceptions or provide specific error messages when they fail
        * File.delete() - couldn't tell if file didn't exist or wrong permissions when it fails
        * File.rename() - worked differently on different platforms and Java was meant to be platform independent
        * No support for symbolic links (a file that points to another file, often used in networks to point to remote locations)
        * Can't get metadata about a file, ownership, security, permissions
        * Many methods don't perform well when working with lots of data, possible to hang when requesting list of all files in a directory
        * Walking a directory is problematic since there's no support for symbolic links
        * */
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
