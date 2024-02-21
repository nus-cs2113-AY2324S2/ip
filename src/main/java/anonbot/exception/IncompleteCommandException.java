package anonbot.exception;

public class IncompleteCommandException extends Exception implements AnonbotExceptionHandler {
    private String command;
    private String argument;

    public IncompleteCommandException(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    protected String getCommand() {
        return command;
    }


    protected String getArgument() {
        return argument;
    }

    @Override
    public void printErrorMessage() {
        System.out.format("Invalid argument for %s: %s" + System.lineSeparator(), command, argument);
    }
}
