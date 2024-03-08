package schmidt.parser;

import schmidt.command.*;
import schmidt.exception.SchmidtException;
import schmidt.task.Deadline;
import schmidt.task.Event;
import schmidt.task.Todo;
import schmidt.task.Task;

/**
 * Represents a parser to parse user input into commands.
 */
public class Parser {
    /**
     * Parses a task into a line of storage.
     *
     * @param task the task
     * @return the line of storage
     * @throws SchmidtException if the task is invalid
     */
    public static String parseTaskToStorage(Task task) throws SchmidtException {
        try {
            String taskType;
            String taskStatus;
            String taskDescription;
            String taskTime;
            if (task instanceof Todo) {
                taskType = "T";
                taskStatus = task.getStatus() ? "1" : "0";
                taskDescription = task.getDescription();
                return taskType + " | " + taskStatus + " | " + taskDescription + "\n";
            } else if (task instanceof Deadline) {
                taskType = "D";
                taskStatus = task.getStatus() ? "1" : "0";
                taskDescription = task.getDescription();
                taskTime = ((Deadline) task).getBy();
                return taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskTime + "\n";
            } else if (task instanceof Event) {
                taskType = "E";
                taskStatus = task.getStatus() ? "1" : "0";
                taskDescription = task.getDescription();
                taskTime = ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                return taskType + " | " + taskStatus + " | " + taskDescription + " | " + taskTime + "\n";
            } else {
                throw new SchmidtException("Task is invalid");
            }
        } catch (Exception e) {
            throw new SchmidtException("An error occurred while saving tasks");
        }
    }

    /**
     * Parses a line of storage into a task.
     *
     * @param line the line of storage
     * @return the task
     * @throws SchmidtException if the line is invalid
     */
    public static Task parseStorageToTask(String line) throws SchmidtException {

        String[] tokens = line.split(" \\| ");

        switch (tokens[0]) {
        case "T":
            Todo todo = new Todo(tokens[2]);
            if (tokens[1].equals("1")) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Deadline deadline = new Deadline(tokens[2], tokens[3]);
            if (tokens[1].equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            Event event = new Event(tokens[2], tokens[3], tokens[4]);
            if (tokens[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        default:
            throw new SchmidtException("Saved tasks are corrupted\n" + "Starting with a new task list");
        }
    }

    /**
     * Parses the user input into a command.
     *
     * @param command the user input
     * @return the command to be executed
     * @throws SchmidtException if the command is invalid
     */
    public static Command parseCommand(String command) throws SchmidtException {
        String trimmedCommand = command.trim();
        String[] commandArray = trimmedCommand.split(" ");
        String commandType = commandArray[0];
        switch (commandType) {
        case "list":
            // list tasks
            return new ListCommand();
        case "mark":
            // mark task as done
            int markIndex;
            try {
                markIndex = parseIndexCommand(trimmedCommand);
            } catch (SchmidtException e) {
                throw new SchmidtException("Please follow the mark command format\n\tmark <task number>");
            }
            return new MarkCommand(markIndex, true);
        case "unmark":
            // mark task as undone
            int unmarkIndex;
            try {
                unmarkIndex = parseIndexCommand(trimmedCommand);
            } catch (SchmidtException e) {
                throw new SchmidtException("Please follow the unmark command format\n\tunmark <task number>");
            }
            return new MarkCommand(unmarkIndex, false);
        case "bye":
            // exit program
            return new ExitCommand();
        case "help":
            // display help message
            return new HelpCommand();
        case "delete":
            // delete task
            int deleteIndex;
            try {
                deleteIndex = parseIndexCommand(trimmedCommand);
            } catch (SchmidtException e) {
                throw new SchmidtException("Please follow the delete command format\n\tdelete <task number>");
            }
            return new DeleteCommand(deleteIndex);
        case "todo":
            // add todo task
            Todo todo = parseTodoCommand(trimmedCommand);
            return new AddCommand(todo);
        case "deadline":
            // add deadline task
            Deadline deadline = parseDeadlineCommand(trimmedCommand);
            return new AddCommand(deadline);
        case "event":
            // add event task
            Event event = parseEventCommand(trimmedCommand);
            return new AddCommand(event);
        case "find":
            // find tasks
            String keyword = parseFindCommand(trimmedCommand);
            return new FindCommand(keyword);
        default:
            // invalid command
            return new HelpCommand();
        }
    }

    /**
     * Splits the user input to get the description of the todo task.
     *
     * @param command the user input
     * @return the todo task
     * @throws SchmidtException if the command is invalid
     */
    public static Todo parseTodoCommand(String command) throws SchmidtException {
        try {
            // split by the first whitespace to get the description
            String[] inputTokens = command.split("\\s+", 2);

            String description = inputTokens[1];
            return new Todo(description);
        } catch (Exception e) {
            throw new SchmidtException("Please follow the todo command format\n" +
                    "\ttodo <description>");
        }
    }

    /**
     * Splits the user input to get the description and by of the deadline task.
     *
     * @param command the user input
     * @return the deadline task
     * @throws SchmidtException if the command is invalid
     */
    public static Deadline parseDeadlineCommand(String command) throws SchmidtException {
        try {
            // parse the input to get the description and by
            String[] commandTokens = command.split("\\s+/by\\s+");

            String description = commandTokens[0].split("\\s+", 2)[1];
            String by = commandTokens[1];

            return new Deadline(description, by);
        } catch (Exception e) {
            throw new SchmidtException("Please follow the deadline command format\n" +
                    "\tdeadline <description> /by <date>");
        }
    }

    /**
     * Splits the user input to get the description, from, and to of the event task.
     *
     * @param command the user input
     * @return the event task
     * @throws SchmidtException if the command is invalid
     */
    public static Event parseEventCommand(String command) throws SchmidtException {
        try {
            // split by "/from" to get the description and time details
            String[] commandSplitByFrom = command.split("\\s+/from\\s+");

            // split by "/to" to get the from and to
            String[] commandSplitByTo = commandSplitByFrom[1].split("\\s+/to\\s+");

            String description = commandSplitByFrom[0].split("\\s+", 2)[1];
            String from = commandSplitByTo[0].trim();
            String to = commandSplitByTo[1].trim();

            return new Event(description, from, to);
        } catch (Exception e) {
            throw new SchmidtException("Please follow the event command format\n" +
                    "\tevent <description> /from <start> /to <end>");
        }
    }

    /**
     * Splits the user input to get the index of the inputted command.
     *
     * @param command the user input
     * @return the index of the command
     * @throws SchmidtException if the command is invalid
     */
    public static int parseIndexCommand(String command) throws SchmidtException {
        try {
            // split by the first whitespace to get the index
            String[] inputTokens = command.split("\\s+", 2);

            if (inputTokens.length != 2) {
                throw new SchmidtException("Invalid command format");
            }

            return Integer.parseInt(inputTokens[1]) - 1;
        } catch (Exception e) {
            throw new SchmidtException("Please follow the command format\n" +
                    "\t<task number>");
        }
    }

    /**
     * Splits the user input to get the keyword to be found.
     *
     * @param command the user input
     * @return the keyword to be found
     * @throws SchmidtException if the command is invalid
     */
    public static String parseFindCommand(String command) throws SchmidtException {
        try {
            // split by the first whitespace to get the keyword
            String[] inputTokens = command.split("\\s+", 2);

            if (inputTokens.length < 2) {
                throw new SchmidtException("Please follow the find command format\n" +
                        "\tfind <keyword>");
            }

            return inputTokens[1];
        } catch (Exception e) {
            throw new SchmidtException("Please follow the find command format\n" +
                    "\tfind <keyword>");
        }
    }
}
