package uwunzhe.exceptions;

import uwunzhe.util.Ui;

public class UwunzheException extends Exception {
    /**
     * Constructor for UwunzheException.
     * 
     * @param message
     */
    public UwunzheException(String message) {
        super(message);
    }
    
    /**
     * Prints the exception message.
     * 
     * @param e The exception to be printed.
     */
    public static void printException(UwunzheException e) {
        Ui.println(e.getMessage());
    }
}
