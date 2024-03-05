package exceptions;

public class InvalidTodoSyntaxException extends Exception {
    public InvalidTodoSyntaxException(String string) {
        super(string);
    }
}
