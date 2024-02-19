package kobot.command;

import kobot.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super(taskList);
    }
    public void execute() {
        taskList.printTaskList();
    }
}
