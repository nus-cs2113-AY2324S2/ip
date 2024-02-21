package anonbot.exception;

public class InvalidCommandException extends Exception implements AnonbotExceptionHandler {
    private String invalidCommand;

    public InvalidCommandException(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    private String getInvalidCommand() {
        return invalidCommand;
    }

    @Override
    public void printErrorMessage() {
        if (getInvalidCommand().isEmpty()) {
            System.out.println("[Error] No command entered");
        } else {
            System.out.println("[Error] Invalid command entered: " + getInvalidCommand());
        }
    }
}
