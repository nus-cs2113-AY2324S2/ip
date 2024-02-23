package bossman.command;

import bossman.exceptions.commandexceptions.InvalidMarkCommandException;
import bossman.task.TaskList;

public class UnmarkCommand implements Command {
    private TaskList userTasks;
    private int taskId;

    public UnmarkCommand(TaskList userTasks, String commandArgs) throws InvalidMarkCommandException {
        this.userTasks = userTasks;

        try {
            taskId = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            throw new InvalidMarkCommandException("Out of range");
        }
    }

    @Override
    public void execute() {
        userTasks.unmarkTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
