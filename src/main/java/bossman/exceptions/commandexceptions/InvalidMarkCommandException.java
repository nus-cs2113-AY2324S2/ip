package bossman.exceptions.commandexceptions;

public class InvalidMarkCommandException extends BossManExceptions {
    public InvalidMarkCommandException(String message) {
        super("Mark/ unmark error: " + message);
    }
}
