package command;

import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.io.IOException;

/**
 * Represent a markCommand
 */
public class MarkCommand extends Command {
    public int taskIndex;
    Formatter formatter;
    public static final String identity = "mark";
    UserInputErrorOutputHandler userInputError;
    Storage storage;

    public MarkCommand(String userCommandText, TaskList taskList) {
        formatter = new Formatter();
        userInputError = new UserInputErrorOutputHandler();
        storage = new Storage();

        int spaceIndex = userCommandText.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.printNoSpacingError("'" + identity + "'");
            setIfNoError(false);
        } else {
            prepareMarkCommand(userCommandText.substring(spaceIndex + 1), taskList);
        }
    }

    /**
     * Execute markCommand, mark specified task in the taskList and save that changes on the local disk
     *
     * @param taskList Instance of Class <code>TaskList</code>
     */
    @Override
    public void execute(TaskList taskList) {
        formatter.printDividingLine();
        Task currentTask = taskList.getTask(taskIndex - 1);
        System.out.println("\tOK, I've marked this task as done:");
        try {
            storage.markTask(currentTask);
        } catch (IOException e) {
            System.out.println("Can not mark your Task!!!\n" + e.getMessage());
        }
        String output = "\t\t" + currentTask.getIdentity() + currentTask.getStatusIcon() + " " + currentTask;
        System.out.println(output);
        formatter.printDividingLine();
    }

    /**
     * Make markCommand ready to execute
     *
     * @param unpreparedUserCommand user command that may have input error
     * @param taskList              Instance of Class <code>TaskList</code>
     */
    public void prepareMarkCommand(String unpreparedUserCommand, TaskList taskList) {
        try {
            Integer.parseInt(unpreparedUserCommand);
        } catch (NumberFormatException e) {
            userInputError.printInputNotNumberError("'" + identity + "'");
            setIfNoError(false);
            return;
        }

        taskIndex = Integer.parseInt(unpreparedUserCommand);
        if (taskIndex > taskList.getSize()) {
            userInputError.printRequestTaskOutOfBoundError();
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
    }

}
