package storage;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;

import task.TaskList;

/**
 * The FileStorage class handles file operations such as creating directories,
 * reading and writing to files and also loading data from files.
 */

public class FileStorage {
    private static final String FILE_NAME = "tasks.txt";
    private static final String FILE_DIRECTORY = "./data";
    private static final String FILE_PATH = FILE_DIRECTORY + "/" + FILE_NAME;

    /**
     * Loads a specified file from a file path and creates the file
     * and the directory if they do not currently exist.
     */

    public static void loadFile() {
        try {
            File directory = new File(FILE_DIRECTORY);
            if (!directory.exists()) {
                createDirectory(directory);
            }
            File file = new File(FILE_PATH);
            if (file.exists()) {
                readDataFromFile(file);
            } else {
                createFile(file);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Creates a directory.
     * @param directory the name of the directory to be created
     */

    public static void createDirectory(File directory) {
        boolean directoryCreated = directory.mkdir();

        if (directoryCreated) {
            System.out.println("Data folder created successfully.\n");
        } else {
            System.out.println("Failed to create data folder.\n");
        }
    }

    /**
     * Creates a file.
     * @param file the name of the file to be created
     * @throws IOException when there is an error with the file creation
     */

    public static void createFile(File file) throws IOException {
        boolean fileCreated = file.createNewFile();

        if (fileCreated) {
            System.out.println("Data file created successfully.\n");
        } else {
            System.out.println("Failed to create data file.\n");
        }
    }

    /**
     * Scans through each line of the file and calls task list to convert them.
     * to tasks
     * @param file the name of the file whose content is to be read
     * @throws FileNotFoundException when the file does not exist
     */

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

    /**
     * Saves the data to a file.
     * @throws IOException when there is an error with the file creation
     */

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

    /**
     * Converts tasks details to a String and writes it to a file.
     * @param file the name of the file where the content is to be written
     * @throws IOException when there is an error with the file creation
     */

    public static void saveFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);

        for (int i = 0; i < TaskList.getLength(); i += 1) {
            fw.write(TaskList.getTasks().get(i).getTaskAsString() + "\n");
        }

        System.out.println("\nWriting successful.\n");
        fw.close();
    }
}
