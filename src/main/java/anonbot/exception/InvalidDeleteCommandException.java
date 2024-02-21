package anonbot.exception;

public class InvalidDeleteCommandException extends IncompleteCommandException {
    public InvalidDeleteCommandException(String command, String argument) {
        super(command, argument);
    }

    @Override
    public void printErrorMessage() {
        System.out.format("There is no task number %s to %s" + System.lineSeparator(), getArgument(),getCommand());
    }
}
