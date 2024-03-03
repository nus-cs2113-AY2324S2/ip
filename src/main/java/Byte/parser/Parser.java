package Byte.parser;

import Byte.exception.ByteException;
import Byte.task.Deadline;
import Byte.task.Event;
import Byte.task.Task;
import Byte.task.TaskList;
import Byte.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Parses user input and performs corresponding actions on tasks.
 */
public class Parser {
    private static final int TASK_TYPE_INDEX = 0;
    private static final int STATUS_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DEADLINE_TIME_INDEX = 3;
    private static final int EVENT_START_TIME_INDEX = 3;
    private static final int EVENT_END_TIME_INDEX = 4;

    /**
     * Parses user input and performs corresponding actions on tasks.
     *
     * @param userInput The user input to parse.
     * @param tasks     The task list on which to perform actions.
     * @return A string representing the response to the user input.
     * @throws ByteException If an error occurs during parsing or task handling.
     */
    public static String parse(String userInput, TaskList tasks) throws ByteException {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";
        switch (command) {
            case "list":
                return tasks.getAllTasks().isEmpty() ? "Task list is empty." : listTasks(tasks);
            case "todo":
                return addTodoTask(argument, tasks);
            case "deadline":
                return addDeadlineTask(argument, tasks);
            case "event":
                return addEventTask(argument, tasks);
            case "delete":
                return deleteTask(argument, tasks);
            case "mark":
                return markTask(argument, tasks);
            case "unmark":
                return unmarkTask(argument, tasks);
            case "find":
                return findTasks(argument, tasks);
            default:
                throw new ByteException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a task from a string representation.
     *
     * @param line The string representation of the task.
     * @return The parsed task.
     * @throws ByteException If an error occurs during parsing.
     */
    public static Task parseTaskFromLine(String line) throws ByteException {
        String[] parts = line.split(" \\| ");
        String type = parts[TASK_TYPE_INDEX];
        String status = parts[STATUS_INDEX];
        String description = parts[DESCRIPTION_INDEX];
        boolean isDone = status.equals("1");
        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                String by = parts[DEADLINE_TIME_INDEX];
                LocalDate deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return new Deadline(description, deadlineDate, isDone);
            case "E":
                String startTime = parts[EVENT_START_TIME_INDEX];
                String endTime = parts[EVENT_END_TIME_INDEX];
                return new Event(description, startTime, endTime, isDone);
            default:
                throw new ByteException("Invalid task type found in file.");
        }
    }

    /**
     * Checks if the user input is an exit command.
     *
     * @param userInput The user input to check.
     * @return true if the input is an exit command, otherwise false.
     */
    public static boolean isExitCommand(String userInput) {
        return userInput.equals("bye");
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The task list containing the tasks to be listed.
     * @return A string representing the list of tasks.
     */
    private static String listTasks(TaskList tasks) {
        StringBuilder taskListBuilder = new StringBuilder();
        taskListBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            taskListBuilder.append((i + 1)).append(". ").append(tasks.getAllTasks().get(i)).append("\n");
        }
        return taskListBuilder.toString();
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param argument The description of the todo task.
     * @param tasks    The task list to which the task should be added.
     * @return A string representing the response to adding the task.
     * @throws ByteException If the todo description is empty.
     */
    private static String addTodoTask(String argument, TaskList tasks) throws ByteException {
        if (argument.isEmpty()) {
            throw new ByteException("The description of a todo cannot be empty.");
        }

        Task todo = new ToDo(argument);
        return addTask(todo, tasks);
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param userInput The user input specifying the deadline task.
     * @param tasks     The task list to which the task should be added.
     * @return A string representing the response to adding the task.
     * @throws ByteException If the deadline task format is invalid or the date is invalid.
     */
    private static String addDeadlineTask(String userInput, TaskList tasks) throws ByteException {
        String[] parts = userInput.trim().split("/by", 2);
        if (parts.length != 2) {
            throw new ByteException("Invalid deadline task format. Please specify deadline using /by.");
        }
        String description = parts[0];
        String by = parts[1].trim();

        if (description.isEmpty()) {
            throw new ByteException("The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new ByteException("The deadline date cannot be empty.");
        }

        try {
            LocalDate deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task deadline = new Deadline(description, deadlineDate);
            return addTask(deadline, tasks);
        } catch (DateTimeException e) {
            throw new ByteException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Adds an event task to the task list.
     *
     * @param userInput The user input specifying the event task.
     * @param tasks     The task list to which the task should be added.
     * @return A string representing the response to adding the task.
     * @throws ByteException If the event task format is invalid.
     */
    private static String addEventTask(String userInput, TaskList tasks) throws ByteException {
        String trimmedInput = userInput.trim();
        if (!trimmedInput.contains(" /from ") || !trimmedInput.contains(" /to ")) {
            throw new ByteException("Invalid event task format. Please specify event timing using /from and /to.");
        }
        String descriptionPart = trimmedInput.split(" /from ", 2)[0];
        String timingPart = trimmedInput.split(" /from ", 2)[1];
        String[] timingDetails = timingPart.split(" /to ", 2);
        String startTime = timingDetails.length > 0 ? timingDetails[0].trim() : "";
        String endTime = timingDetails.length > 1 ? timingDetails[1].trim() : "";

        if (descriptionPart.isEmpty()) {
            throw new ByteException("The description of an event cannot be empty.");
        }
        if (startTime.isEmpty() || endTime.isEmpty()) {
            throw new ByteException("Both the start time and end time for the event must be specified.");
        }

        Task event = new Event(descriptionPart, startTime, endTime);
        return addTask(event, tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param argument The index of the task to be deleted.
     * @param tasks    The task list from which the task should be deleted.
     * @return A string representing the response to deleting the task.
     * @throws ByteException If the task index is invalid.
     */
    private static String deleteTask(String argument, TaskList tasks) throws ByteException {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task deletedTask = tasks.getAllTasks().get(index);
            tasks.deleteTask(index);
            return "Noted. I've removed this task:\n  " + deletedTask + "\nNow you have " + tasks.getAllTasks().size() + " tasks in the list.";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ByteException("Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param argument The index of the task to be marked as done.
     * @param tasks    The task list containing the task to be marked.
     * @return A string representing the response to marking the task as done.
     * @throws ByteException If the task index is invalid or the task is already marked as done.
     */
    private static String markTask(String argument, TaskList tasks) throws ByteException {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.getTask(index);
            if (task == null) {
                throw new ByteException("Task not found.");
            }
            if (task.isDone()) {
                throw new ByteException("Task is already marked as done.");
            }
            task.markAsDone();
            return "Nice! I've marked this task as done:\n" + task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ByteException("Please enter a valid task number.");
        }
    }

    /**
     * Marks a task as not done in the task list.
     *
     * @param argument The index of the task to be marked as not done.
     * @param tasks    The task list containing the task to be marked.
     * @return A string representing the response to marking the task as not done.
     * @throws ByteException If the task index is invalid or the task is already marked as not done.
     */
    private static String unmarkTask(String argument, TaskList tasks) throws ByteException {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.getTask(index);
            if (task == null) {
                throw new ByteException("Task not found.");
            }
            if (!task.isDone()) {
                throw new ByteException("Task is already marked as not done.");
            }
            task.markAsNotDone();
            return "OK, I've marked this task as not done yet:\n" + task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ByteException("Please enter a valid task number.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task  The task to be added.
     * @param tasks The task list to which the task should be added.
     * @return A string representing the response to adding the task.
     */
    private static String addTask(Task task, TaskList tasks) {
        if (task.isDone()) {
            task.markAsDone();
        }
        tasks.addTask(task);
        return "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getAllTasks().size() + " tasks in the list.";
    }

    /**
     * Finds tasks in the task list that contain a specified keyword.
     *
     * @param keyword The keyword to search for.
     * @param tasks   The task list in which to search for the keyword.
     * @return A string representing the matching tasks found.
     */
    private static String findTasks(String keyword, TaskList tasks) {
        List<Task> foundTasks = tasks.findTasksByKeyword(keyword);
        if (foundTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder foundTasksMessage = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                Task task = foundTasks.get(i);
                String taskString = String.format("%d. %s", i + 1, task);
                foundTasksMessage.append(taskString).append("\n");
            }
            return foundTasksMessage.toString();
        }
    }

}
