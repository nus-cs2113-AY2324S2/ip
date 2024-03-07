package anonbot.exception;

public class EmptyArgumentException extends InvalidArgumentException {
    public EmptyArgumentException(String command) {
        super(command, "");
    }

    @Override
    public void printErrorMessage() {
        System.out.println("[Error] Argument expected for the command: " + getCommand());
        super.printSyntax();
    }
}
