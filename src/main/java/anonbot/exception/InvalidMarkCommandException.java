package anonbot.exception;

public class InvalidMarkCommandException extends IncompleteCommandException {
    public InvalidMarkCommandException(String command, String argument) {
        super(command, argument);
    }

    @Override
    public void printErrorMessage() {
        System.out.format("There is no task number %s to %s" + System.lineSeparator(), getArgument(),getCommand());
    }
}
