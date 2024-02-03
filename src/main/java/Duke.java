import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Ms Chatty :)");
        System.out.println("What can I do for you?");

        String userCommand;
        Scanner in = new Scanner(System.in);
        userCommand = in.nextLine();
        while (!(userCommand.equals("bye"))) {
            System.out.println(userCommand);
            userCommand = in.nextLine();
        }

        System.out.println("Bye! Hope to see you again :)");
    }
}
