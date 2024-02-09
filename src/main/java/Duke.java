public class Duke {
    public static void main(String[] args) {

	// CONSTANTS

	// Logo
        String logo = " \n"
        + "  ________  ________  ___  ________  ________                            \n"
        + "  |\\   __  \\|\\   __  \\|\\  \\|\\   __  \\|\\   ___  \\               \n"
        + "  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\    \n"
        + "   \\ \\  \\ \\  \\ \\   _  _\\ \\  \\ \\  \\ \\  \\ \\  \\\\ \\  \\       \n"
        + "    \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\\\ \\  \\   \n"
        + "     \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\_______\\ \\__\\\\ \\__\\    \n"
        + "      \\|_______|\\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|              \n";

        // Border line
        String line = "____________________________________________________________";


	// CODE

	// Greeting Message
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Orion!\n" + "What can I do for you?");
        System.out.println(line);

	// Print farewell
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
