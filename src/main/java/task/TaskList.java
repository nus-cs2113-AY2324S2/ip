package carrot.task;

import carrot.storage.Storage;
import carrot.ui.Ui;
import java.util.ArrayList;

/**
 * Represents a collection of tasks and provides methods to manipulate the task list.
 */
public class TaskList {
    private static ArrayList<Task> listOfTasks = Storage.loadListOfTasks();;

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public static void addTask(Task task) {
        listOfTasks.add(task);
        Storage.writeAllTasksToStorage(listOfTasks);

        Ui.printAddedTask(task, listOfTasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public static void deleteTask(int taskIndex) {
        try {
            Task taskToRemove = listOfTasks.get(taskIndex);
            listOfTasks.remove(taskIndex);
            Storage.writeAllTasksToStorage(listOfTasks);

            Ui.printDeletedTask(taskToRemove, listOfTasks);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }

    /**
     * Changes the completion status of a task in the task list.
     *
     * @param isDone    the new completion status of the task
     * @param taskIndex the index of the task whose status is to be changed
     */
    public static void changeTaskStatus(boolean isDone, int taskIndex) {
        // listOfTasks is indexed from 0.
        // taskIndex is indexed from 1, as how the user sees the list is ordered
        Task task = listOfTasks.get(taskIndex - 1);

        task.setStatus(isDone);
        Storage.writeAllTasksToStorage(listOfTasks);
        Ui.printChangedTaskStatus(isDone, task);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return the list of tasks
     */
    public static ArrayList<Task> getTaskList() {
        return listOfTasks;
    }
}
