package beefy.command;

import beefy.BeefyException;
import beefy.task.TaskList;

public class ToDoCommand implements Command{
    private TaskList userTasks;
    private String taskDescription;

    public ToDoCommand(TaskList userTasks, String userParams) throws BeefyException {
        if (userParams.isEmpty()) {
            throw new BeefyException("Quit fooling me, I do not see any task to add!");
        }
        this.userTasks = userTasks;
        taskDescription = userParams;
    }

    @Override
    public void execute() {
        userTasks.addTask(taskDescription);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
