package anonbot.misc;

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
     * 9. `find` - Finds Keywords or Keyphrases to search the task list.
     * 10. `help` - Shows the list of supported command.
 what about the invalid command?
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

    private static String getCommandStringFromCommandType(CommandType commandType) {
        return commandType.name().toLowerCase();
    }

    public static CommandType getCommandTypeFromString (String commandString) {
        try {
            return CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    public static void printListOfAvailableCommand() {
        for (CommandType commandEnum : CommandType.values()) {
            if (commandEnum != CommandType.UNKNOWN) {
                System.out.print(getCommandStringFromCommandType(commandEnum) + ", ");
            }
        }
    }

    public static String getAssociatedCommandArgumentSyntax(String command) {
        Command.CommandType commandType = Command.getCommandTypeFromString(command);
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
