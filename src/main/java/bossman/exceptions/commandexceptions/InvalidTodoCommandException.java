package bossman.exceptions.commandexceptions;

public class InvalidTodoCommandException extends BossManExceptions {
    public InvalidTodoCommandException(String message) {
        super("Todo error: " + message);
    }
}
