package helpy;

import helpy.exceptions.EventDateSequenceException;
import helpy.exceptions.IllegalDescriptionException;
import helpy.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage system for tasks.
 */
public class Storage {
    protected String filePath;
    protected File savedTasks;

    /**
     * Constructs a new Storage object with the given file path and task list.
     *
     * @param filePath The file path where the tasks should be stored.
     * @param taskList The task list for tasks to be loaded into from the storage file.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        savedTasks = new File(filePath);
        try {
            if (savedTasks.getParentFile().mkdirs() && savedTasks.createNewFile()) {
                return;
            }
        } catch (IOException e) {
            System.out.println("Error creating save file.");
            return;
        }
        try {
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNext()) {
                String taskInfo = scanner.nextLine();
                loadTask(taskInfo, taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found.");
        }


    }

    /**
     * Loads a task from a line of save data information and adds it to the task list.
     *
     * @param taskInfo The string representation of the task.
     * @param taskList The task list object to add the loaded task to.
     */
    public void loadTask(String taskInfo, TaskList taskList) {
        String[] details = taskInfo.split(" \\| ");
        if (details[0].equals("T")) {
            try {
                Todo newTodo = new Todo(details[2]);
                if (details[1].equals("1")) {
                    newTodo.setDone(true);
                }
                taskList.addTask(newTodo);
            } catch (IllegalDescriptionException ignored) {} // Ignore corrupted lines
        } else if (details[0].equals("E")) {
            try {
                Event newEvent = new Event(details[2]);
                if (details[1].equals("1")) {
                    newEvent.setDone(true);
                }
                taskList.addTask(newEvent);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException |
                     EventDateSequenceException ignored) {} // Ignore corrupted lines
        } else if (details[0].equals("D")) {
            try {
                Deadline newDeadline = new Deadline(details[2]);
                if (details[1].equals("1")) {
                    newDeadline.setDone(true);
                }
                taskList.addTask(newDeadline);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException ignored) {} // Ignore corrupted lines
        }
    }

    /**
     * Updates the storage file to the current state of the task list.
     *
     * @param taskList The task list to be written to the storage file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void update(TaskList taskList) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath);
        for (Task task : taskList.getTasks()) {
            task.saveToFile(filePath);
        }
        helpyWriter.close();
    }
}
