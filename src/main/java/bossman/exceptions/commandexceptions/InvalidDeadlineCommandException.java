package bossman.exceptions.commandexceptions;

public class InvalidDeadlineCommandException extends BossManExceptions {
    public InvalidDeadlineCommandException(String message) {
        super("Deadline error: " + message);
    }
}
