public enum CommandList {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    private static final String MAX_DIGIT_LENGTH_3_REGEX = "^[0-9]{1,3}$";
    private static final String UNRESTRICTED_CHAR_LENGTH_REGEX = ".+";
    private static final String[][] lutRegexSequence = {
            {},
            {},
            {MAX_DIGIT_LENGTH_3_REGEX},
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
        if (CommandExecutor.tasks.isEmpty()) {
            Formatter.printListEmpty();
        } else {
            Formatter.printListAll();
        }
    }

    public static void executeMark(CommandParser userCommandReader) {
        Formatter.printMarkDoneNotif(userCommandReader);
    }

    public static void executeUnmark(CommandParser userCommandReader) {
        Formatter.printMarkUndoneNotif(userCommandReader);
    }

    public static void executeDelete(CommandParser userCommandReader) {
        int index = Integer.parseInt(userCommandReader.getArgumentTokens()[0]) - 1;
        Task removedTask = CommandExecutor.tasks.remove(index);
        Formatter.printDeleteNotif(removedTask);

    }

    public static void executeTodo(CommandParser userCommandReader) {
        Todo newTodo = new Todo(userCommandReader.getArgumentTokens()[0]);
        CommandExecutor.tasks.add(newTodo);
        Formatter.printTaskNotif(newTodo);
    }

    public static void executeDeadline(CommandParser userCommandReader) {
        Deadline newDeadline = new Deadline(userCommandReader.getArgumentTokens()[0], userCommandReader.getArgumentTokens()[1]);
        CommandExecutor.tasks.add(newDeadline);
        Formatter.printTaskNotif(newDeadline);
    }

    public static void executeEvent(CommandParser userCommandReader) {
        String description = userCommandReader.getArgumentTokens()[0];
        String startTime = userCommandReader.getArgumentTokens()[1];
        String endTime = userCommandReader.getArgumentTokens()[2];

        Event newEvent = new Event(description, startTime, endTime);
        CommandExecutor.tasks.add(newEvent);
        Formatter.printTaskNotif(newEvent);
    }
}

