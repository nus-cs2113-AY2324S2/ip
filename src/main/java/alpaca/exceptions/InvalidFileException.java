package alpaca.exceptions;

<<<<<<< HEAD
/**
 * Represents an exception when the file is corrupted
 */
public class InvalidFileException extends Exception{
=======
public class InvalidFileException extends AlpacaException{
>>>>>>> master
    @Override
    public String toString () {
        return "Baa-baa-baa, file is corrupted\n";
    }
}
