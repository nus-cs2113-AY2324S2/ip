import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {

        // Create scanner object to read input
        Scanner scanner = new Scanner(System.in);
        // Create string to store line separating responses
        String line = "____________________________________________________________";

        // Add starting statement
        System.out.println(line);
        System.out.println("Hello! I'm Alice");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Declare the input variable
        String input;
        // Use a condition that directly checks input
        while (true) {
            // Read the user input here
            input = scanner.nextLine();
            if (input.equals("bye")) {
                // Exit loop if input is "bye"
                break;
            }
            System.out.println(line);
            System.out.println(input); // Echo user input after reading it
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        // Close the scanner after exiting the loop
        scanner.close();
    }
}
