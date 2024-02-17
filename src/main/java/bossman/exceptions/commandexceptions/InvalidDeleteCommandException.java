package bossman.exceptions.commandexceptions;

public class InvalidDeleteCommandException extends BossManExceptions {
    public InvalidDeleteCommandException(String message) {
        super("Delete error: " + message);
    }
}
