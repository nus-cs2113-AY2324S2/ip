package anonbot.exception;

public class EmptyArgumentException extends Exception implements AnonbotExceptionHandler {
    private String command;

    public EmptyArgumentException(String command) {
        this.command = command;
    }

    private String getCommand() {
        return command;
    }

    @Override
    public void printErrorMessage() {
        System.out.println("[Error] Argument expected for the command: " + getCommand());
    }
}
