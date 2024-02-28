package carrot.command;

/**
 * Represents an abstract command.
 * <p>
 * This is the superclass for other command types such as Todo, List, Delete, Exit Commands.
 */
public abstract class Command {
    /**
     * Executes the command based on the provided user input.
     *
     * @param userInput the user input string containing the command and any arguments
     */
    public abstract void execute(String userInput);
}
