package com.udemy.paths;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
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

        // FileStore
        // FileSystems.getDefault() returns a FileSystem obj
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores) {
            System.out.println(store); // gets name and drive letter of file stores, could parse to get just drive letter but risky if it ever changes
            System.out.println(store.name() + "\n"); // gets the name of the drives (file stores)
        }

        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println(path); // prints the drive letters on a Windows OS, typically don't need to do this in a use-case
            // because when an application is installed it remembers/can find out where it lives on the file system
            // if it is needed the user can tell it where to save/load files to/from
        }

        System.out.println("\n Walking Tree for Dir2");
        Path dir2Path = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir2");
        try {
            Files.walkFileTree(dir2Path, new WalkFileTreePrintNames()); // walkFileTree(Path, SimpleFileVisitor)
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        /*
        * Here we're just printing out names and it doesn't matter whether we use the preVisitDirectory() to print
        * directory names or the postVisitDirectory().
        * Depending on what we want to do though it can matter.
        *
        * ie. If we want to copy a file tree then we'd want to handle the directory before handling the entries
        * because the copying of a directory will have to exist before copying the entries.
        * In this scenario, we would actually handle the directory using the preVisitDirectory()
        *
        * If we want to traverse a file tree to delete it then we'd actually want to handle the directory in the
        * postVisitDirectory() because we have to delete all the entries in the directory and all its descendants
        * before deleting the directory (it has to be empty)
        *
        * Basically, handling directories pre or post depends on what makes sense from the type of processing we want to do
        *
        * Note: We can't make any assumptions about the order in which the files and directories are visited
        * */

        System.out.println("\n Copy Dir2 to Dir4\\Dir2Copy");
        Path copyPath = FileSystems.getDefault().getPath("FileTree" + File.separator + "Dir4" + File.separator + "Dir2Copy");
        try {
            Files.walkFileTree(dir2Path, new WalkFileTreeCopyFiles(dir2Path, copyPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
