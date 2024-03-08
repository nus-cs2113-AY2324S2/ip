package schmidt.parser;

import schmidt.command.*;
import schmidt.exception.SchmidtException;
import schmidt.task.Deadline;
import schmidt.task.Event;
import schmidt.task.Todo;

public class Parser {
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
            int markIndex = parseMarkCommand(trimmedCommand);
            return new MarkCommand(markIndex, true);
        case "unmark":
            // mark task as undone
            int unmarkIndex = parseMarkCommand(trimmedCommand);
            return new MarkCommand(unmarkIndex, false);
        case "bye":
            // exit program
            return new ExitCommand();
        case "help":
            // display help message
            return new HelpCommand();
        case "delete":
            // delete task
            int index = parseDeleteCommand(trimmedCommand);
            return new DeleteCommand(index);
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

    public static int parseDeleteCommand(String command) throws SchmidtException {
        try {
            // split by the first whitespace to get the index
            String[] inputTokens = command.split("\\s+", 2);

            if (inputTokens.length < 2) {
                throw new SchmidtException("Please follow the delete command format\n" +
                        "\tdelete <task number>");
            }

            return Integer.parseInt(inputTokens[1]) - 1;
        } catch (Exception e) {
            throw new SchmidtException("Please follow the delete command format\n" +
                    "\tdelete <task number>");
        }
    }

    public static int parseMarkCommand(String command) throws SchmidtException {
        try {
            // split by the first whitespace to get the index
            String[] inputTokens = command.split("\\s+", 2);

            if (inputTokens.length < 2) {
                throw new SchmidtException("Please follow the mark/unmark command format\n" +
                        "\t[un]mark <task number>");
            }

            return Integer.parseInt(inputTokens[1]) - 1;
        } catch (Exception e) {
            throw new SchmidtException("Please follow the mark/unmark command format\n" +
                    "\t[un]mark <task number>");
        }
    }

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
