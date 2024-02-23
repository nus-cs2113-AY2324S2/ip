package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidEventCommandException extends BossManExceptions {
    public InvalidEventCommandException(String message) {
        super("Event error: " + message);
    }
}
