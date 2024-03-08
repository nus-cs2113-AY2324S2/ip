package anonbot.misc;

/**
 * Command class related to the handling of supported commands.
 */
public final class Command {
    /**
     * List of possible commands supported by this program (except UNKNOWN).
     * CommandType has the exact same name as the string counterpart.
     * For argument syntax of certain commands, see `getAssociatedCommandArgumentSyntax` function.
     * Possible Commands:
     * 1. `bye`, `exit` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     * 3. `mark` - Marks specific task as done.
     * 4. `unmark` - Marks specific task as undone.
     * 5. `todo` - Creates a new todo task.
     * 6. `deadline` - Creates a new deadline task.
     * 7. `event` - Creates a new event task.
     * 8. `delete` - Deletes the specific task.
     * 9. `find` - Finds Keyword or Keyphrase to search the task list.
     * 10. `help` - Shows the list of supported commands.
      */
    public enum CommandType {
        BYE, EXIT,
        LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        FIND,
        HELP,
        UNKNOWN // Catch-all Type, Not a valid command
    }

    /**
     * Describes the status of the program after a command has been run.
     * Used to indicate whether the program should continue to run or terminate gracefully.
     */
    public enum CommandStatus {
        STATUS_OK, STATUS_EXIT
    }

    /**
     * Gets the corresponding command in string form based on the command type.
     *
     * @param commandType The command type.
     * @return The command of type String.
     */
    private static String getCommandStringFromCommandType(CommandType commandType) {
        return commandType.name().toLowerCase();
    }

    /**
     * Gets the corresponding command typed based on the command string.
     *
     * @param commandString The command in string format.
     * @return The associated command type.
     */
    public static CommandType getCommandTypeFromCommandString(String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Prints a Comma-separated list of supported commands.
     */
    public static void printListOfAvailableCommand() {
        for (CommandType commandEnum : CommandType.values()) {
            if (commandEnum != CommandType.UNKNOWN) {
                System.out.print(getCommandStringFromCommandType(commandEnum) + ", ");
            }
        }
    }

    /**
     * Generates the corresponding command syntax for the supported command.
     *
     * @param command The command to generate the syntax for.
     * @return The full syntax of the command, including the argument(s).
     */
    public static String getAssociatedCommandArgumentSyntax(String command) {
        Command.CommandType commandType = Command.getCommandTypeFromCommandString(command);
        switch (commandType) {
        case MARK:
            return "mark <task_number>";
        case UNMARK:
            return "unmark <task_number>";
        case TODO:
            return "todo <description>";
        case DEADLINE:
            return "deadline <description> [/by <end_time>]";
        case EVENT:
            return "event <description> [/from <start_time> [/to <end_time>]]";
        case DELETE:
            return "delete <task_number>";
        case FIND:
            return "find <keyphrase>";
        default:
            return command;
        }
    }
}
