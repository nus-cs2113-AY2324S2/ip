package exceptions;
/**
 * This exception is thrown when there is an error accessing or parsing the file that stores tasks.
 * It is crucial for handling file-related operations, ensuring that any file errors are appropriately managed.
 */

public class MarioFileError extends Exception{
    public MarioFileError(){
        super("Error while parsing file!");
    }
}
