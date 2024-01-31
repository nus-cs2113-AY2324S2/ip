import java.util.Scanner;

public class Ma {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        String logo = "🐎  __  __    _    🐎\n"
                + "   |  \\/  |  / \\   \n"
                + "   | |\\/| | / _ \\  \n"
                + "   | |  | |/ ___ \\ \n"
                + "🐎 |_|  |_/_/   \\_\\🐎\n"
                + "What can I help you with？";

        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Ma\nWhat can I do for you?");
        printLine();

        while (true) {
            String userInput = scanner.nextLine();
            printLine();
            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else {
                System.out.println(userInput);
                printLine();
            }
        }
        scanner.close();
    }
}
