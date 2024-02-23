package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidMarkCommandException extends BossManExceptions {
    public InvalidMarkCommandException(String message) {
        super("Mark/ unmark error: " + message);
    }
}
