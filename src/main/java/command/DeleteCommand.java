package command;

import exception.AdamException;
import task.TaskList;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(String[] inputArguments) {
        this.index = Integer.parseInt(inputArguments[0]);
    }

    @Override
    public boolean execute(TaskList tasks) throws AdamException {
        tasks.deleteTask(index);
        return false;
    }
}
