package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidDeleteCommandException extends BossManExceptions {
    public InvalidDeleteCommandException(String message) {
        super("Delete error: " + message);
    }
}
