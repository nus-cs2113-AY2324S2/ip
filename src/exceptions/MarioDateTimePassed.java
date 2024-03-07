package exceptions;
/**
 * This exception is thrown when an input date has already passed the current date. 
 * It ensures that tasks or events specified in the Mario application have future dates.
 */

public class MarioDateTimePassed extends Exception {
    public MarioDateTimePassed() {
        super("Input date has already passed!");
    }
}