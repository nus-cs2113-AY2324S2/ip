package chris.commands;

import chris.tasktypes.taskList;

public class findCommand extends Command{
    public findCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        return tasks.find(description[0]).printTaskList();
    }
}
