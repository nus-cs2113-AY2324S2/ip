package chris.commands;

import chris.tasktypes.taskList;
import chris.tasktypes.Deadline;

public class deadlineCommand extends Command{
    public deadlineCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) {
        tasks.addTask(new Deadline(description, false));
        System.out.println("New deadline added!");
        tasks.printTaskList();
    }
}
