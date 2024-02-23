package tasks;

import storage.SaveManager;
import java.util.ArrayList;
import java.io.IOException;

public class ListKeeper {
    private ArrayList<Task> tasks;

    public ListKeeper() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds the task to the list of tasks.
     * @param task a task object to be added to the list
     */
    public void addToList(Task task) {
        this.tasks.add(task);
        System.out.println("Added task: " + task);
    }

    /**
     * Deletes the task at the specified index.
     * @param taskIndex the index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        Task removedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        System.out.println("Removed task: " + removedTask);
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("You have " + this.tasks.size() + " tasks");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }
    }

    /**
     * Checks if the input index is a valid task index.
     * @param inputIndex the index to be checked
     * @return true if the index is valid, false otherwise
     */
    public boolean isValidTaskIndex(int inputIndex) {
        return inputIndex >= 1 && inputIndex <= this.tasks.size();
    }

    /**
     * Marks the task specified as completed or not completed.
     * @param inputIndex the index of the task to be marked
     * @param isCompleted whether the task is to be marked as completed or not completed
     */
    public void processMark(int inputIndex, boolean isCompleted) {
        Task task = this.tasks.get(inputIndex - 1);
        task.mark(isCompleted);
    }

    /**
     * Saves the tasks data to the hard disk.
     * @param saveManager the SaveManager object to manage the saving of data
     */
    public void saveTasksData(SaveManager saveManager) {
        try {
            saveManager.clearFile();
            if (this.tasks.isEmpty()) {
                return;
            }
            for (Task task : this.tasks) {
                System.out.println("Saving: " + task);
                saveManager.writeToFile(task.getStringRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Data could not be saved");
        }
    }
}
