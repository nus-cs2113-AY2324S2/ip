package bossman.exceptions.commandexceptions;

public class UnknownCommandException extends BossManExceptions {
    public UnknownCommandException(String message) {
        super("Error: " + message);
    }
}
