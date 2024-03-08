package Backend;

/**
 * This class handles the String-outputted text giving feedback to the user.
 */
public class Ui {

    public static void WelcomeMessage() {
        System.out.println("ヽ(•‿•)ノ");
        System.out.println("______________________________________");
        System.out.println("Heya, Im ya Chatbot, call me John!\nWhat can I do for you today?");
    }

    public static void Goodbye() {
        System.out.println("______________________________________");
        System.out.println("Hope to see you soon Bruda.");
    }

    public static void ReadCommand() {
        System.out.print("Enter command: ");
    }

    public static void showLine() {
        System.out.println("______________________________________");
    }
}
