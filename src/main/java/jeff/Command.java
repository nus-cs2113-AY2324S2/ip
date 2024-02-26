package jeff;

/**
 * Represents a command to be executed by the program.
 */
public abstract class Command {

    /**
     * Defines the behavior to be executed by subclasses.
     * Subclasses must implement this method to provide specific functionality.
     */
    public abstract void execute();

    /**
     * Checks if the program should exit.
     *
     * @return {@code false} by default. Will only return {@code true} if overwritten in subclass.
     */
    public boolean isExit() {
        return false;
    }
}
