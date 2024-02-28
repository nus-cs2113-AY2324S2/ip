package misty.command;

import misty.data.TaskList;
import misty.data.exception.EmptyParameterException;
import misty.storage.Storage;
import misty.ui.UserUi;

/**
 * Adds a todo task in task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_STRING = "todo";
    private String taskName;

    /**
     * Constructs TodoCommand object.
     *
     * @param taskName Name of todo task.
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes todo command to add a task to task list.
     *
     * @param taskList ArrayList containing all tasks.
     * @param storage Storage object used to save data to hard disk.
     * @param userUi UserUi object used to interact with user.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addTodo(taskName);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}