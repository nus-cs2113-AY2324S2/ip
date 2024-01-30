import java.util.Scanner;

public class Gene {
    private static String botName = "Gene";
    private Scanner scanner = new Scanner(System.in);

    private void printLineSeparation() {
        for (int i = 0; i < 29; i++) {
            System.out.print("__");
        }
        System.out.println("__");
    }

    public void startChat() {
        printLineSeparation();
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
        printLineSeparation();

        // Read user input in a loop
        while (true) {
            System.out.print("User Input: ");
            String userInput = scanner.nextLine();

            // End chat if user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            // Echo user input
            printLineSeparation();
            System.out.println(userInput);
            printLineSeparation();

        }
    }

    public void endChat() {
        printLineSeparation();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparation();
    }
}
