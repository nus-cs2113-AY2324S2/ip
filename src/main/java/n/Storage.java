/**
 * Storage helps to handle the saving of the Task List to
 * the .txt text file as well as the loading of saved data
 * from the .txt text file into a Task List.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n;

import n.exceptions.EmptyTaskDescriptionException;
import n.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

import static n.TaskList.taskList;

public class Storage {
    /** The file path where the task list is stored. */
    private static final String FILEPATH = "./data/n.txt";
    private static String filePath;
    public Storage() {
        filePath = FILEPATH;
    }
    /**
     * Loads saved data from the .txt text file into the
     * Task List.
     *
     * @throws FileNotFoundException        If the file is not found.
     * @throws EmptyTaskDescriptionException If a task has an empty description.
     */
    public void loadTaskList() throws EmptyTaskDescriptionException, IOException {
        Files.createDirectories(Paths.get("data"));
        if (Files.exists(Path.of(filePath), LinkOption.NOFOLLOW_LINKS)) {
            File initialTaskList = new File(filePath);
            Scanner s = new Scanner(initialTaskList);
            while (s.hasNextLine()){
                String taskDescription = s.nextLine();
                // Skip header and footer lines
                if (taskDescription.equals("Task List:") || taskDescription.startsWith("Number of Tasks")) {
                    continue;
                }
                // Determine task type and description
                Type taskType = Parser.getTaskType(taskDescription);
                String task = Parser.textToTaskDescription(taskDescription, taskType);
                // Create task object based on type and add to the task list
                switch (taskType) {
                    case ToDo:
                        taskList.add(new ToDo(task, taskList.size()));
                        break;
                    case Event:
                        taskList.add(new Event(task, taskList.size()));
                        break;
                    case Deadline:
                        taskList.add(new Deadline(task, taskList.size()));
                        break;
                }
                //mark task if task is completed
                if (taskDescription.charAt(8) == 'X') {
                    taskList.get(taskList.size() - 1).setDone(true);
                }
            }
            s.close();
        }
    }
    /**
     * Saves the current task list to the file.
     */
    public void saveTaskList() {
        Path filePath = Path.of(Storage.filePath);
        try {
            // Delete existing file and create a new one
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
            Files.write(filePath, ("Task List:\n").getBytes(), StandardOpenOption.APPEND);
            for (Task task : taskList) {
                // Write each task list information to the file
                Files.write(filePath, (task.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(filePath, ("Number of Tasks: " +taskList.size()).getBytes(), StandardOpenOption.APPEND);
            Ui.printTaskListSavedMessage(filePath);
        } catch (IOException e) {
            Ui.printMessage(Ui.SAVE_AS_FILE_ERROR);
        }
    }
}
