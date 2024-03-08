package Yoj.taskList;

import Yoj.exception.YojException;
import Yoj.tasks.Deadline;
import Yoj.tasks.Event;
import Yoj.tasks.ToDo;
import Yoj.tasks.Task;
import Yoj.ui.*;
import java.io.IOException;
import java.util.ArrayList;

public class List {
    public static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Adds a new task to the list based on user input.
     *
     * @param userInput   input from the user specifying the task to add.
     * @throws YojException      If the task cannot be added due to invalid format or other reasons.
     * @throws IOException       If there is an error during input/output operation.
     */
    public static void addTask(String userInput) throws YojException, IOException {
        if (userInput.startsWith("todo")) {
            manageToDo(userInput);
        } else if (userInput.startsWith("deadline")) {
            manageDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            manageEvent(userInput);
        }
        Ui.printShortLine();
        Ui.addTaskMessage();
        Ui.printLine();
    }
    /**
     * Deletes a task from the list based on the task index provided by the user.
     *
     * @param userInput  input from the user specifying the task number to delete.
     * @throws YojException  If the task number is not specified or invalid.
     */
    public static void deleteTask(String userInput) throws YojException {
        String taskNum = userInput.substring("delete".length()).trim();
        if (taskNum.isEmpty()) {
            throw new YojException("do specify the task number of what u want to delete...");
        }
        try {
            int num = Integer.parseInt(taskNum);
            if (tasks.size() == 0) {
                Ui.noDeleteMessage();
            } else if (num > 0 && num <= tasks.size()) {
                Task deletedTask = tasks.remove(num - 1);
                Ui.taskDeletedMessage(deletedTask);
            } else {
                Ui.wrongDeleteMessage();
            }
        } catch (NumberFormatException e) {
            Ui.wrongDeleteMessage();
        }

    }
    /**
     * Handles adding a ToDo task.
     *
     * @param userInput  input from the user containing the task description.
     * @throws YojException If the description is empty.
     * @throws IOException  If there is an error during input/output operation.
     */
    public static void manageToDo(String userInput) throws YojException, IOException {
        String description = userInput.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of todo cannot be empty...");
        } else {
            tasks.add(new ToDo(userInput.substring(5)));
        }
    }
    /**
     * Handles adding a Deadline task.
     *
     * @param userInput  input from the user containing the task description and deadline.
     * @throws YojException If the description is empty or improperly formatted.
     * @throws IOException  If there is an error during input/output operation.
     */
    public static void manageDeadline(String userInput) throws YojException, IOException {
        String description = userInput.substring("deadline".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of deadline cannot be empty...");
        } else {
            String[] parts = userInput.substring(9).split(" /by ");
            tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        }
    }
    /**
     * Handles adding an Event task.
     *
     * @param userInput input from the user containing the task description and event times.
     * @throws YojException If the description is empty or improperly formatted.
     * @throws IOException  If there is an error during input/output operation.
     */
    public static void manageEvent(String userInput) throws YojException, IOException {
        String description = userInput.substring("event".length()).trim();
        if (description.isEmpty()) {
            throw new YojException("The description of event cannot be empty...");
        } else {
            String[] parts = userInput.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            tasks.add(new Event(parts[0].trim(), times[0].trim(), times[1].trim()));
        }
    }
    /**
     * Searches for tasks that contain the given input by users.
     *
     * @param userInput The specific keyword to find.
     */
    public static void findTask(String userInput) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(userInput)) {
                tasksFound.add(task);
            }
        }
        if (tasksFound.isEmpty()){
            Ui.printNoTaskFound();
        } else {
            Ui.printTaskFound(tasksFound);
        }
    }

}
