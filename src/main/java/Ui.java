import java.util.Scanner;

public class Ui {
    public static void greetUser() {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");
    }

    public static String getFirstCommand() {
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        return userCommand;
    }
    public static void endSession() {
        System.out.println("Tasks saved successfully.");
        System.out.println("Bye! Hope to see you again :)");
    }
}
