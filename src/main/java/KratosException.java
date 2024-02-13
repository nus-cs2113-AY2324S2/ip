public class KratosException extends Exception {
    public static void handleException(Exception e, String command) {
        if (e instanceof IllegalArgumentException) {
            System.out.println(Kratos.LINE);
            System.out.println("Verily, the COMMAND hath eluded us, " +
                    "obscured in the realm of the unknown.\n" +
                    "Let us seek clarity and purpose in our journey forward.");
            System.out.println(Kratos.LINE);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println(Kratos.LINE);
            if (command.startsWith("todo")) {
                System.out.println("Your intentions for a 'todo' task are as vague as the mists of Hades.\n" +
                        "Specify a task after 'todo' or face the consequences.");
            } else if (command.startsWith("deadline")) {
                System.out.println("Your intentions for a 'deadline' task are as unclear as the shifting sands of time.\n" +
                        "Specify a task after 'deadline' or face the consequences.");
            } else if (command.startsWith("event")) {
                System.out.println("Your intentions for an 'event' are as elusive as a fleeting dream.\n" +
                        "Specify a task after 'event' or face the consequences.");
            } else {
                System.out.println("Your intentions are as vague as the mists of Hades.\n" +
                        "Specify a valid command or face the consequences.");
            }
            System.out.println(Kratos.LINE);
        } else {
            // Handle other exceptions
            System.out.println(Kratos.LINE);
            System.out.println("An unexpected error has materialized, " +
                    "shrouded in the veils of chaos. " +
                    "We must navigate through this uncertainty with vigilance and resolve.");
            System.out.println(Kratos.LINE);
        }
    }
}
