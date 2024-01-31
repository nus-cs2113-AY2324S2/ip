import java.util.Scanner;

public class Zuke {
    private static final String INDENTATION_LINE =
            "____________________________________________________________\n";

    public static void main(String[] args) {
        String logo =
                "███████╗██╗   ██╗██╗  ██╗███████╗\n" +
                "╚══███╔╝██║   ██║██║ ██╔╝██╔════╝\n" +
                "  ███╔╝ ██║   ██║█████╔╝ █████╗  \n" +
                " ███╔╝  ██║   ██║██╔═██╗ ██╔══╝  \n" +
                "███████╗╚██████╔╝██║  ██╗███████╗\n" +
                "╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        String receivedMessage;

        System.out.println("Hello from\n" + logo);
        System.out.println(INDENTATION_LINE +
                " Hello! I'm Zuke\n" +
                " What can I do for you?\n" +
                INDENTATION_LINE);

        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            receivedMessage = userInput.nextLine();
            if (receivedMessage.equals("bye")) {
                System.out.println(INDENTATION_LINE +
                        " Bye. Hope to see you again soon!\n" +
                        INDENTATION_LINE);
                break;
            }
            System.out.println(INDENTATION_LINE +
                    receivedMessage + "\n" +
                    INDENTATION_LINE);
        }
        userInput.close();
    }
}
