/**
 * Handles methods that is shown in UI
 */
public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    /**
     * Prints welcome message
     */
    public static void welcomeMessage() {
        System.out.println(LINE_SEPARATOR + "\n" +
                "Hello! I'm Duck\n" +
                "What can I do for you?\n" +
                "  _____  _    _  _____ _  __\n" +
                " |  __ \\| |  | |/ ____| |/ /\n" +
                " | |  | | |  | | |    | ' / \n" +
                " | |  | | |  | | |    |  <  \n" +
                " | |__| | |__| | |____| . \\ \n" +
                " |_____/ \\____/ \\_____|_|\\_\\");
    }

    /**
     * Prints exit message and exits out of application
     * @return true
     */
    public static boolean exitMessage() {
        System.out.println(LINE_SEPARATOR + "\n" + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR);
        return true;
    }

}
