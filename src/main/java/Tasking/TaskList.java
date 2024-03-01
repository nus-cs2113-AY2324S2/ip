package Tasking;

import java.io.IOException;
import java.util.ArrayList;

import static Storage.Storage.rewriteFile;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a single task into an ArrayList of tasks
     * This function can be called when initialising data into the program or can be called
     * by the user when creating a task
     *
     * @param userTask the task to be added
     * @param isInitMode to determine whether printing is required
     * @throws IOException if there is an issue with writing data
     */
    public static void addTask(Task userTask, boolean isInitMode) throws IOException {
        list.add(userTask);
        if (!isInitMode) {
            // Used for normal task adding, printing not needed when initialising
            System.out.println(" Got it. mark I've added this task:");
            System.out.println(" " + userTask);
            System.out.println(" Now you have " + list.size() + " tasks in the list.");
            Davvy.writeData(userTask);
        }
    }

    /**
     * Remove a task from the ArrayList of Tasks
     *
     * @param index pass in the index of the task to be deleted
     * @throws IOException if there is an issue with writing data
     */
    public static void deleteTask(int index) throws IOException {
        Task task = list.get(index - 1);
        list.remove(index - 1);
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println(" Now you have " + list.size() + " tasks in the list.");
        rewriteFile();
    }

    public static Task getTask(int taskNumber) {
        return list.get(taskNumber);
    }

    public static int listLength() {
        return list.size();
    }

    /**
     * Prints every item in the ArrayList indexed with numbers
     */
    public static void printList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(" " + (i+1) + ".");
            System.out.println(list.get(i));
        }
    }
}