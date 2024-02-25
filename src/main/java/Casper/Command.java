package Casper;

/**
 * An abstract class representing a general command to be executed.
 */
public abstract class Command {
    public boolean isRunning;
    public Command(boolean isRunning){
        this.isRunning = isRunning;
    }
    public abstract void execute(Ui ui, TaskList tasks, Storage storage);
}
