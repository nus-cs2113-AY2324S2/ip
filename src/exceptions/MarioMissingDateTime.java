package exceptions;
/**
 * This exception is thrown when no date and time are detected in the input provided by the user.
 * It is critical for commands where date and time information is mandatory for the operation to proceed.
 */

public class MarioMissingDateTime extends Exception{
    public MarioMissingDateTime(){
        super("No date and time detected!");
    }
}
