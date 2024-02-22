public class InvalidCommandException extends Exception {
    public static void handleInvalidCommandException() {
        System.out.println("OH NOOO! Please enter the todo task name!!!");
    }
}

// --- TO FIX ---
// Need to create different methods for adding
// Todo, adding Deadline, etc...
// so can throw exceptions for each function