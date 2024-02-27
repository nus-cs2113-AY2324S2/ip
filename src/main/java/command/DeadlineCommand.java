package command;

import errorhandle.UserInputErrorOutputHandler;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represent a deadlineCommand
 */
public class DeadlineCommand extends Command {
    public final String identity = "deadline";
    Storage storage;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;
    protected String by;

    public DeadlineCommand(String userCommandText) {
        storage = new Storage();
        userInputError = new UserInputErrorOutputHandler();
        ui = new Ui();
        int spaceIndex = userCommandText.indexOf(" ");
        prepareDeadlineCommand(userCommandText.substring(spaceIndex + 1));
    }

    /**
     * Execute deadlineCommand, add new deadlineTask to the taskList and save that task on the local disk
     *
     * @param taskList Instance of Class <code>TaskList</code>
     */
    @Override
    public void execute(TaskList taskList) {
        Task t = new Deadline(content.trim(), by.trim());
        taskList.addTask(t);
        ui.printNewTaskAddedMessage(t, taskList);
        try {
            storage.saveTask(taskList.getTask(taskList.getSize() - 1));
        } catch (IOException e) {
            System.out.println("Can not save your Task!!!\n" + e.getMessage());
        }
    }

    /**
     * Make deadlineCommand ready to execute
     *
     * @param unpreparedUserCommand user command that may have input error
     */
    public void prepareDeadlineCommand(String unpreparedUserCommand) {
        if (unpreparedUserCommand.toLowerCase().startsWith(identity)) {
            userInputError.printNoTaskContentError(identity);
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
        int byIndex = unpreparedUserCommand.indexOf("/by");
        content = unpreparedUserCommand.substring(0, byIndex);
        by = unpreparedUserCommand.substring(byIndex + 3).trim();
    }
}
