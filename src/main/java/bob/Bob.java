package bob;

import bob.exceptions.InvalidArgumentException;
import bob.exceptions.InvalidCommandException;
import bob.exceptions.InvalidTaskNumberException;
import bob.utils.Command;
import bob.utils.Parser;
import bob.utils.TaskManager;
import bob.utils.StateManager;
import bob.utils.Ui;

import java.io.IOException;

public class Bob {
    public String executeCommand(Command userCommand, TaskManager manager, Parser inputParser) throws
            InvalidTaskNumberException, InvalidArgumentException {
        String commandOutput;
        String taskName;
        String[] arguments;

        switch (userCommand) {
        case LIST:
            commandOutput = manager.listTasks();
            break;
        case MARK:
        case UNMARK:
            try {
                arguments = inputParser.parseArguments(userCommand);
                int taskId = Integer.parseInt(arguments[0]); // Throws NumberFormatException
                commandOutput = manager.updateTaskProgress(taskId, userCommand); // Throws NPE or ArrayIndexOOB Ex.
            } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException exception) {
                // Catch improperly formatted arguments for mark/unmark operations
                inputParser.clearInput();
                throw new InvalidTaskNumberException(userCommand);
            }
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
            commandOutput = "ERROR";
            break;
        }

        return commandOutput;
    }

    public void run() {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.printWelcome();

        TaskManager manager;
        Parser inputParser = new Parser();

        try {
            manager = StateManager.loadState();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            manager = new TaskManager();
        }

        while (inputParser.hasMoreInput()) {
            try {
                Command userCommand = inputParser.parseCommand();

                if (inputParser.isDone(userCommand)) {
                    // Check if "bye" command was given as input
                    StateManager.saveState(manager);
                    break;
                }

                String executionResult = executeCommand(userCommand, manager, inputParser);
                userInterface.print(executionResult);
            } catch (IOException | InvalidCommandException | InvalidTaskNumberException |
                     InvalidArgumentException exception) {
                userInterface.print(exception.getMessage());
            }
        }

        userInterface.printExit();
    }
}
