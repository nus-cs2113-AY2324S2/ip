package soot.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SavedFileManager {
    public static ArrayList<String> readSavedFile(String path) throws FileNotFoundException {
        ArrayList<String> readFile = new ArrayList<>();
        File savedFile = new File(path);
        Scanner s = new Scanner(savedFile);
        while (s.hasNext()) {
            readFile.add(s.nextLine());
        }
        return readFile;
    }
}
