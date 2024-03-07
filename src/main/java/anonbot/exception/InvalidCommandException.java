package anonbot.exception;

import anonbot.misc.Command;

public class InvalidCommandException extends Exception implements AnonbotExceptionHandler {
    private String invalidCommand;

    public InvalidCommandException(String invalidCommand) {
        setInvalidCommand(invalidCommand);
    }

    private void setInvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    private String getInvalidCommand() {
        return this.invalidCommand;
    }

    @Override
    public void printErrorMessage() {
        if (getInvalidCommand().isEmpty()) {
            System.out.println("[Error] No command entered. Please check that there are no leading spaces.");
        } else {
            System.out.println("[Error] Invalid command entered: " + getInvalidCommand());
            System.out.print("Valid Commands: ");
            Command.printListOfAvailableCommand();
            System.out.println();
        }
    }
}
