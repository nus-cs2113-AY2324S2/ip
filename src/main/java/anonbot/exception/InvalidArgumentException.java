package anonbot.exception;

import anonbot.misc.Command;

import javax.swing.*;

public class InvalidArgumentException extends Exception implements AnonbotExceptionHandler {
    private String command;
    private String argument;
    private String commandArgumentSyntax;

    public InvalidArgumentException(String command, String argument){
        setCommand(command);
        setArgument(argument);
        setCommandArgumentSyntax(command);
    }

    private void setCommand(String command) {
        this.command = command;
    }
    private void setArgument(String argument) {
        this.argument = argument;
    }
    private void setCommandArgumentSyntax(String command) {
        this.commandArgumentSyntax = Command.getAssociatedCommandArgumentSyntax(command);
    }

    protected String getCommand() {
        return this.command;
    }

    protected String getArgument() {
        return this.argument;
    }
    protected String getCommandArgumentSyntax() {
        return this.commandArgumentSyntax;
    }

    protected void printSyntax() {
        System.out.println("Syntax: " + getCommandArgumentSyntax());
    }
    @Override
    public void printErrorMessage() {
        System.out.println("[Error] Command " + getCommand() + " has invalid argument " + getArgument());
        printSyntax();
    }
}
