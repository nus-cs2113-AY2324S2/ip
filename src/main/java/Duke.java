import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui userInterface = new Ui();
        userInterface.printLogo();
        userInterface.generateWelcome();

        Scanner sc = new Scanner(System.in);
        boolean hasMoreInput = true;

        do {
            String userCommand = sc.next();

            if (userCommand.equals("bye")) {
                hasMoreInput = false;
            } else {
                userInterface.echoCommand(userCommand);
            }

        } while (hasMoreInput);

        userInterface.generateExit();
    }
}
