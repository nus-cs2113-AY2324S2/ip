package bossman.exceptions.commandexceptions;

import bossman.exceptions.BossManExceptions;

public class InvalidTodoCommandException extends BossManExceptions {
    public InvalidTodoCommandException(String message) {
        super("Todo error: " + message);
    }
}
