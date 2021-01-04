package com.udemy.paths;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadDirectoryNIOMain {
/*
* A directory's content is the list of files that it contains*/
    public static void main(String[] args) {

        Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");

        // newDirectoryStream() returns a directory stream of type Path. Returns only directory's direct descendants
        // can filter DirectoryStream for specific file types by specifying 2nd arg using glob syntax for file pattern matching
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, "*.dat")) {
            for (Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) { // good practice to explicitly catch DirectoryIteratorException when iterating over DirectoryStream
            System.out.println(e.getMessage());
        }
    }
}
