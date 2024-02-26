package exceptions;

import utils.constants;

// Custom exception for handling invalid task input
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Invalid command entered!!!");
    }
}
