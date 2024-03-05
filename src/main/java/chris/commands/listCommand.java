package chris.commands;

import chris.tasktypes.taskList;

public class listCommand extends Command{
    public listCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) {
        System.out.println("Here are your current tasks!");
        tasks.printTaskList();
    }
}
