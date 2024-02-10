public enum CommandList {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT;
    private static final String[][] lutRegexSequence = {
            {},
            {},
            {"^[0-9]+$"},
            {"^[0-9]+$"},
            {".+"},
            {".+", ".+"},
            {".+", ".+", ".+"}
    };

    public static int getArgumentCount(String commandName) {
        return lutRegexSequence[CommandList.valueOf(commandName).ordinal()].length;
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
        if (readUserCommand.getIsGoodTokens()) {
            Formatter.printMarkDoneNotif(readUserCommand);
        } else {
            Formatter.printErrorBadTokens();
        }
    }

    public static void executeUnmark(CommandParser readUserCommand) {
        if (readUserCommand.getIsGoodTokens()) {
            Formatter.printMarkUndoneNotif(readUserCommand);
        } else {
            Formatter.printErrorBadTokens();
        }

    }

    public static void executeTodo(CommandParser readUserCommand) {
        if (readUserCommand.getIsGoodTokens()) {
            Todo newTodo = new Todo(readUserCommand.getArgumentTokens()[0]);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newTodo;
            Formatter.printTodoNotif(readUserCommand);
        } else {
            Formatter.printErrorBadTokens();
        }
    }

    public static void executeDeadline(CommandParser readUserCommand) {
        if (readUserCommand.getIsGoodTokens()) {
            Deadline newDeadline = new Deadline(readUserCommand.getArgumentTokens()[0], readUserCommand.getArgumentTokens()[1]);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newDeadline;
            Formatter.printDeadlineNotif(readUserCommand);
        } else {
            Formatter.printErrorBadTokens();
        }
    }

    public static void executeEvent(CommandParser readUserCommand) {
        if (readUserCommand.getIsGoodTokens()) {
            String description = readUserCommand.getArgumentTokens()[0];
            String startTime = readUserCommand.getArgumentTokens()[1];
            String endTime = readUserCommand.getArgumentTokens()[2];

            Event newEvent = new Event(description, startTime, endTime);
            CommandExecutor.tasks[CommandExecutor.listCount++] = newEvent;
            Formatter.printEventNotif(readUserCommand);
        } else {
            Formatter.printErrorBadTokens();
        }

    }
}

