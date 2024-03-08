package parser;

import task.*;
import ui.Ui;

public class Parser {

    /**
     * Parses the user command and executes the corresponding action.
     *
     * @param command The user command to be parsed.
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui object for displaying messages.
     */
    public static void parse(String command, TaskList tasks, Ui ui) {

        /*
         * Example of commands:
         *
         * todo borrow book - Add a Todo task "borrow book"
         * event project meeting /from 2pm /to 4pm - Add an Event task "project meeting", start at "2pm" and end at "4pm"
         * deadline return book /by Sunday - Add a Deadline task "return book" by "Sunday"
         * list - List all tasks
         *
         * mark 3 - Mark task 3 as done
         * unmark 3 - Mark task 3 as NOT done
         * delete 2 - Delete task 2
         *
         * blah - Invalid command
         * bye - Exit the application
         */

        // Split the command into two parts: action and description
        String[] parts = command.split(" ", 2);
        String action = parts[0];

        switch (action) {
            case "todo":
            case "event":
            case "deadline":
                addCommand(parts, tasks, ui);
                break;
            case "mark":
                markTaskAsDone(Integer.parseInt(parts[1]), tasks, true);
                break;
            case "unmark":
                markTaskAsDone(Integer.parseInt(parts[1]), tasks, false);
                break;
            case "delete":
                deleteCommand(parts, tasks, ui);
                break;
            case "list":
                if (parts.length > 1) {
                    ui.invalidCommand();
                    return;
                }
                listCommand(tasks);
                break;
            case "find":
                findCommand(parts, tasks, ui);
                break;
            // Duke.java will handle the "bye" command
            default:
                ui.invalidCommand();
                break;
        }
    }

    private static boolean isCommandValid(String[] parts) {
        return parts.length < 2;
    }

    private static void addCommand(String[] parts, TaskList tasks, Ui ui) {
        String taskType = parts[0];
        if (isCommandValid(parts)) {
            ui.invalidTaskFormat(parts[0]);
            return;
        }
        String taskDescription = parts[1];

        switch (taskType) {
            case "todo":
                addTodoTask(taskDescription, tasks, ui);
                break;
            case "deadline":
                addDeadlineTask(taskDescription, tasks, ui);
                break;
            case "event":
                addEventTask(taskDescription, tasks, ui);
                break;
            default:
                ui.invalidTaskFormat("task");
                break;
        }
    }

    private static void deleteCommand(String[] parts, TaskList tasks, Ui ui) {
        if (isCommandValid(parts)) {
            ui.invalidIndex();
            return;
        }
        if (!parts[1].matches("\\d+")) {
            ui.invalidIndex();
            return;
        }
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.deleteTask(taskNumber);
    }

    private static void listCommand(TaskList tasks) {
        tasks.listTasks();
    }

    private static void addTodoTask(String taskDescription, TaskList tasks, Ui ui) {
        if (taskDescription.isEmpty()) {
            ui.invalidTaskFormat("todo");
            return;
        }
        Task task = new Todo(taskDescription);
        tasks.addTask(task);
    }

    private static void addEventTask(String taskDescription, TaskList tasks, Ui ui) {
        if (taskDescription.isEmpty()) {
            ui.invalidTaskFormat("event");
            return;
        }
        String[] eventParts = taskDescription.split(" /from ", 3);
        if (eventParts.length < 2) {
            ui.invalidTaskFormat("event");
            return;
        }
        String eventDescription = eventParts[0];
        String[] timeParts = eventParts[1].split(" /to ", 2);
        if (timeParts.length < 2) {
            ui.invalidTaskFormat("event");
            return;
        }
        String start = timeParts[0];
        String end = timeParts[1];
        Task task = new Event(eventDescription, start, end);
        tasks.addTask(task);
    }

    private static void addDeadlineTask(String taskDescription, TaskList tasks, Ui ui) {
        String[] deadlineParts = taskDescription.split(" /by ", 2);
        if (deadlineParts.length < 2 ||deadlineParts[0].isEmpty() || deadlineParts[1].isEmpty()) {
            ui.invalidTaskFormat("deadline");
            return;
        }
        String deadlineDescription = deadlineParts[0];
        String by = deadlineParts[1];
        Task task = new Deadline(deadlineDescription, by);
        tasks.addTask(task);
    }

    private static void markTaskAsDone(int taskNumber, TaskList tasks, boolean markDone) {
        tasks.markTaskAsDone(taskNumber, markDone);
    }

    private static void findCommand(String[] parts, TaskList tasks, Ui ui) {
        if (parts.length < 2) {
            ui.showTaskNotFound();
            return;
        }
        String keyword = parts[1];
        tasks.findTasks(keyword);
    }
}