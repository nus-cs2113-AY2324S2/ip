package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidFindCommandException extends BossManExceptions {
    public InvalidFindCommandException(String message) {
        super("Find error: " + message);
    }
}
