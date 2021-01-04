package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreatePathsAndFilesNIOMain {

    public static void main(String[] args) {

        try {
            /*Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
            Files.createFile(fileToCreate);*/ // exception to rule, can't be used to create directories

            // creating directory
            /*Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.createDirectory(dirToCreate);*/

            // create directories
            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2\\Dir3\\Dir4\\Dir5\\Dir6");
            Files.createDirectories(dirToCreate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
