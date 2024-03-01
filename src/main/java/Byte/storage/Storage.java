package Byte.storage;

import Byte.exception.ByteException;
import Byte.parser.Parser;
import Byte.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the parent directory for the given file if it does not exist.
     *
     * @param file The file for which to create the parent directory.
     */
    private static void createParentDirectory(File file) {
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean directoriesCreated = parentDirectory.mkdirs();
            if (!directoriesCreated) {
                System.err.println("Failed to create directories for file: " + file.getPath());
            }
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the storage file.
     * @throws ByteException If an error occurs while loading tasks from the file.
     */
    public List<Task> load() throws ByteException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        createParentDirectory(file);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tasks.add(Parser.parseTaskFromLine(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new ByteException("File not found.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an error occurs while writing tasks to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        createParentDirectory(file);

        FileWriter writeToFile = new FileWriter(filePath);
        for (Task task : tasks) {
            writeToFile.write(task.toFileString() + "\n");
        }
        writeToFile.close();

    }

}
