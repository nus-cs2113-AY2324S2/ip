public enum CommandList {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT;
    private static final String[][] lutRegexSequence = {
            {},
            {},
            {"[0-9]+"},
            {"[0-9]+"},
            {".+"},
            {".+",".+"},
            {".+",".+",".+"}
    };
    public static int getArgumentCount(String commandName) {
        return lutRegexSequence[CommandList.valueOf(commandName).ordinal()].length;
    }
    public static String[] getRegexSequence(String commandName) {
        return lutRegexSequence[CommandList.valueOf(commandName).ordinal()];
    }
    public static void executeBye() {
        System.out.println("************************************************************");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("************************************************************");
        Duke.isRunning = false;
    }
    public static void executeList() {
        if (Duke.listCount == 0) {
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < Duke.listCount; i++) {
                System.out.print((i + 1) + ".");
                System.out.println(Duke.tasks[i]);
            }
        }
    }
    public static void executeMark(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex < Duke.tasks.length) {
            Duke.tasks[userSelectedIndex - 1].markAsDone();

            System.out.println("Nice! I've marked this task as done:");
            System.out.print((userSelectedIndex) + ".");
            System.out.printf("[%s] ", Duke.tasks[userSelectedIndex - 1].getStatusIcon());
            System.out.println(Duke.tasks[userSelectedIndex - 1].description);
        }
    }
    public static void executeUnmark(CommandParser readUserCommand) {
        int userSelectedIndex = Integer.parseInt(readUserCommand.getArgumentTokens()[0]);
        if (userSelectedIndex < Duke.tasks.length) {
            Duke.tasks[userSelectedIndex - 1].markAsNotDone();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.print((userSelectedIndex) + ".");
            System.out.printf("[%s] ", Duke.tasks[userSelectedIndex - 1].getStatusIcon());
            System.out.println(Duke.tasks[userSelectedIndex - 1].description);
        }
    }
    public static void executeTodo(CommandParser readUserCommand) {
        Todo newTodo = new Todo(readUserCommand.getArgumentTokens()[0]);
        Duke.tasks[Duke.listCount++] = newTodo;
        System.out.println("added: " + newTodo);
    }
    public static void executeDeadline(CommandParser readUserCommand) {
        Deadline newDeadline = new Deadline(readUserCommand.getArgumentTokens()[0], readUserCommand.getArgumentTokens()[1]);
        Duke.tasks[Duke.listCount++] = newDeadline;
        System.out.println("added: " + newDeadline);
    }
    public static void executeEvent(CommandParser readUserCommand) {
        String description = readUserCommand.getArgumentTokens()[0];
        String startTime = readUserCommand.getArgumentTokens()[1];
        String endTime = readUserCommand.getArgumentTokens()[2];

        Event newEvent = new Event(description, startTime, endTime);
        Duke.tasks[Duke.listCount++] = newEvent;
        System.out.println("added: " + newEvent);
    }
}

