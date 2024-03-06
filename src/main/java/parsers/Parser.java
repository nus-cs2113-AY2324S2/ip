package parsers;

import commands.*;
import exceptions.KikuEmptyTaskException;
import exceptions.KikuException;
import exceptions.KikuInvalidTaskException;

import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

/**
 * Parses user input into command objects for execution.
 * This class supports parsing of different types of commands,
 * including adding todos, deadlines, events, marking tasks as done or not done,
 * deleting tasks, finding tasks, and exiting KikuBot.
 */
public class Parser {
    private static final String HORIZONTAL = "____________________________________________________________";

    /**
     * Parses the given user input into a specific command object.
     *
     * @param userInput The raw input string from the user.
     * @return A Command object representing the user's command.
     * @throws KikuException If the input is invalid or incomplete.
     */
    public static Command parseCommand(String userInput) throws KikuException {
        if (userInput.trim().isEmpty()) {
            throw new KikuInvalidTaskException("OOPS! Input cannot be empty! \n" + HORIZONTAL);
        }

        String commandType = userInput.split(" ")[0];
        //String arguments = userInput.substring(userInput.indexOf(" ") + 1);
        String arguments = userInput.contains(" ") ? userInput.substring(userInput.indexOf(" ") + 1) : "";

        switch (commandType) {
        case "todo":
            if (arguments.trim().isEmpty()) {
                throw new KikuEmptyTaskException();
            }
            return new AddTodoCommand(arguments);
        case "deadline":
            String[] deadlineParts = arguments.split(" /by ", 2);
            if (deadlineParts.length < 2) {
                throw new KikuInvalidTaskException("OOPS! Please specify the deadline with \n" +
                        "<task_description> /by <due_Date> \n" + HORIZONTAL);
            }
            return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1]);
        case "event":
            String[] eventParts = arguments.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                throw new KikuInvalidTaskException("OOPS! Please specify the event with \n" +
                        "<task_description> /from <start_date_time> /to" +
                        " <end_date_time> \n" + HORIZONTAL);
            }
            String[] times = eventParts[1].split(" /to ", 2);
            return new AddEventCommand(eventParts[0], times[0], times[1]);
        case "list":
            return new ListTaskCommand();
        case "mark":
        case "unmark":
        case "delete":
            try {
                int index = Integer.parseInt(arguments) - 1;
                if (index < 0) {
                    throw new KikuInvalidTaskException("OOPS! Index must be greater than 0! \n" +
                            HORIZONTAL);
                }
                return handleIndexBasedCommand(commandType, index);
            } catch (NumberFormatException e) {
                throw new KikuInvalidTaskException("OOPS! Invalid format, " +
                        "please specify task index correctly! \n" + HORIZONTAL);
            }
        case "find":
            if (arguments.trim().isEmpty()) {
                throw new KikuInvalidTaskException("OOPS! Please enter keyword(s) to find!");
            }
            return new FindTaskCommand(arguments);
        case "bye":
            return new ExitCommand();
        default:
            throw new KikuInvalidTaskException("OOPS! Please specify a todo, deadline, or event! " +
                    "Make sure all spellings are correct ~ \n" + HORIZONTAL);
        }
    }

    /**
     * Handles commands that are based on the index of tasks,
     * including marking, unmarking, and deleting tasks.
     *
     * @param commandType The type of command, either mark, unmark or delete.
     * @param index The index of the task to be operated on.
     * @return A Command object for the specified operation.
     */
    private static Command handleIndexBasedCommand(String commandType, int index) {
        switch (commandType) {
        case "mark":
            return new MarkTaskCommand(index);
        case "unmark":
            return new UnmarkTaskCommand(index);
        case "delete":
            return new DeleteTaskCommand(index);
        default:
            throw new IllegalArgumentException("OOPS! Unhandled command type! \n" + HORIZONTAL);
        }
    }

    /**
     * Converts the textual representation of a task from the storage file into a Task object.
     * This method is used when loading tasks from the file to ensure each line representing
     * a task is correctly transformed into a Task object.
     *
     * @param line A string from the storage file that represents a single task.
     * @return A Task object represented by the line, or null if the line cannot be parsed into a Task.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        if ("T".equals(parts[0]) && parts.length >= 3) {
            task = new Todo(parts[2]);
        } else if ("D".equals(parts[0]) && parts.length >= 4) {
            task = new Deadline(parts[2], parts[3]);
        } else if ("E".equals(parts[0]) && parts.length >= 5) {
            task = new Event(parts[2], parts[3], parts[4]);
        }

        if (task != null && "1".equals(parts[1])) {
            task.markAsDone();
        }
        return task;
    }
}
