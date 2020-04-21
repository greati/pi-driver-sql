package br.ufrn.imd.lii.pidriver.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static boolean canWriteInFile(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean isDirectory(String path) {
        try {
            Path pathF = Paths.get(path);
            File f = new File(path);
            return f.isDirectory();
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
    }

    public static boolean pathExists(String path) {
        try {
            Path pathF = Paths.get(path);
            File f = new File(path);
            return f.exists();
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
    }

    public static boolean isValidPath(String path) {
        try {
            Paths.get(path);
        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }
        return true;
    }

}
