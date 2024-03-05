package chris.commands;
import chris.customexceptions.customExceptions;
import chris.tasktypes.taskList;

public class Command {
    protected String[] description;
    protected boolean isQuit = false;
    public Command(String[] description) {
        this.description = description;
    }

    public String execute(taskList tasks) throws customExceptions {
        return "Sorry, I do not recognise this command!";
    }

    public boolean quit() {
        return this.isQuit;
    }
}
