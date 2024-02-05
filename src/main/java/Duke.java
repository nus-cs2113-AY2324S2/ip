public class Duke {
    static String botName = "Uwunzhe";
    static String logo = " _    _                          _          \n"
            + "| |  | |                        | |         \n"
            + "| |  | |_      ___   _ _ __  ___| |__   ___ \n"
            + "| |  | \\ \\ /\\ / / | | | '_ \\|_  / '_ \\ / _ \\\n"
            + "| |__| |\\ V  V /| |_| | | | |/ /| | | |  __/\n"
            + " \\____/  \\_/\\_/  \\__,_|_| |_/___|_| |_|\\___|\n";

    static String lineString = "____________________________________________________________";

    public static void displayLogo() {
        System.out.println(logo);
    }

    public static void addLineBreak() {
        System.out.println(lineString);
    }

    public static void initialize() {
        displayLogo();
        addLineBreak();

        System.out.println("HELLO MY POSITIVE MENTALITY FLEN!! MY NAME IS " + botName.toUpperCase() + "!!!");
        System.out.println("Actually uh... What even do you want me to do?");

        addLineBreak();
    }

    public static void exit() {
        System.out.println("Good night my positive mentality flen, "
                + "it is time for me to take my happy pills. Bye Bye!");
        addLineBreak();
    }

    public static void main(String[] args) {
        initialize();
        exit();
    }
}
