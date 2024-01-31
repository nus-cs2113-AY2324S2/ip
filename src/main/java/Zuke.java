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
        int numberOfTask = 0;
        String[] tasks = new String[100];

        System.out.println("Hello from\n" + logo);
        System.out.println(INDENTATION_LINE +
                "Hello! I'm Zuke\n" +
                "What can I do for you?\n" +
                INDENTATION_LINE);

        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            receivedMessage = userInput.nextLine();
            System.out.println(INDENTATION_LINE);

            if (receivedMessage.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" +
                        INDENTATION_LINE);
                break;
            }

            if (receivedMessage.equals("list")) {
                for (int i = 0; i < numberOfTask; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
                System.out.println(INDENTATION_LINE);
            } else {
                tasks[numberOfTask] = receivedMessage;
                numberOfTask++;
                System.out.println("added: " +
                        receivedMessage + "\n" +
                        INDENTATION_LINE);
            }
        }
        userInput.close();
    }
}
