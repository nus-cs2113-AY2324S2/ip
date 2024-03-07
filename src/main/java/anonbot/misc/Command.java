package anonbot.misc;

public final class Command {
    /**
     * List of possible commands supported by this program.
     * CommandType has the exact same name as the string counterpart.
     * Possible Commands:
     * 1. `bye`, `exit` - Exits the program.
     * 2. `list` - Lists out all the tasks.
     * 3. `mark <task_number> - Marks specific task as done.
     * 4. `unmark` <task_number> - Marks specific task as undone.
     * 5. `todo` <description> - Creates a new todo task.
     * 6. `deadline` <description> /by <end_time> - Creates a new deadline task.
     * 7. `event` <description> /from <start_time> /to <end_time> - Creates a new event task.
     * 8. `delete` <task_number> - Deletes the specific task.
     * 9. TO BE UPDATED
      */
    public enum CommandType {
        BYE, EXIT,
        LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        FIND,
        UNKNOWN
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
}
