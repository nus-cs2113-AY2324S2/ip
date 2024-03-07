package anonbot.exception;

/**
 * Exception class for an empty task description when it should be non-empty.
 */
public class EmptyTaskArgumentException extends EmptyArgumentException {
    public EmptyTaskArgumentException(String taskCommand) {
        super(taskCommand);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("[Error] " + getCommand() + " task has an empty task Description");
        printSyntax();
    }
}
