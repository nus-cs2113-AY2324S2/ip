import Quokka.exceptions.QuokkaException;
import Quokka.tasks.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks in the Quokka program.
 * It provides methods to add, delete, display, and manipulate tasks in the list.
 */
public class TaskList {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        try {
            if (tasks.size() >= MAX_TASKS) {
                throw new QuokkaException("Sorry, the task list is full. You cannot add more tasks.");
            }
            tasks.add(newTask);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        try {
            if (taskIndex < 1 || taskIndex > tasks.size()) {
                throw new QuokkaException("Invalid task index. Please provide a valid task index to delete");
            }

            Task deletedTask = tasks.remove(taskIndex - 1);

            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + deletedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list");
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }

    /**
     * Displays all tasks in the task list.
     */
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param userInput The user input containing the task index to be marked as done.
     */
    public void markTaskAsDone(String userInput) {
        try {
            updateTaskStatus(userInput, true, "Nice! I've marked this task as done:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param userInput The user input containing the task index to be marked as not done.
     */
    public void markTaskAsNotDone(String userInput) {
        try {
            updateTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

    /**
     * Updates the status of a task in the task list.
     *
     * @param userInput     The user input containing the task index and status update.
     * @param newStatus     The new status of the task (true for done, false for not done).
     * @param statusMessage The message to be displayed after updating the task status.
     */
    private void updateTaskStatus(String userInput, boolean newStatus, String statusMessage) {
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).setStatus(newStatus);
                    System.out.println("    " + statusMessage);
                    System.out.println("      " + tasks.get(taskIndex));
                } else {
                    throw new QuokkaException("Invalid task index.");
                }
            } else {
                throw new QuokkaException("Please provide a valid task index to mark as done or not done.");
            }
        } catch (NumberFormatException e) {
            System.out.println("     Error: Invalid task index format.");
        }
    }

    /**
     * Finds tasks in the task list containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTask(String keyword) {
        if (keyword.isEmpty()) {
            System.out.println("    Please provide a keyword to search for.");
            return;
        }

        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("    No matching tasks found.");
        } else {
            displayMatchingTasks(matchingTasks);
        }
    }

    /**
     * Displays matching tasks found during the search.
     *
     * @param matchingTasks The list of matching tasks to display.
     */
    private void displayMatchingTasks(List<Task> matchingTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + matchingTasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public int size() {
        return tasks.size();
    }

    public Object get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
