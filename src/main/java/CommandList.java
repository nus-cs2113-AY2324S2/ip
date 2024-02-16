public enum CommandList {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT;
    private static final String MAX_DIGIT_LENGTH_3_REGEX = "^[0-9]{1,3}$";
    private static final String UNRESTRICTED_CHAR_LENGTH_REGEX = ".+";
    private static final String[][] lutRegexSequence = {
            {},
            {},
            {MAX_DIGIT_LENGTH_3_REGEX},
            {MAX_DIGIT_LENGTH_3_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX},
            {UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX, UNRESTRICTED_CHAR_LENGTH_REGEX}
    };

    public static int getArgumentCount(String commandName) {
        return lutRegexSequence[CommandList.valueOf(commandName).ordinal()].length;
    }
    public static int getMaxArgumentCount() {
        int maxSoFar = 0;
        for (int i = 0; i < lutRegexSequence.length; i++) {
            if (lutRegexSequence[i].length > maxSoFar) {
                maxSoFar = lutRegexSequence[i].length;
            }
        }
        return maxSoFar;
    }

    public static String[] getRegexSequence(String commandName) {
        return lutRegexSequence[CommandList.valueOf(commandName).ordinal()];
    }
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        CommandExecutor.isRunning = false;
    }

    public static void executeList() {
        if (CommandExecutor.listCount == 0) {
            Formatter.printListEmpty();
        } else {
            Formatter.printListAll();
        }
    }

    public static void executeMark(CommandParser readUserCommand) {
            Formatter.printMarkDoneNotif(readUserCommand);
    }

    public static void executeUnmark(CommandParser readUserCommand) {
            Formatter.printMarkUndoneNotif(readUserCommand);
    }

    public static void executeTodo(CommandParser readUserCommand) {
            Todo newTodo = new Todo(readUserCommand.getArgumentTokens()[0]);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newTodo;
            Formatter.printTaskNotif(readUserCommand);
    }

    public static void executeDeadline(CommandParser readUserCommand) {
            Deadline newDeadline = new Deadline(readUserCommand.getArgumentTokens()[0], readUserCommand.getArgumentTokens()[1]);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newDeadline;
            Formatter.printTaskNotif(readUserCommand);
    }

    public static void executeEvent(CommandParser readUserCommand) {
            String description = readUserCommand.getArgumentTokens()[0];
            String startTime = readUserCommand.getArgumentTokens()[1];
            String endTime = readUserCommand.getArgumentTokens()[2];

            Event newEvent = new Event(description, startTime, endTime);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newEvent;
            Formatter.printTaskNotif(readUserCommand);
    }
}

