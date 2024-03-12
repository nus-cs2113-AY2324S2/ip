package MassimoBoiException;

/**
 * Handles any Chatbot related exceptions.
 */
public class MassimoBoiException extends Exception{
    /**
     * Prints out the error message.
     */
    public void errorMessage(){
        System.out.println("Your instructions have thrown an error");
    }
}
