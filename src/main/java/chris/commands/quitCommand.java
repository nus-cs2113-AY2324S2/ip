package chris.commands;

import chris.tasktypes.taskList;

public class quitCommand extends Command{
    public quitCommand(String[] description) {
        super(description);
    }

    public void execute(taskList tasks) {
        super.isQuit = true;
    }
}
