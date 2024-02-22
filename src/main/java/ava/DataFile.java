package ava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class DataFile {
    protected static final String FILE_PATH = "data/ava.txt";

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        if (!file.exists()) {
            createFile();
        } else {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }

    private static void createFile() {
        try {
            File dataFile = new File(FILE_PATH);
            if (!dataFile.getParentFile().exists()) {
                Files.createDirectories(dataFile.getParentFile().toPath());
            }
            if (dataFile.createNewFile()) {
                System.out.println("File created: " + dataFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    protected static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }
}
