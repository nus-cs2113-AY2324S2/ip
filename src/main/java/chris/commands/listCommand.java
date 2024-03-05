package chris.commands;

import chris.tasktypes.taskList;

public class listCommand extends Command{
    public listCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        return tasks.printTaskList();
    }
}
