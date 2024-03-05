package edithExceptionPackage;

/**
 * Represents the exception that is thrown when an error occurs in the program.
 */
public class ChatBotExceptions extends Exception{

    /**
     * Constructor for the ChatBotExceptions class.
     * @param message The error message.
     */
    public ChatBotExceptions(String message){

        super("ERROR: " + message);
    }
}