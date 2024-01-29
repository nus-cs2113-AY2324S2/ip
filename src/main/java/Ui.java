public class Ui {
    private static final String WELCOME = " Hello! I'm Bob\n What can I do for you?";
    private static final String EXIT = " Bye. Hope to see you again soon!";
    private static final String SEPARATOR = "____________________________________________________________";

    void printLogo() {
        String bobAsciiLogo = " ____        _     \n"
               + "|  _ \\      | |\n"
               + "| |_) | ___ | |__\n"
               + "|  _ < / _ \\| '_ \\\n"
               + "| |_) | (_) | |_) |\n"
               + "|____/ \\___/|_.__/\n";

        System.out.print(bobAsciiLogo);
    }

    void generateWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(WELCOME);
        System.out.println(SEPARATOR);
    }

    void generateExit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT);
        System.out.println(SEPARATOR);
    }

    void echoCommand(String command) {
        System.out.println(SEPARATOR);
        System.out.println(command);
        System.out.println(SEPARATOR);
    }
}
