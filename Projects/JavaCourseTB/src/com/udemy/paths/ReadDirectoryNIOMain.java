package com.udemy.paths;

import java.io.File;
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

        /*DirectoryStream.Filter<Path> filter =
                new DirectoryStream.Filter<Path>() { // must implement accept() method when using DirectoryStream.Filter
                                                    // returns true for a path, the path is going to be included in teh DirectoryStream results
                    public boolean accept(Path path) throws IOException {
                        return Files.isRegularFile(path); // test to see if the particular path is a regular file
                    }
                };*/
        // using lambda expression
        // since there is only 1 method to implement we can convert the above lines to a lambda
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);

//        Path directory = FileSystems.getDefault().getPath("FileTree\\Dir2");
        Path directory = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2"); // now supports all OS's

        // newDirectoryStream() returns a directory stream of type Path. Returns only directory's direct descendants
        // can filter DirectoryStream for specific file types by specifying 2nd arg using glob syntax for file pattern matching
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for (Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) { // good practice to explicitly catch DirectoryIteratorException when iterating over DirectoryStream
            System.out.println(e.getMessage());
        }

        // best practice to never hard-code file separator since may not work for all platforms
        String separator = File.separator;
        System.out.println(separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);

        try {
            // createTempFile(prefix, suffix, fileAttr)
            // 3rd arg is for file attributes that we can specify but for temp files there's often no reason to do so
            Path tempFile = Files.createTempFile("myapp", ".appext");
            // the resulting path name is always associated with the default file system, can be specified to store somewhere else
            // of course, can also create temp directory
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
