package anonbot.exception;

/**
 * Exception class for empty arguments when it should be non-empty.
 */
public class EmptyArgumentException extends InvalidArgumentException {
    public EmptyArgumentException(String command) {
        super(command, "");
    }

    @Override
    public void printErrorMessage() {
        System.out.println("[Error] Argument expected for the command: " + getCommand());
        super.printSyntax();
    }
}
