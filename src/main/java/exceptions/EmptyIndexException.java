package exceptions;

import utils.constants;

// Custom exception for handling invalid task input
public class EmptyIndexException extends Exception {
    public EmptyIndexException() {
        super("You have not entered the index!!!");
    }
}
