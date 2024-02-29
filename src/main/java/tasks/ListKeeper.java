package tasks;

import storage.SaveManager;
import java.util.ArrayList;
import exceptions.InvalidTaskIndex;
import java.io.IOException;

public class ListKeeper {
    private ArrayList<Task> tasks;

    public ListKeeper() {
        this.tasks = new ArrayList<>();
    }

    public static String getSampleListCommand() {
        return "To list all tasks: list";
    }
    public static String getSampleDeleteCommand() {
        return "To delete the task at index x: delete x";
    }
    public static String getSampleMarkCommand() {
        return "To mark the task at index x as completed: mark x";
    }
    public static String getSampleUnmarkCommand() {
        return "To mark the task at index x as not completed: unmark x";
    }

    private void verifyTaskIndex(int inputIndex) throws InvalidTaskIndex {
        if (inputIndex >= 1 && inputIndex <= this.tasks.size()) {
            return;
        }
        if (this.tasks.isEmpty()) {
            throw new InvalidTaskIndex("The list is empty");
        }
        throw new InvalidTaskIndex("The task index is invalid. Select a task index between 1 and " + this.tasks.size());
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
    public void deleteTask(int taskIndex) throws InvalidTaskIndex {
        verifyTaskIndex(taskIndex);
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
     * Marks the task specified as completed or not completed.
     * @param inputIndex the index of the task to be marked
     * @param isCompleted whether the task is to be marked as completed or not completed
     */
    public void processMark(int inputIndex, boolean isCompleted) throws InvalidTaskIndex{
        verifyTaskIndex(inputIndex);
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

    public void printMatchingTasks(String[] keywordsToFind) {
        boolean hasMatchingTasks = false;
        for (int taskIndex = 0; taskIndex < this.tasks.size(); taskIndex++) {
            Task task = this.tasks.get(taskIndex);
            if (task.hasKeywords(keywordsToFind)) {
                hasMatchingTasks = true;
                System.out.println(taskIndex + 1 + ". " + task);
            }
        }
        if (!hasMatchingTasks) {
            System.out.println("No matching tasks");
        }
    }
}
