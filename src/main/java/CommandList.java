public enum CommandList {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, //insert new command name here
    ;
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
    public static void executeDelete(CommandParser userCommandReader) {
        int index = Integer.parseInt(userCommandReader.getArgumentTokens()[0]) - 1;
        Task removedTask = CommandExecutor.tasks.remove(index);
        Formatter.printDeleteNotif(removedTask);
    }

    //insert new command here
}

