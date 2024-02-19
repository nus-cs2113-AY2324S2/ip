package kobot.command;

import kobot.task.TaskList;

public abstract class Command {
    protected static final int MAX_COMMAND_SPLIT_LIMIT = 2;
    protected boolean canExecute;
    protected TaskList taskList;
    
    Command(TaskList taskList) {
        canExecute = false;
        this.taskList = taskList;
    }
    public abstract void execute();
}
