package chris.commands;

import chris.tasktypes.taskList;
import chris.tasktypes.Event;

public class eventCommand extends Command{
    public eventCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) {
        tasks.addTask(new Event(description, false));
        System.out.println("New event added!");
        tasks.printTaskList();
    }
}
