public class DukeException extends Exception {

    public static void handleException(Exception exception, String input) {
        final String SEPARATOR = "=====================================================================================================================";

        String[] splitInput = input.split(" ");

        System.out.println(SEPARATOR);

        if (exception instanceof ArrayIndexOutOfBoundsException) {
            System.out.println("You have exceeded the task limit of 100 tasks.");

        } else if (exception instanceof StringIndexOutOfBoundsException) {

            if (input.startsWith("mark") || input.startsWith("unmark")) {
                System.out.println("The task to marked or unmarked is not stated clearly.");
            }
            else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
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

