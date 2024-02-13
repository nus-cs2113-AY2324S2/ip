package exceptions;

import utils.Constants;

// Custom exception for handling invalid task input
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        System.out.println(Constants.BREAKLINE);
        System.out.println("The command is not correct.");
        System.out.println(Constants.BREAKLINE);
    }
}
