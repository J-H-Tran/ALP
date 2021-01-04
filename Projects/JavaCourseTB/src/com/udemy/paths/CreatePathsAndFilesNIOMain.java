package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CreatePathsAndFilesNIOMain {

    public static void main(String[] args) {

        try {
            /*Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
            Files.createFile(fileToCreate);*/ // exception to rule, can't be used to create directories

            // creating directory
            /*Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.createDirectory(dirToCreate);*/

            // create directories
            /*Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6");
            Files.createDirectories(dirToCreate);*/

            // get files attributes/metadata
            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1\\file1.txt");
            long size = Files.size(filePath);
            System.out.println("size = " + size);
            System.out.println("size = " + Files.getLastModifiedTime(filePath));

            // get all attributes of the file
            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            // readAttributes() gets the basic set of attributes, specify which set in 2nd arg
            // in this case readAttributes() returns an instance that implements the BasicFileAttributes Interface, there are other system specific sub-interfaces
            System.out.println("size = " + attributes.size());
            System.out.println("lastModifiedTime = " + attributes.lastModifiedTime());
            System.out.println("isSymbolicLink = " + attributes.isSymbolicLink());
            System.out.println("creationTime = " + attributes.creationTime());
            System.out.println("fileKey = " + attributes.fileKey());
            System.out.println("isDirectory = " + attributes.isDirectory());    // does filePath point to a directory?
            System.out.println("isRegularFile = " + attributes.isRegularFile());// does filePath point to a regular file?
            // java.nio Does contain other interfaces for file attributes that are OS specific
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
