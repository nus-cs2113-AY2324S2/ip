package chris.commands;

import chris.tasktypes.taskList;

public class quitCommand extends Command{
    public quitCommand(String[] description) {
        super(description);
    }

    public String execute(taskList tasks) {
        super.isQuit = true;
        return "Goodbye and see you soon!";
    }
}
