package bossman.command;

import bossman.task.TaskList;

public class ListCommand implements Command {
    private TaskList userTasks;

    public ListCommand(TaskList userTasks) {
        this.userTasks = userTasks;
    }

    @Override
    public void execute() {
        userTasks.printTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
