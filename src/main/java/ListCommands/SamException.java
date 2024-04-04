package ListCommands;

// Custom exception class for Sam application
public class SamException extends Exception {
    // Constructor to create a Commands.SamException with an error message
    public SamException(String errorMessage) {
        if (errorMessage != null) {
            // Print the error message if not null
            System.out.println(errorMessage);
        }
        // Print a generic error message
        System.out.println("Please try again.");
    }
}
