package anonbot.misc;

import anonbot.Ui;
import anonbot.data.AnonBotFileWriter;
import anonbot.exception.EmptyArgumentException;
import anonbot.exception.InvalidArgumentException;
import anonbot.task.Task.TaskType;
import anonbot.task.TaskManager;
import anonbot.exception.InvalidCommandException;

public class CommandManager {
    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, an error will be thrown.
     * See `Misc.Command` for the list of Possible command types.
     */
    public static Status processCommand(String userInput) throws InvalidCommandException {
        Status executionStatus = Status.STATUS_OK;

        if (userInput.isEmpty()) {
            throw new InvalidCommandException("");
        }

        String commandString = Parser.getCommand(userInput);
        Command.CommandType command = Command.getCommandTypeFromString(commandString);
        String rawArgument = Parser.getCommandArgument(userInput);

        try {
            switch (command) {
            case EXIT:
                // fallthrough
            case BYE:
                AnonBotFileWriter.saveAnonBotData();
                Ui.printGoodbye();
                executionStatus = Status.STATUS_EXIT;
                break;
            case LIST:
                TaskManager.printTaskList();
                break;
            case MARK:
                processMarkCommand(rawArgument);
                break;
            case UNMARK:
                processUnmarkCommand(rawArgument);
                break;
            case TODO:
                TaskManager.createNewTask(rawArgument, TaskType.TODO);
                break;
            case DEADLINE:
                TaskManager.createNewTask(rawArgument, TaskType.DEADLINE);
                break;
            case EVENT:
                TaskManager.createNewTask(rawArgument, TaskType.EVENT);
                break;
            case DELETE:
                processDeleteCommand(rawArgument);
                break;
            case FIND:
                Finder.findTaskUsingKeyphrase(commandString, rawArgument);
                break;
            default: // Invalid Command
                throw new InvalidCommandException(commandString);
            }
        } catch (InvalidArgumentException e) {
            e.printErrorMessage();
        }
        return executionStatus;
    }

    private static void processMarkCommand(String rawArgument) throws InvalidArgumentException {
        if (rawArgument.isEmpty()) {
            throw new EmptyArgumentException("mark");
        }

        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsDone(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("mark", rawArgument);
        }
    }

    private static void processUnmarkCommand(String rawArgument) throws InvalidArgumentException {
        if (rawArgument.isEmpty()){
            throw new EmptyArgumentException("unmark");
        }

        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsUndone(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("unmark", rawArgument);
        }
    }

    private static void processDeleteCommand(String rawArgument) throws InvalidArgumentException {
        if (rawArgument.isEmpty()) {
            throw new EmptyArgumentException("delete");
        }

        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.deleteTask(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("delete", rawArgument);
        }
    }
}
