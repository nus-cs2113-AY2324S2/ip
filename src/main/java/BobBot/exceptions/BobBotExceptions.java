package BobBot.exceptions;

/**
 * Implements a custom exception class for BobBot.
 * 
 * <p> This class is the parent class for all custom exceptions in BobBot. </p>
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public abstract class BobBotExceptions extends Exception {
    
    public BobBotExceptions() {

    }

    public abstract void displayExceptionMessage();
}
