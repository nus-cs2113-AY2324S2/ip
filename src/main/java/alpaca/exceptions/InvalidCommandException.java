package alpaca.exceptions;

/**
 * Represents an exception when the command is invalid
 */
public class InvalidCommandException extends AlpacaException{
    @Override
    public String toString () {
        return "Baa-baa-baa, I'm sorry, but I don't know what that means :-(\n";
    }
}
