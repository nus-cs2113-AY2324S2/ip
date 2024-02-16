package exception;

import anonbot.AnonBot;

public class IncompleteCommandException  extends Exception implements AnonbotExceptionHandler {
    private String command;
    private String argument;

    public IncompleteCommandException(String command, String argument){
        this.command = command;
        this.argument = argument;
    }

    @Override
    public void printErrorMessage() {
        System.out.format("Invalid argument for %s: %s" + System.lineSeparator(), command, argument);
    }
}
