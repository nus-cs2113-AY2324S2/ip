import java.util.Scanner;

public class Xavier {
    public static final String LINE = "_________________________________________________________________";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        printWelcomeMessage();

        while (true) {
            String command = input.nextLine();
            System.out.println(LINE);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            }
            else {
                taskManager.handleCommand(command);
            }
            System.out.println(LINE);
        }
    }

    private static void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + "Xavier");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
}

