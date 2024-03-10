package tasks;

import storage.SaveManager;
import java.util.ArrayList;
import exceptions.InvalidTaskIndex;
import ui.UserInterface;

import java.io.IOException;

public class ListKeeper {
    private ArrayList<Task> tasks;
    private final UserInterface userInterface;

    public ListKeeper(UserInterface userInterface) {
        this.tasks = new ArrayList<>();
        this.userInterface = userInterface;
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

    /**
     * Verifies that the task index is valid.
     * A valid task index is between 1 and the number of tasks in the list.
     * @param inputIndex the index of the task to be verified
     * @throws InvalidTaskIndex if the task index is invalid
     */
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
        this.userInterface.print("Added task: " + task);
    }

    /**
     * Deletes the task at the specified index.
     * @param taskIndex the index of the task to be deleted
     * @throws InvalidTaskIndex if the task index is invalid
     */
    public void deleteTask(int taskIndex) throws InvalidTaskIndex {
        verifyTaskIndex(taskIndex);
        Task removedTask = this.tasks.get(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        this.userInterface.print("Removed task: " + removedTask);
    }

    public void printList() {
        this.userInterface.print("You have " + this.tasks.size() + " tasks");
        for (int i = 0; i < this.tasks.size(); i++) {
            this.userInterface.print(i + 1 + ". " + this.tasks.get(i));
        }
    }

    /**
     * Marks the task specified as completed or not completed.
     * @param inputIndex the index of the task to be marked
     * @param isCompleted whether the task is to be marked as completed or not completed
     * @throws InvalidTaskIndex if the task index is invalid
     */
    public void processMark(int inputIndex, boolean isCompleted) throws InvalidTaskIndex{
        verifyTaskIndex(inputIndex);
        Task task = this.tasks.get(inputIndex - 1);
        String feedback = task.mark(isCompleted);
        this.userInterface.print(feedback);
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
                this.userInterface.print("Saving: " + task);
                saveManager.writeToFile(task.getStringRepresentation());
            }
        } catch (IOException e) {
            this.userInterface.print("Data could not be saved");
        }
    }

    /**
     * Prints the tasks that contain at least one of the keywords.
     * @param keywordsToFind keywords mentioned by the user
     */
    public void printMatchingTasks(String[] keywordsToFind) {
        boolean hasMatchingTasks = false;
        for (int taskIndex = 0; taskIndex < this.tasks.size(); taskIndex++) {
            Task task = this.tasks.get(taskIndex);
            if (task.hasKeywords(keywordsToFind)) {
                hasMatchingTasks = true;
                this.userInterface.print(taskIndex + 1 + ". " + task);
            }
        }
        if (!hasMatchingTasks) {
            this.userInterface.print("No matching tasks");
        }
    }
}
