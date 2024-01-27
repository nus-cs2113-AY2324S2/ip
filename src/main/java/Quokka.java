import java.util.Scanner;

public class Quokka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Echo the user's command
            System.out.println("    " + userInput);

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}
