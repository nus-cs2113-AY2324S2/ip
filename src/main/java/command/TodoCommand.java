package command;

import errorhandle.UserInputErrorOutputHandler;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import java.io.IOException;

/**
 * Represent a todoCommand
 */
public class TodoCommand extends Command {
    public static final String identity = "todo";
    Storage storage;
    protected UserInputErrorOutputHandler userInputError;
    protected Ui ui;

    public TodoCommand(String userCommandText) {
        storage = new Storage();
        ui = new Ui();
        userInputError = new UserInputErrorOutputHandler();
        int spaceIndex = userCommandText.indexOf(" ");
        prepareTodoCommand(userCommandText.substring(spaceIndex + 1));
    }

    /**
     * Execute todoCommand, add new todoTask to the taskList and save that task on the local disk
     *
     * @param taskList Instance of Class <code>TaskList</code>
     */
    @Override
    public void execute(TaskList taskList) {
        Task t = new ToDo(content.trim());
        taskList.addTask(t);
        ui.printNewTaskAddedMessage(t, taskList);
        try {
            storage.saveTask(taskList.getTask(taskList.getSize() - 1));
        } catch (IOException e) {
            System.out.println("Can not save your Task!!!\n" + e.getMessage());
        }
    }

    /**
     * Make todoCommand ready to execute
     *
     * @param unpreparedUserCommand user command that may have input error
     */
    public void prepareTodoCommand(String unpreparedUserCommand) {
        if (unpreparedUserCommand.toLowerCase().startsWith(identity)) {
            userInputError.printNoTaskContentError(identity);
            setIfNoError(false);
            return;
        }
        setIfNoError(true);
        content = unpreparedUserCommand;
    }
}
