public class LogicManager {

    public static final int MAX_NUM_OF_TASKS = 100;
    private ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private Command processListCommand(String[] words) {
        boolean hasUnnecessaryArguments = words.length > 1;
        if (hasUnnecessaryArguments) {
            return new Command(CommandType.INVALID_COMMAND);
        }
        return new Command(CommandType.LIST);
    }

    private boolean isIndexValidInt(String taskIndexString) {
        try {
            Integer.parseInt(taskIndexString);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }
    private Command processMarkUnmarkCommand(String[] words) {
        if (words.length != 2) {
            return new Command(CommandType.INVALID_COMMAND);
        }
        if (!isIndexValidInt(words[1])) {
            return new Command(CommandType.INVALID_INDEX);
        }

        // Valid Command
        int taskIndex = Integer.parseInt(words[1]);
        MarkUnmarkArguments markUnmarkArguments = new MarkUnmarkArguments(taskIndex);
        if (words[0].equals("mark")) {
            return new Command(CommandType.MARK, markUnmarkArguments);
        }
        return new Command(CommandType.UNMARK, markUnmarkArguments);
    }

    private Command processToDo(String currentInput) {
        // Extract after _todo_, which is 4 characters
        String taskName = currentInput.substring(4);
        taskName = taskName.trim();
        return new Command(CommandType.TODO, new ToDoArguments(taskName));
    }
    private Command processDeadline(String currentInput) {
        int idxOfDeadline = currentInput.indexOf("/");

        // Extract after _deadline_, which is 8 characters long
        String taskName = currentInput.substring(8, idxOfDeadline);
        taskName = taskName.trim();

        // We extract the deadline after _/by _, which is 4 characters long
        String by = currentInput.substring(idxOfDeadline + 4);
        by = by.trim();

        DeadLineArguments deadLineArguments = new DeadLineArguments(taskName, by);
        return new Command(CommandType.DEADLINE, deadLineArguments);
    }
    private Command processEvent(String currentInput) {

        int idxOfStart = currentInput.indexOf("/");
        // Extract after _event_, which is 5 characters long
        String taskName = currentInput.substring(5, idxOfStart);
        taskName = taskName.trim();

        int idxOfEnd = currentInput.indexOf("/", idxOfStart + 1);
        // Extract start after _/from_, which is 5 characters long, and before idxOfEnd
        String start = currentInput.substring(idxOfStart + 5, idxOfEnd);
        start = start.trim();

        // Extract end after _/to_, which is 3 characters long
        String end = currentInput.substring(idxOfEnd + 3);
        end = end.trim();

        EventArguments eventArguments = new EventArguments(taskName, start, end);
        return new Command(CommandType.EVENT, eventArguments);
    }

    private Command getCommandType(String currentInput) {
        String[] words = currentInput.split(" ");

        if (words[0].equals("list")) {
            return processListCommand(words);
        }
        if (words[0].equals("mark") || words[0].equals("unmark")) {
            return processMarkUnmarkCommand(words);
        }
        if (words[0].equals("todo")) {
            return processToDo(currentInput);
        }
        if (words[0].equals("deadline")) {
            return processDeadline(currentInput);
        }
        if (words[0].equals("event")) {
            return processEvent(currentInput);
        }
        return new Command(CommandType.INVALID_COMMAND);
    }

    private void printFeedbackForInvalidCommand() {
        System.out.println("Invalid command");
    }
    private void printFeedbackForInvalidTask() {
        System.out.println("Please give a valid task");
    }
    private void printFeedbackForInvalidIndex() {
        System.out.println("Please specify the task you wish to mark/unmark");
    }
    private void printErrorMessage() {
        System.out.println("I'm very confused~~~~");
    }

    public void processCommand(String currentInput) {
        Command command = getCommandType(currentInput);
        switch (command.commandType) {
        case LIST:
            listKeeper.printList();
            break;
        case MARK:
        case UNMARK:
            MarkUnmarkArguments markUnmarkArguments = (MarkUnmarkArguments)(command.commandArguments);
            if (command.commandType == CommandType.MARK) {
                listKeeper.processMark(markUnmarkArguments.index);
            } else {
                listKeeper.processUnmark(markUnmarkArguments.index);
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
        case INVALID_INDEX:
            printFeedbackForInvalidIndex();
            break;
        case INVALID_COMMAND:
            printFeedbackForInvalidCommand();
            break;
        case INVALID_TASK:
            printFeedbackForInvalidTask();
            break;
        default:
            printErrorMessage();
            break;
        }
    }
}
