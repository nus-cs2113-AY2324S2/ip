package uwunzhe.exceptions;

import uwunzhe.util.Ui;

public class UwunzheException extends Exception {
    /**
     * Constructor for UwunzheException.
     * 
     * @param message The cause of the exception.
     */
    public UwunzheException(String message) {
        super(message);
    }

    /**
     * Constructor for UwunzheException.
     * 
     * @param message The cause of the exception using enum {@link ExceptionMessages}.
     */
    public UwunzheException(ExceptionMessages message) {
        super(message.getMessage());
    }
    
    /**
     * Prints the exception message.
     * 
     * @param e The exception message to be printed.
     */
    public static void printException(UwunzheException e) {
        Ui.println(e.getMessage());
    }
}
