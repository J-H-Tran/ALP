package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PathsAndFilesNIOMain {

    public static void main(String[] args) {
        try {
            Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Files.copy(sourceFile, copyFile);   // Errors if file to be copied already exists
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING); // 3rd arg will copy file even if it already exists

            // copying a directory, only the folder and not the files it contains. To do that you have to walk the file tree
            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

            // moving files

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
