package anonbot.exception;

import anonbot.misc.Command;

/**
 * General Exception Class that handles invalid arguments for commands that expect arguments.
 */
public class InvalidArgumentException extends Exception implements AnonbotExceptionHandler {
    private String command;
    private String argument;
    private String commandArgumentSyntax;

    /**
     * Creates a new InvalidArgument exception.
     *
     * @param command The associated command for which the argument is invalid.
     * @param argument The argument that caused the exception.
     */
    public InvalidArgumentException(String command, String argument) {
        setCommand(command);
        setArgument(argument);
        setCommandArgumentSyntax(command);
    }

    private void setCommand(String command) {
        this.command = command;
    }

    protected String getCommand() {
        return this.command;
    }

    private void setArgument(String argument) {
        this.argument = argument;
    }

    protected String getArgument() {
        return this.argument;
    }

    private void setCommandArgumentSyntax(String command) {
        this.commandArgumentSyntax = Command.getAssociatedCommandArgumentSyntax(command);
    }

    private String getCommandArgumentSyntax() {
        return this.commandArgumentSyntax;
    }

    /**
     * Prints to console the required syntax for a particular command.
     */
    protected void printSyntax() {
        System.out.println("Syntax: " + getCommandArgumentSyntax());
    }

    @Override
    public void printErrorMessage() {
        System.out.println("[Error] Command " + getCommand() + " has invalid argument " + getArgument());
        printSyntax();
    }
}
