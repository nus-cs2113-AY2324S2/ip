package OGFCore;

/**
 * Exception that stores the message to be shown to the user when handled, as well as whether the program should be terminated.
 */
public class OGFException extends Exception{
    private final boolean isFatal;
    public OGFException(String message, boolean isFatal){
        super(message);
        this.isFatal = isFatal;
    }
    public boolean getFatal(){
        return isFatal;
    }
}
