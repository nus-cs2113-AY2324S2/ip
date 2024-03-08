package chris.commands;

import chris.tasktypes.taskList;
import chris.tasktypes.Event;

public class eventCommand extends Command{
    public eventCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        tasks.addTask(new Event(description, false));
        return "New event added!\n" + tasks.printTaskList();
    }
}
