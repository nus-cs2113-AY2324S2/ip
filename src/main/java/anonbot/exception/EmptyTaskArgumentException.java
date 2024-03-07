package anonbot.exception;

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
