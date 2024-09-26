public class BrennanException extends Exception {
    /**
     * Handles exceptions that occur during Duke application operations.
     * It prints appropriate error messages based on the type of exception.
     * If the exception is related to array index out of bounds,
     * it notifies the user about exceeding the task limit.
     * If the exception is related to string index out of bounds,
     * it identifies whether the input is incomplete or unclear,
     * depending on the operation being performed.
     * If the exception is an illegal argument exception,
     * it indicates that the command provided is unfamiliar.
     * For any other type of exception, it displays a generic unknown error message.
     *
     * @param exception The exception that occurred.
     * @param input     The input string where the exception occurred.
     */
    public static void handleException(Exception exception, String input) {
        final String SEPARATOR = "=====================================================================================================================";

        String[] splitInput = input.split(" ");

        System.out.println(SEPARATOR);

        if (exception instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("You have exceeded the task limit of 100 tasks.");

        } else if (exception instanceof StringIndexOutOfBoundsException) {

            if (input.startsWith("mark") || input.startsWith("unmark")) {
                System.out.println("The task to marked or unmarked is not stated clearly.");
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("find")) {
                System.out.println("Incomplete " + splitInput[0] + " detected. " +
                        "Your statement is not clear. Please fix this.");
            }
        } else if (exception instanceof IllegalArgumentException) {
            System.out.println("Unfamiliar commands cannot be accepted by the system.");

        } else {
            System.out.println("Unknown error detected. Try to fix this immediately.");
        }
        System.out.println(SEPARATOR);
    }
}