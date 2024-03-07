package alpaca.exceptions;
public class EmptyTaskDescriptionException extends AlpacaException{
    private String message;
    public EmptyTaskDescriptionException(String message) {
        this.message = message;
    }
    @Override
    public String toString () {
        return getMessage();
    }
}
