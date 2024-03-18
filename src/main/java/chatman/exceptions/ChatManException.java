package chatman.exceptions;

/**
 * Implements custom abstract exception class for ChatMan-related exceptions to inherit from.
 *
 * @author LWachtel1
 * */
public abstract class ChatManException extends Exception{

    /**
     * Constructor for ChatManException class.
     **/
    public ChatManException() {

    }

    /**
     * Provides abstract method to be overriden by concrete subclasses, which each prints own
     * exception-specific error message.
     * */
    public abstract void sendErrorMsg();

}
