import java.util.Scanner;
/**
 * User interface class to deal with interactions with user.
 */
public class Ui {
    /**
     * Prints messages to indicate start of user's session.
     */
    public static void greetUser() {
        System.out.println(Messages.GREET_USER_MESSAGE);
        System.out.println(Messages.PROMPTS_USER_INPUT);
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
        System.out.println(Messages.TASKS_SAVED_MESSAGE);
        System.out.println(Messages.FAREWELL_MESSAGE);
    }
}
