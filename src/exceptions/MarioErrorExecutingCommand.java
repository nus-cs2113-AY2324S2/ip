package exceptions;
/**
 * This exception is thrown when an error occurs during the execution of a command.
 * It encapsulates the original exception, providing a way to identify and handle specific command execution errors.
 */

public class MarioErrorExecutingCommand extends Exception {
    public MarioErrorExecutingCommand(Exception args) {
        super("Error occured when executing command!\nError:\t" + args.getMessage());
    }
}