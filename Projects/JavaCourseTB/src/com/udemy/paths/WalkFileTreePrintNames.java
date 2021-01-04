package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreePrintNames extends SimpleFileVisitor<Path> {
    /* Walking the Directory Tree
     * FileVisitor Interface:
     * methods to implement
     * preVisitDirectory() - accepts reference to a directory and the BasicFileAttributes instance for the directory
     *                     called before entries in the directory are visited
     * postVisitDirectory() - accepts reference to a directory and an exception object (if necessary)
     *                     called after entries in directory and all its descendants have been visited
     *                     exception arg set when an exception happens during traversal of the entries and descendants
     * Note: Possible to skip files while traversing so not every file has to be visited for this method to be called
     * Every file has to be visited or explicitly skipped and if an exception is thrown and not handled traversal ends prematurely
     * and postVisitDirectory() is called
     *
     * visitFile() - probably most used method. Accepts a reference to the file and a BasicFileAttributes instance
     *                     This is where we run the code that will operate on the file. Only called for files
     * visitFileFailed() - called when a file CANNOT be accessed. An exception that's thrown is passed to the method
     *                     Can decide to handle the Exception. Can be called for files and directories
     * When walking a file tree we consider the starting position as the 'root' directory
     *
     * we can only go down the file tree and NOT up the file tree
     *
     * common use case:
     * - for finding files
     * - searching for files
     * */

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println(dir.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    // If we don't implement this and an error occurs then an IOException will be thrown
    // Instead, we can notify the user or handle the error and then return CONTINUE if we want the traversal to continue
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }
}
