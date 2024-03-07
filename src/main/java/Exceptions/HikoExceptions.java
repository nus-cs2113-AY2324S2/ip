package Exceptions;


public class HikoExceptions extends Exception{
    /**
     * Constructs a new HikoException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HikoExceptions(String message){
        super(message);
    }
}
