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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static void createParentDirectory(File file) {
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            boolean directoriesCreated = parentDirectory.mkdirs();
            if (!directoriesCreated) {
                System.err.println("Failed to create directories for file: " + file.getPath());
            }
        }
    }

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
