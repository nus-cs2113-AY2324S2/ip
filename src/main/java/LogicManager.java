public class LogicManager {

    public static final int MAX_NUM_OF_TASKS = 100;
    private ListKeeper listKeeper;

    public LogicManager(ListKeeper listKeeper) {
        this.listKeeper = listKeeper;
    }

    private boolean isIndexValidInt(String taskIndexString) {
        try {
            Integer.parseInt(taskIndexString);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }

    private Command getCommandType(String currentInput) {
        String[] words = currentInput.split(" ");

        if (words[0].equals("list")) {
            boolean hasUnnecessaryArguments = words.length > 1;
            if (hasUnnecessaryArguments) {
                return new Command(CommandType.INVALID_COMMAND);
            }
            return new Command(CommandType.LIST);
        }

        boolean isCommandMark = words[0].equals("mark");
        boolean isCommandUnmark = words[0].equals("unmark");
        if (isCommandMark || isCommandUnmark) {
            if (words.length != 2) {
                return new Command(CommandType.INVALID_COMMAND);
            }
            if (!isIndexValidInt(words[1])) {
                return new Command(CommandType.INVALID_INDEX);
            }

            // Valid Command
            int taskIndex = Integer.parseInt(words[1]);
            if (isCommandMark) {
                return new Command(CommandType.MARK, taskIndex);
            }
            return new Command(CommandType.UNMARK, taskIndex);
        }

        return new Command(CommandType.ADD);
    }

    private void printFeedbackForInvalidCommand() {
        System.out.println("Invalid command");
    }
    private void printFeedbackForInvalidIndex() {
        System.out.println("Please specify the task you wish to mark/unmark");
    }

    public void processCommand(String currentInput) {
        Command command = getCommandType(currentInput);
        switch (command.commandType) {
        case LIST:
            listKeeper.printList();
            break;
        case ADD:
            listKeeper.addToList(currentInput);
            break;
        case MARK:
            listKeeper.processMark(command.inputIndex);
            break;
        case UNMARK:
            listKeeper.processUnmark(command.inputIndex);
            break;
        case INVALID_INDEX:
            printFeedbackForInvalidIndex();
            break;
        case INVALID_COMMAND:
            printFeedbackForInvalidCommand();
            break;
        }
    }
}
