package exceptions;
/**
 * This exception is thrown when the user input does not match the expected format.
 * It is used to prompt the user to check their input and adhere to the expected command or data formats.
 */

public class MarioWrongFormat extends Exception {
    public MarioWrongFormat() {
        super("Check your input format!");
    }
}
