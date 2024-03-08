package anonbot.misc;

import anonbot.Ui;
import anonbot.data.AnonBotFileWriter;
import anonbot.exception.EmptyArgumentException;
import anonbot.exception.InvalidArgumentException;
import anonbot.exception.InvalidCommandException;
import anonbot.misc.Command.CommandStatus;
import anonbot.misc.Command.CommandType;
import anonbot.task.Task.TaskType;
import anonbot.task.TaskManager;

/**
 * A class that handles the logical flow of the user's input
 */
public class CommandManager {
    /**
     * Takes user inputs as commands and process them.
     * If the input is not one of the supported commands below, an error will be thrown.
     * See `misc.Command` for the list of Possible command types.
     *
     * @param userInput The raw user input, containing the command and argument (if applicable)
     * @return The execution status to indicate whether to continue running the program.
     * @throws InvalidCommandException If an unsupported command is supplied.
     */
    public static CommandStatus processCommand(String userInput) throws InvalidCommandException {
        CommandStatus executionStatus = CommandStatus.STATUS_OK;

        if (userInput.isEmpty()) {
            throw new InvalidCommandException("");
        }

        String commandString = Parser.getCommand(userInput);
        CommandType command = Command.getCommandTypeFromCommandString(commandString);
        String rawArgument = Parser.getCommandArgument(userInput);

        try {
            switch (command) {
            case EXIT:
                // fallthrough
            case BYE:
                AnonBotFileWriter.saveAnonBotData();
                Ui.printGoodbye();
                executionStatus = CommandStatus.STATUS_EXIT;
                break;
            case LIST:
                TaskManager.printTaskList();
                break;
            case MARK:
                processMarkCommand(rawArgument);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case UNMARK:
                processUnmarkCommand(rawArgument);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case TODO:
                TaskManager.createNewTask(rawArgument, TaskType.TODO);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case DEADLINE:
                TaskManager.createNewTask(rawArgument, TaskType.DEADLINE);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case EVENT:
                TaskManager.createNewTask(rawArgument, TaskType.EVENT);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case DELETE:
                processDeleteCommand(rawArgument);
                AnonBotFileWriter.saveAnonBotData();
                break;
            case FIND:
                Finder.findTaskUsingKeyphrase(commandString, rawArgument);
                break;
            case HELP:
                System.out.println("List of Supported commands:");
                Command.printListOfAvailableCommand();
                System.out.println();
                break;
            default: // Invalid Command
                throw new InvalidCommandException(commandString);
            }
        } catch (InvalidArgumentException e) {
            e.printErrorMessage();
        }
        return executionStatus;
    }

    /**
     * Processes the mark command by obtaining the task number to mark the corresponding task as done.
     *
     * @param rawArgument A valid task number.
     * @throws InvalidArgumentException If there is no task number provided, or if the task number is not
     *         numeric, or if there is no task with that particular task number.
     */
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

    /**
     * Processes the unmark command by obtaining the task number to mark the corresponding task as undone.
     *
     * @param rawArgument A valid task number.
     * @throws InvalidArgumentException If there is no task number provided, or if the task number is not
     *         numeric, or if there is no task with that particular task number.
     */
    private static void processUnmarkCommand(String rawArgument) throws InvalidArgumentException {
        if (rawArgument.isEmpty()) {
            throw new EmptyArgumentException("unmark");
        }

        try {
            int taskNumber = Parser.getTaskNumberFromString(rawArgument);
            TaskManager.markTaskAsUndone(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("unmark", rawArgument);
        }
    }

    /**
     * Processes the delete command by obtaining the task number to delete the corresponding task.
     *
     * @param rawArgument A valid task number.
     * @throws InvalidArgumentException If there is no task number provided, or if the task number is not
     *         numeric, or if there is no task with that particular task number.
     */
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
