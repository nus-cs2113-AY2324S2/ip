package chris.commands;
import chris.customexceptions.customExceptions;
import chris.tasktypes.taskList;

public class Command {
    protected String[] description;
    protected boolean isQuit = false;
    public Command(String[] description) {
        this.description = description;
    }

    public void execute(taskList tasks) throws customExceptions {
        System.out.println("Sorry, I do not recognise this command!");
    }

    public boolean quit() {
        return this.isQuit;
    }
}
