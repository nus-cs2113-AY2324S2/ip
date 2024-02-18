package command;

import exception.AdamException;
import task.TaskList;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        tasks.deleteTask(index);
        return false;
    }
}
