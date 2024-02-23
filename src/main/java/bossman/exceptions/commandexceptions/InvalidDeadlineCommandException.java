package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidDeadlineCommandException extends BossManExceptions {
    public InvalidDeadlineCommandException(String message) {
        super("Deadline error: " + message);
    }
}
