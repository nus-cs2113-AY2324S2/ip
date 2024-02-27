package misty.command;

import misty.List;
import misty.Storage;
import misty.UserUi;
import misty.exception.EmptyParameterException;

public class TodoCommand extends Command {
    public static final String COMMAND_STRING = "todo";
    protected String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(List taskList, Storage storage, UserUi userUi) {
        try {
            taskList.addTodo(taskName);
        } catch (EmptyParameterException e) {
            userUi.printErrorEmptyParameter();
        }
    };
}
