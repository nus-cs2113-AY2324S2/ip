package taskListPackage;

import taskPackage.Deadlines;
import taskPackage.Events;
import taskPackage.Task;
import taskPackage.ToDos;
import ui.Ui;
import edithExceptionPackage.ChatBotExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks and performs
 * operations like adding, deleting, and
 * marking tasks as done.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a TaskList object with the given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(List<Task> list) {
        tasks = list;
        ui = new Ui();
    }

    /**
     * Retrieves the current list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNumber The task number to delete.
     * @throws ChatBotExceptions If there are no tasks to delete
     * or if an invalid task number is provided.
     */
    public void deleteTask(String taskNumber) throws ChatBotExceptions {
        if (tasks.isEmpty()) {
            throw new ChatBotExceptions("No tasks to delete. Task list is empty.");
        }
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.remove(index);
                System.out.println("Noted. I've removed this task:");
                System.out.println("   " + removedTask.getStatusIcon() +
                        " " + removedTask.getDescription());
                ui.printFormattedMessage(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                ui.printFormattedMessage("Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            ui.printFormattedMessage("Invalid command. Please enter a valid task number to delete.");
        }
    }

    /**
     * Adds an event task to the task list.
     *
     * @param taskDescription The description of the event task.
     * @throws ChatBotExceptions If the task description is in an invalid format.
     */
    public void addEvent(String taskDescription) throws ChatBotExceptions {
        String[] parts = taskDescription.split(" from | to ");
        if (parts.length < 3) {
            throw new ChatBotExceptions("Invalid event format. " +
                    "Please use 'event <description> from <start> to <end>'.");
        }
        String description = parts[0];
        String fromDate = parts[1];
        String toDate = parts[2];
        Events newEvent = new Events(description, fromDate, toDate, false);
        addTaskToList(newEvent);
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param taskDescription The description of the deadline task.
     * @throws ChatBotExceptions If the task description is in an invalid format.
     */
    public void addDeadline(String taskDescription) throws ChatBotExceptions {
        String[] parts = taskDescription.split(" by ");
        if (parts.length < 2) {
            throw new ChatBotExceptions("Invalid deadline format. " +
                    "Please use 'deadline <description> by <date>'.");
        }
        String description = parts[0];
        String byDate = parts[1];
        Deadlines newDeadline = new Deadlines(description, byDate, false);
        addTaskToList(newDeadline);
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param taskDescription The description of the to-do task.
     */
    public void addTodo(String taskDescription) {
        ToDos newTodo = new ToDos(taskDescription, false);
        addTaskToList(newTodo);
    }

    private void addTaskToList(Task newTask) {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + newTask.getStatusIcon() + " " + newTask.getDescription());
        ui.printFormattedMessage(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks the task with the specified task number as done.
     *
     * @param taskNumber The task number to mark as done.
     */
    public void markTaskAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                ui.printFormattedMessage("   " + task.getStatusIcon()
                        + " " + task.getDescription());
            } else {
                ui.printFormattedMessage("Invalid task number." +
                        " Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            ui.printFormattedMessage("Invalid command." +
                    " Please enter a valid task number to mark as done.");
        }
    }

    /**
     * Marks the task with the specified task number as not done yet.
     *
     * @param taskNumber The task number to mark as not done.
     */
    public void unmarkTaskAsDone(String taskNumber) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                ui.printFormattedMessage("   " + task.getStatusIcon()
                        + " " + task.getDescription());
            } else {
                ui.printFormattedMessage("Invalid task number. " +
                        "Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            ui.printFormattedMessage("Invalid command. " +
                    "Please enter a valid task number to unmark.");
        }
    }

    /**
     * Finds tasks containing the specified keyword and prints them.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasksByKeyword(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        int numberOfMatchingTasks = matchingTasks.size();
        if (numberOfMatchingTasks == 0) {
            ui.printFormattedMessage("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : matchingTasks) {
                int originalIndex = tasks.indexOf(task) + 1;
                System.out.println(originalIndex + ". " + task.toString());
            }
            ui.printFormattedMessage("There are "
                    + numberOfMatchingTasks + " number of tasks found");
        }
    }
}
