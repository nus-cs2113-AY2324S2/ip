package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

public class DeleteCommand implements Command {
    private static final CommandType taskType = CommandType.DELETE;
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void run(TaskList tasks) throws InputException {
        String response = tasks.getPosAt(taskIndex).toString();
        tasks.deleteTaskAt(taskIndex);
        response = response + "\n" + tasks;
        ResponseManager.printActionOnTasks(taskType.getType(), response);
    }

    public boolean isExit() {
        return false;
    }
}
