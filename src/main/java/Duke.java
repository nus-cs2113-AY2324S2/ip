public class Duke {
    // ASCII Art Generated from: https://patorjk.com/software/taag/#p=display&f=Big&t=anonBot
    private static final String LOGO = "                          ____        _   \n"
            + "                         |  _ \\      | |  \n"
            + "   __ _ _ __   ___  _ __ | |_) | ___ | |_ \n"
            + "  / _` | '_ \\ / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | (_| | | | | (_) | | | | |_) | (_) | |_ \n"
            + "  \\__,_|_| |_|\\___/|_| |_|____/ \\___/ \\__|\n";
    private static final String SECTION_BAR = "____________________________________________________________";

    /**
     * Prints greeting messages.
     */
    private static void printGreetings() {
        System.out.println(LOGO);
        System.out.println(SECTION_BAR);
        System.out.println("Hello! I'm anonBot");
        System.out.println("What can I do for you?");
        System.out.println(SECTION_BAR + "\n");
    }

    /**
     * Prints goodbye message.
     */
    private static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SECTION_BAR);
    }

    /**
     * Greets the user and exits.
     *
     * @param args List of command-line arguments
     */
    public static void main(String[] args) {
        printGreetings();
        printGoodbye();
    }
}
