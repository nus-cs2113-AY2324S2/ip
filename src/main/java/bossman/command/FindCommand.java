package bossman.command;

import bossman.exceptions.commandexceptions.InvalidFindCommandException;
import bossman.task.TaskList;

public class FindCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    public FindCommand(TaskList userTasks, String userParams) throws InvalidFindCommandException {
        if (userParams.isEmpty()) {
            throw new InvalidFindCommandException("Missing parameter");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    @Override
    public void execute() {
        userTasks.findTask(taskDescription);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
