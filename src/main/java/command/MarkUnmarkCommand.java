package command;

import exception.InputException;
import task.TaskList;
import tool.ResponseManager;

public class MarkUnmarkCommand implements Command {
    private final CommandType taskType;
    private final int taskIndex;

    public MarkUnmarkCommand(CommandType taskType, int taskIndex) {
        this.taskType = taskType;
        this.taskIndex = taskIndex;
    }

    public void run(TaskList tasks) throws InputException {
        switch (taskType) {
        case MARK:
            tasks.markTask(taskIndex);
            break;

        case UNMARK:
            tasks.unmarkTask(taskIndex);
            break;

        default:
            break;
        }
        ResponseManager.printActionOnTasks(taskType.getType(),
                tasks.getPosAt(taskIndex).toString());
    }

    public boolean isExit() {
        return false;
    }
}
