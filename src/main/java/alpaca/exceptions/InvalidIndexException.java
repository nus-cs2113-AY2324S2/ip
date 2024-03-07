package alpaca.exceptions;

/**
 * Represents an exception when the file is corrupted
 */
public class InvalidIndexException extends AlpacaException{
    @Override
    public String toString () {
        return "Baa-baa-baa, index is invalid\n";
    }
}
