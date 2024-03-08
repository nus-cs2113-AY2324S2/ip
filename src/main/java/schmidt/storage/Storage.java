package schmidt.storage;

import schmidt.command.Command;
import schmidt.exception.SchmidtException;
import schmidt.parser.Parser;
import schmidt.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of tasks in a file to be loaded from and saved to.
 */
public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return the list of tasks
     * @throws SchmidtException if there was an error loading tasks
     */
    public ArrayList<Task> load() throws SchmidtException {
        try {
            ArrayList<Task> tasks = null;
            tasks = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            boolean isFileEmpty = true;

            while (scanner.hasNextLine()) {
                isFileEmpty = false;
                String line = scanner.nextLine();
                tasks.add(Parser.parseStorageToTask(line));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            throw new SchmidtException("Storage file not found\n" + "Starting with an empty task list");
        }
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks the list of tasks
     * @throws SchmidtException if there was an error saving tasks
     */
    public void save(TaskList tasks) throws SchmidtException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);

                fileWriter.write(Parser.parseTaskToStorage(task));
            }
            fileWriter.close();
        } catch (Exception e) {
            throw new SchmidtException("An error occurred while saving tasks");
        }
    }
}
