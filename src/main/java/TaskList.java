import Quokka.exceptions.QuokkaException;
import Quokka.tasks.*;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final int MAX_TASKS = 100;
    private List<Task> tasks = new ArrayList<>();

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

    public void markTaskAsDone(String userInput) {
        try {
            updateTaskStatus(userInput, true, "Nice! I've marked this task as done:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

    public void markTaskAsNotDone(String userInput) {
        try {
            updateTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

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
