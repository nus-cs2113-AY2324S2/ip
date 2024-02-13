public class KratosException extends Exception {
    public static void handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.out.println(Kratos.LINE);
            System.out.println("Verily, the COMMAND hath eluded us, " +
                    "obscured in the realm of the unknown.\n" +
                    "Let us seek clarity and purpose in our journey forward.");
            System.out.println(Kratos.LINE);
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println(Kratos.LINE);
            System.out.println("Your intentions are as vague as the mists of Hades.\n" +
                    "Specify a task after 'todo' or face the consequences.");
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
