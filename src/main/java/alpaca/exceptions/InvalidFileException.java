package alpaca.exceptions;

public class InvalidFileException extends AlpacaException{
    @Override
    public String toString () {
        return "Baa-baa-baa, file is corrupted\n";
    }
}
