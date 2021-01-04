package com.udemy.paths;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreeCopyFiles extends SimpleFileVisitor<Path> {
    /* Copy everything in the Dir2 folder to a Dir2 directory within the Dir4 directory
     *
     * Need to override visitFile(), visitFileFailed() and preVisitDirectory()
     * */

    private Path sourceRoot;
    private Path targetRoot;

    public WalkFileTreeCopyFiles(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }



    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relativizedPath = sourceRoot.relativize(dir); // get relative path
        System.out.println("Relativized path = " + relativizedPath);

        Path copyDir = targetRoot.resolve(relativizedPath);// get destination path for the copy
        System.out.println("Resolved path for copy = " + copyDir);

        try {
            Files.copy(dir, copyDir);   // copies the directory/file (entries), copy(from, to)
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativizedPath = sourceRoot.relativize(file); // get relative path
        System.out.println("Relativized path = " + relativizedPath);

        Path copyDir = targetRoot.resolve(relativizedPath);// get destination path for the copy
        System.out.println("Resolved path for copy = " + copyDir);

        try {
            Files.copy(file, copyDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return FileVisitResult.CONTINUE;
    }
    /*
    * In order to figure out the path for the copied file we're going to use the path.relativize()
    * this method constructs a relative path that resolves to a given path.
    *
    * ie. if the path that we are relativizing against is C:\path1
    * and we give the path to relativize as C:\path1\path2\path3
    * then the relativized path results in \path2\path3, which is the given path relative to C:\path1
    *
    * Once we have the relative path we need to resolve it against the copied directory's location
    * by calling the path.resolve() that will turn the relative path we got from relativize()
    * into the full path for the copied file
    *
    * We call the relativized method to get the relative path to the source root
    *
    * Example:
    * sourcePath = "FileTree\\Dir2\\Dir3\\file1.txt";
    * sourceRoot = "FileTree\\Dir2";
    * targetRoot = "FileTree\\Dir4\\Dir2Copy";
    * relativizedPath = sourceRoot.relativize(sourcePath); // -> "Dir3\\file1.txt"
    * resolvedPathForCopy = targetRoot.resolve(relativizedPath); // -> "FileTree\\Dir4\\Dir2Copy\\Dir3\\file1.txt"
    *
    * Essentially, figure out the relative pth to the sourceRoot and then appending it to the targetRoot
    * because, when doing a copy of the relative path to both the source and target roots will be the same
    *
    * We need the sourceRoot path and targetRoot path to figure out the path for the copy
    * but the preVisitDirectory() and visitFile() are only passed a reference to a directory/file that's being visited
    * We have to save the source and destination roots as instance variables in the class implementing SimpleFileVisitor
    * Consequently, we're going to add those fields in the constructor to handle that
    *
    * Can pass more parameters to the copy method to specify what should happen if the destination path already exists
    * to handle use cases in real world applications
    * */
}
