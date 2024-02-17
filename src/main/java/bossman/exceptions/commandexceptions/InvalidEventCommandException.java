package bossman.exceptions.commandexceptions;

public class InvalidEventCommandException extends BossManExceptions {
    public InvalidEventCommandException(String message) {
        super("Event error: " + message);
    }
}
