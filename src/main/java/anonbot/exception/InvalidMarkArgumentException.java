package anonbot.exception;

/**
 * Exception class for invalid arguments for mark and unmark commands
 */
public class InvalidMarkArgumentException extends InvalidArgumentException {
    public InvalidMarkArgumentException(String command, String argument) {
        super(command, argument);
    }

    @Override
    public void printErrorMessage() {
        System.out.format("[Error] There is no task number %s to %s" + System.lineSeparator(),
                getArgument(), getCommand());
    }
}
