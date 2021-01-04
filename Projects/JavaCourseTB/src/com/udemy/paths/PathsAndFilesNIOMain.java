package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class PathsAndFilesNIOMain {

    public static void main(String[] args) {
        try {
            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
//            Files.delete(fileToDelete); // throws an exception if file doesn't exist to be deleted.
            Files.deleteIfExists(fileToDelete); // to handle that we call this method
            // can use method to delete directories but they have to be empty first, need to walk file tree to do so

            /*Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Files.copy(sourceFile, copyFile);   // Errors if file to be copied already exists
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING); // 3rd arg will copy file even if it already exists

            // copying a directory, only the folder and not the files it contains. To do that you have to walk the file tree
            sourceFile = FileSystems.getDefault().getPath("Examples", "Dir1");
            copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);*/

            // moving files
            /*Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            // must specify full path of destination not enough to only specify directory
            Path destination = FileSystems.getDefault().getPath("Examples", "Dir1", "file1copy.txt");
            Files.move(fileToMove, destination);*/

            // renaming a file, is effectively moving it with a different name. source and destination directories have to be the same
            /*Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path destination = FileSystems.getDefault().getPath("Examples", "file2.txt");
            Files.move(fileToMove, destination);*/
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
