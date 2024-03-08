package chris.commands;

import chris.tasktypes.taskList;
import chris.tasktypes.Deadline;

public class deadlineCommand extends Command{
    public deadlineCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        tasks.addTask(new Deadline(description, false));
        return "New deadline added!\n" + tasks.printTaskList();
    }
}
