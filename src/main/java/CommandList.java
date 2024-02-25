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
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsDone();
            Formatter.printMarkDoneNotif(userSelectedIndex - 1);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
    }

    public static void executeUnmark(CommandParser userCommandReader) {
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsNotDone();
            Formatter.printMarkUndoneNotif(userSelectedIndex - 1);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
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
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            Task removedTask = CommandExecutor.tasks.remove(userSelectedIndex - 1);
            Formatter.printDeleteNotif(removedTask);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
    }

    //insert new command here
}

