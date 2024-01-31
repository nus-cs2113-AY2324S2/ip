import java.util.Scanner;
import java.util.ArrayList;

public class Ma {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        ArrayList<String> list = new ArrayList<>();

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
            } else if ("list".equalsIgnoreCase(userInput)) {
                printLine();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(" " + (i + 1) + ". " + list.get(i));
                }
                printLine();
            } else {
                list.add(userInput);
                printLine();
                System.out.println("added: " + userInput);
                printLine();
            }
        }
        scanner.close();
    }
}
