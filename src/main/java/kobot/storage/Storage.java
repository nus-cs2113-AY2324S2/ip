package kobot.storage;

import kobot.task.TaskList;
import kobot.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "tasks.txt";

    /**
     * Creates storage file.
     */
    public static void createFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.close();
        } catch (IOException exception) {
            Ui.printCreateFileErrorMessage();
            System.exit(0);
        }
    }

    /**
     * Loads tasks from storage file.
     * 
     * @return TaskList with list of loaded tasks from file.
     */
    public static TaskList loadFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            TaskList taskList = new TaskList();
            while (scanner.hasNext()) {
                taskList.loadTask(scanner.nextLine());
            }
            return taskList;
        } catch (FileNotFoundException exception) {
            createFile();
            return new TaskList();
        }
    }

    /**
     * Updates storage file with the current task list.
     * 
     * @param taskList Current list of tasks.
     */
    public static void updateFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(taskList.toStorageFormat());
            fileWriter.close();
        } catch (IOException exception) {
            Ui.printUpdateFileErrorMessage();
            System.exit(0);
        }
    }
}
