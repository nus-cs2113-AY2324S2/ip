package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class UnknownCommandException extends BossManExceptions {
    public UnknownCommandException(String message) {
        super("Error: " + message);
    }
}
