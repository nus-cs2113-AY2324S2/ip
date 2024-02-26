package geepee.task.list;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import geepee.task.Task;
import geepee.system.SystemMessage;
import geepee.system.FileHandler;

public class List {

    /** Stores the tasks currently in the list */
    protected ArrayList<Task> tasks;
    /** Handles writing and reading from the data file */
    protected FileHandler fileHandler;

    /**
     * Initialises an instance of the List class. Uses a FileHandler instance to read
     * task data from the data file and store it in the list.
     * 
     * @param filePath Filepath of data file to read from and write to.
     */
    public List(String filePath) {
        try {
            fileHandler = new FileHandler(filePath);
            tasks = fileHandler.getTasksFromFile();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
            SystemMessage.printFileNotFoundMessage();
        }
    }

    /**
     * Adds a new task to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        ListMessage.printAfterAddingTask(tasks.size(), task);
    }

    /**
     * Deletes a task at the specified index from the list.
     */
    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ListMessage.printAfterRemovingTask(tasks.size(), deletedTask);
    }

    /**
     * Changes the completion status of a task at the specified index.
     */
    public void changeTaskStatus(int index, boolean isDone) {
        tasks.get(index).changeStatus(isDone);
        ListMessage.printTaskStatusMessage(isDone, tasks.get(index));
    }

    /**
     * Prints tasks from the lists that are relevant to the given keyword.
     */
    public void findTasksFromKeyword(String keyword) {
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                relevantTasks.add(task);
            }
        }
        ListMessage.printRelevantTasks(relevantTasks);
    }

    /**
     * Returns the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Prints all the tasks currently in the list.
     */
    public void getAllTasks() {
        ListMessage.printAllTasks(tasks);
    }

    /**
     * Writes the tasks currently in the list to the data file.
     */
    public void writeTasksToFile() {
        fileHandler.writeTasks(tasks);
    }
}
