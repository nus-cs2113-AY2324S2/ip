package Events;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Storage.Store;
import HikoUi.Ui;

public class TaskList {
    private static ArrayList<Task> list;
    private Store store; // Reference to the Store for saving changes

    /**
     * Constructs a new TaskList instance. Initializes the internal list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Associates a Store object with this TaskList for data persistence.
     *
     * @param store The Store object to be used for saving task changes.
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public static int getTotalTasks(){
        return list.size();
    }

    /**
     * Retrieves a task by its position in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index){
        return list.get(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (list.isEmpty());
    }

    /**
     * Removes a task from the list at a specified index.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {list.remove(index);
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearAllTasks() {
        this.list.clear(); // Assuming the internal task list is named 'list'
    }

    /**
     * Adds a task to the list and persists changes if a Store is associated.
     *
     * @param task The Task object to add to the list.
     */

    public void addTask(Task task) {
        list.add(task);
        if (store != null) store.saveTasks();
        Ui.divider();
        System.out.println("Added: " + task);
        Ui.divider();
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return An ArrayList containing all tasks in the list.
     */
    public ArrayList<Task> getTasks() {
        return list;
    }

}
