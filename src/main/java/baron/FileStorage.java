package baron;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

import task.TaskList;

public class FileStorage {
    private static final String FILE_NAME = "tasks.txt";
    private static final String FILE_DIRECTORY = "./data";
    private static final String FILE_PATH = FILE_DIRECTORY + "/" + FILE_NAME;

    public static void loadFile() {
        try {
            File directory = new File(FILE_DIRECTORY);
            if (!directory.exists()) {
                boolean directoryCreated = directory.mkdir();
                if (directoryCreated) {
                    System.out.println("Data folder created successfully.\n");
                } else {
                    System.out.println("Failed to create data folder.\n");
                }
            }
            File file = new File(FILE_PATH);
            if (file.exists()) {
                readDataFromFile(file);
            } else {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("Data file created successfully.\n");
                } else {
                    System.out.println("Failed to create data file.\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    public static void readDataFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        System.out.println("Retrieving file details.");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            TaskList.readTaskFromFile(line);
        }
        System.out.println();
        sc.close();
    }

    public static void save() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("Data file created successfully.\n");
            } else {
                System.out.println("Failed to create data file.\n");
            }
        }
        saveFile(file);
    }
    public static void saveFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < TaskList.getLength(); i += 1) {
            fw.write(TaskList.getTasks().get(i).getTaskAsString() + "\n");
        }
        System.out.println("\nWriting successful.\n");
        fw.close();
    }
}
