package misty.command;

import misty.data.TaskList;
import misty.storage.Storage;
import misty.ui.UserUi;
import misty.data.exception.EmptyParameterException;

public class TodoCommand extends Command {
    public static final String COMMAND_STRING = "todo";
    protected String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addTodo(taskName);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}
