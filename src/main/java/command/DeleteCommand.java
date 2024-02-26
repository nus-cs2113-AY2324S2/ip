package command;

import errorhandle.UserInputErrorOutputHandler;
import format.Formatter;
import storage.Storage;
import task.Task;
import task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command{
    public int taskIndex;
    Formatter formatter;
    public static final String identity = "delete";
    UserInputErrorOutputHandler userInputError;
    Storage storage;

    public DeleteCommand(String userCommandText, TaskList taskList) {
        formatter = new Formatter();
        userInputError = new UserInputErrorOutputHandler();
        storage = new Storage();

        int spaceIndex = userCommandText.indexOf(" ");
        if (spaceIndex == -1) {
            userInputError.printNoSpacingError("'" + identity + "'");
            setIfNoError(false);
        } else {
            prepareDeleteCommand(userCommandText.substring(spaceIndex + 1), taskList);
        }
    }
    @Override
    public void execute(TaskList taskList) {
        formatter.printDividingLine();
        Task currentTask = taskList.getTask(taskIndex - 1);
        System.out.println("\tNoted. I've removed this task:");
        try {
            storage.deleteTask(currentTask);
        } catch (IOException e) {
            System.out.println("Can not delete your Task!!!\n" + e.getMessage());
        }
        String output = "\t\t" + currentTask.getIdentity() + currentTask.getStatusIcon() + " " + currentTask;
        System.out.println(output);
        taskList.removeTask(taskIndex - 1);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
        formatter.printDividingLine();
    }
    public void prepareDeleteCommand(String unpreparedDeleteCommand, TaskList taskList){
        try {
            Integer.parseInt(unpreparedDeleteCommand);
        } catch (NumberFormatException e) {
            userInputError.printInputNotNumberError("'" + identity + "'");
            setIfNoError(false);
            return;
        }

        taskIndex = Integer.parseInt(unpreparedDeleteCommand);
        if (taskIndex > taskList.getSize()) {
            userInputError.printRequestTaskOutOfBoundError();
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
    }
}
