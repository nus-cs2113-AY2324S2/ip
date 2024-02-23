package bossman.command;

import bossman.exceptions.commandexceptions.InvalidDeleteCommandException;
import bossman.task.TaskList;

public class DeleteCommand implements Command{
    private TaskList userTasks;
    private int taskId;

    public DeleteCommand(TaskList userTasks, String commandArgs) throws InvalidDeleteCommandException{
        this.userTasks = userTasks;
        try {
            taskId = Integer.parseInt(commandArgs);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteCommandException("Out of range");
        }
    }

    @Override
    public void execute() {
        userTasks.removeTask(taskId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
