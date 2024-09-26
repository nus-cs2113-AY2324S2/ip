package chatman.exceptions;

import java.util.ArrayList;

/**
 * Implements custom abstract exception class for ChatMan-related exceptions to inherit from.
 *
 * @author LWachtel1
 * */
public abstract class ChatManException extends Exception {

    private String commandType;
    private String erroneousInput;
    /**
     * Constructor for ChatManException class.
     *
     * @param commandType Command type entered by user for which exception was thrown.
     * @param erroneousInput String storing specific problem with user input which resulted in exception.
     **/
    public ChatManException(String commandType, String erroneousInput) {
        this.commandType = commandType;
        this.erroneousInput = erroneousInput;
    }

    /**
     * Provides abstract method to be overriden by concrete subclasses, which each prints own
     * exception-specific error message.
     * */
    public abstract void sendErrorMsg();

    public String getCommandType() {
        return commandType;
    }

    public String getErroneousInput() {
        return erroneousInput;
    }
}
