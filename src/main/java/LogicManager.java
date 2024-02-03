public class LogicManager {
    private final ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private Command getInvalidCommand() {
        return new Command(CommandType.INVALID_COMMAND);
    }

    private Command getListCommand(String[] words) {
        boolean hasUnnecessaryArguments = words.length > 1;
        if (hasUnnecessaryArguments) {
            return getInvalidCommand();
        }
        return new Command(CommandType.LIST);
    }

    private boolean canStringBeParsedAsInt(String taskIndexString) {
        try {
            Integer.parseInt(taskIndexString);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    private boolean isValidArgument(String taskIntegerString) {
        if (!canStringBeParsedAsInt(taskIntegerString)) {
            return false;
        }
        int taskIndex = Integer.parseInt(taskIntegerString);
        return this.listKeeper.isValidTaskIndex(taskIndex);
    }
    private Command getMarkUnmarkCommand(String[] words) {
        if (words.length != 2) {
            return getInvalidCommand();
        }
        String taskIndexString = words[1];
        if (!isValidArgument(taskIndexString)) {
            return getInvalidCommand();
        }

        int taskIndex = Integer.parseInt(taskIndexString);
        MarkUnmarkArguments markUnmarkArguments = new MarkUnmarkArguments(taskIndex);
        if (words[0].equals("mark")) {
            return new Command(CommandType.MARK, markUnmarkArguments);
        }
        return new Command(CommandType.UNMARK, markUnmarkArguments);
    }

    private Command getToDoCommand(String currentInput) {
        // Extract after _todo_, which is 4 characters
        String taskName = currentInput.substring(4);
        taskName = taskName.trim();
        if (taskName.isEmpty()) {
            return getInvalidCommand();
        }
        return new Command(CommandType.TODO, new ToDoArguments(taskName));
    }
    private Command getDeadlineCommand(String currentInput) {
        try {
            int idxOfDeadline = currentInput.indexOf("/");

            // Extract after _deadline_, which is 8 characters long
            String taskName = currentInput.substring(8, idxOfDeadline);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                return getInvalidCommand();
            }

            // We extract the deadline after _/by _, which is 4 characters long
            String by = currentInput.substring(idxOfDeadline + 4);
            by = by.trim();

            DeadLineArguments deadLineArguments = new DeadLineArguments(taskName, by);
            return new Command(CommandType.DEADLINE, deadLineArguments);
        } catch (Exception exception) {
            return getInvalidCommand();
        }
    }
    private Command getEventCommand(String currentInput) {
        try {
            int idxOfStart = currentInput.indexOf("/");
            // Extract after _event_, which is 5 characters long
            String taskName = currentInput.substring(5, idxOfStart);
            taskName = taskName.trim();
            if (taskName.isEmpty()) {
                return getInvalidCommand();
            }

            int idxOfEnd = currentInput.indexOf("/", idxOfStart + 1);
            // Extract start after _/from_, which is 5 characters long, and before idxOfEnd
            String start = currentInput.substring(idxOfStart + 5, idxOfEnd);
            start = start.trim();

            // Extract end after _/to_, which is 3 characters long
            String end = currentInput.substring(idxOfEnd + 3);
            end = end.trim();

            EventArguments eventArguments = new EventArguments(taskName, start, end);
            return new Command(CommandType.EVENT, eventArguments);
        } catch (Exception exception) {
            return getInvalidCommand();
        }
    }

    private Command getCommand(String currentInput) {
        String[] words = currentInput.split(" ");
        String commandType = words[0];
        if (commandType.equals("list")) {
            return getListCommand(words);
        }
        if (commandType.equals("mark") || commandType.equals("unmark")) {
            return getMarkUnmarkCommand(words);
        }
        if (commandType.equals("todo")) {
            return getToDoCommand(currentInput);
        }
        if (commandType.equals("deadline")) {
            return getDeadlineCommand(currentInput);
        }
        if (commandType.equals("event")) {
            return getEventCommand(currentInput);
        }
        return getInvalidCommand();
    }

    private void printErrorMessage() {
        System.out.println("I'm very confused~~~~");
    }

    public void processCommand(String currentInput) {
        Command command = getCommand(currentInput);
        switch (command.commandType) {
        case LIST:
            listKeeper.printList();
            break;

        case MARK:
        case UNMARK:
            MarkUnmarkArguments markUnmarkArguments = (MarkUnmarkArguments)(command.commandArguments);
            int taskIndex = markUnmarkArguments.index;
            if (command.commandType == CommandType.MARK) {
                listKeeper.processMark(taskIndex);
            } else {
                listKeeper.processUnmark(taskIndex);
            }
            break;

        case TODO:
            ToDoArguments toDoArguments = (ToDoArguments)(command.commandArguments);
            listKeeper.addToList(new ToDo(toDoArguments));
            break;

        case DEADLINE:
            DeadLineArguments deadLineArguments = (DeadLineArguments)(command.commandArguments);
            listKeeper.addToList(new Deadline(deadLineArguments));
            break;

        case EVENT:
            EventArguments eventArguments = (EventArguments)(command.commandArguments);
            listKeeper.addToList(new Event(eventArguments));
            break;

        case INVALID_COMMAND:
        default:
            printErrorMessage();
            break;
        }
    }
}
