package chris.commands;
import chris.customexceptions.customExceptions;
import chris.tasktypes.taskList;

public class Command {
    protected String[] description;
    protected boolean isQuit = false;
    public Command(String[] description) {
        this.description = description;
    }

    /**
     * Executes the command when called
     * @param tasks taskList of tasks to be executed on
     * @return String that contains the response of the action
     * @throws customExceptions if any of the inputs is in incorrect format
     */
    public String execute(taskList tasks) throws customExceptions {
        return "Sorry, I do not recognise this command!";
    }

    /**
     * This function is called to tell the main loop whether to continue running or not
     * @return isQuit of Command
     */
    public boolean quit() {
        return this.isQuit;
    }
}
