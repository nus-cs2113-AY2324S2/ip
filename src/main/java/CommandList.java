public enum CommandList {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, //insert new command name here
    ;
    /**
     * Exits the program
     *
     */
    public static void executeBye() {
        Formatter.printGoodbyeMsg();
        CommandExecutor.isRunning = false;
    }
    /**
     * Prints user list
     *
     */
    public static void executeList() {
        if (CommandExecutor.tasks.isEmpty()) {
            Formatter.printListEmpty();
        } else {
            Formatter.printListAll();
        }
    }

    /**
     * Returns the index location of task in list and marks the task in list
     *
     * @param userCommandReader the parsed user input
     * @return index location of task in list
     *
     */
    public static int executeMark(CommandParser userCommandReader) {
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsDone();
            Formatter.printMarkDoneNotif(userSelectedIndex - 1);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
        return userSelectedIndex;
    }

    /**
     * Returns the index location of task in list and unmarks the task in list
     *
     * @param userCommandReader the parsed user input
     * @return index location of task in list
     *
     */
    public static int executeUnmark(CommandParser userCommandReader) {
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            CommandExecutor.tasks.get(userSelectedIndex - 1).markAsNotDone();
            Formatter.printMarkUndoneNotif(userSelectedIndex - 1);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
        return userSelectedIndex;
    }
    /**
     * Returns and adds a Todo task into list
     *
     * @param userCommandReader the parsed user input
     * @return The todo task
     *
     */
    public static Todo executeTodo(CommandParser userCommandReader) {
        Todo newTodo = new Todo(userCommandReader.getArgumentTokens()[0]);
        CommandExecutor.tasks.add(newTodo);
        Formatter.printTaskNotif(newTodo);
        return newTodo;
    }

    /**
     * Returns and adds a Deadline task into list
     *
     * @param userCommandReader the parsed user input
     * @return The deadline task
     *
     */
    public static Deadline executeDeadline(CommandParser userCommandReader) {
        Deadline newDeadline = new Deadline(userCommandReader.getArgumentTokens()[0], userCommandReader.getArgumentTokens()[1]);
        CommandExecutor.tasks.add(newDeadline);
        Formatter.printTaskNotif(newDeadline);
        return newDeadline;
    }
    /**
     * Returns and adds a Event task into list
     *
     * @param userCommandReader the parsed user input
     * @return The event task
     *
     */
    public static Event executeEvent(CommandParser userCommandReader) {
        String description = userCommandReader.getArgumentTokens()[0];
        String startTime = userCommandReader.getArgumentTokens()[1];
        String endTime = userCommandReader.getArgumentTokens()[2];

        Event newEvent = new Event(description, startTime, endTime);
        CommandExecutor.tasks.add(newEvent);
        Formatter.printTaskNotif(newEvent);
        return newEvent;
    }
    /**
     * Returns the index of deleted task and deletes task from list
     *
     * @param userCommandReader the parsed user input
     * @return The index of deleted task
     *
     */
    public static int executeDelete(CommandParser userCommandReader) {
        int userSelectedIndex = Integer.parseInt(userCommandReader.getArgumentTokens()[0]);
        if (userSelectedIndex <= CommandExecutor.tasks.size() && userSelectedIndex > 0) {
            Task removedTask = CommandExecutor.tasks.remove(userSelectedIndex - 1);
            Formatter.printDeleteNotif(removedTask);
        } else {
            Formatter.printErrorIndexOutOfRange();
        }
        return userSelectedIndex;
    }

    //insert new command here
}

