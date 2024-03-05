import java.util.Scanner;
/**
 * User interface class to deal with interactions with user.
 */
public class Ui {
    /**
     * Prints messages to indicate start of user's session.
     */
    public static void greetUser() {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");
    }

    /**
     * Scans the very first command from user.
     *
     * @return A string representing user's command.
     */
    public static String getFirstCommand() {
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        return userCommand;
    }

    /**
     * Prints messages to indicate end of user's session.
     */
    public static void endSession() {
        System.out.println("Tasks saved successfully.");
        System.out.println("Bye! Hope to see you again :)");
    }
}
