package command;

import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.io.IOException;

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

    public void prepareMarkCommand(String unpreparedMarkCommand, TaskList taskList) {
        unpreparedMarkCommand = unpreparedMarkCommand.trim();
        try {
            Integer.parseInt(unpreparedMarkCommand);
        } catch (NumberFormatException e) {
            userInputError.printInputNotNumberError("'" + identity + "'");
            setIfNoError(false);
            return;
        }

        taskIndex = Integer.parseInt(unpreparedMarkCommand);
        if (taskIndex > taskList.getSize()) {
            userInputError.printRequestTaskOutOfBoundError();
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
    }

}
