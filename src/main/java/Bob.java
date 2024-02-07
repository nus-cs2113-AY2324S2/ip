public class Bob {
    public String executeCommand(Command userCommand, TaskManager manager, Parser inputParser) {
        String commandOutput;
        String taskName;
        String[] arguments;

        switch (userCommand) {
        case LIST:
            commandOutput = manager.listTasks();
            break;
        case MARK:
        case UNMARK:
            arguments = inputParser.parseArguments(userCommand);
            int taskId = Integer.parseInt(arguments[0]);
            commandOutput = manager.updateTaskProgress(taskId, userCommand);
            break;
        case TODO:
            arguments = inputParser.parseArguments(userCommand);
            taskName = arguments[0];
            commandOutput = manager.addTodo(taskName);
            break;
        case DEADLINE:
            arguments = inputParser.parseArguments(userCommand);
            taskName = arguments[0];
            String dueDate = arguments[1];
            commandOutput = manager.addDeadline(taskName, dueDate);
            break;
        case EVENT:
            arguments = inputParser.parseArguments(userCommand);
            taskName = arguments[0];
            String startDate = arguments[1];
            String endDate = arguments[2];
            commandOutput = manager.addEvent(taskName, startDate, endDate);
            break;
        default:
            inputParser.clearInput();
            commandOutput = "ERROR";
            break;
        }

        return commandOutput;
    }

    public void run() {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.printWelcome();

        TaskManager manager = new TaskManager();
        Parser inputParser = new Parser();

        while (true) {
            Command userCommand = inputParser.parseCommand();

            if (inputParser.isDone(userCommand)) {
                // Check if "bye" command was given as input
                break;
            }

             String executionResult = executeCommand(userCommand, manager, inputParser);
             userInterface.print(executionResult);
        }

        userInterface.printExit();
    }
}
