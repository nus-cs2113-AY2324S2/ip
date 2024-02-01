import java.util.Scanner;

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);

        System.out.println("__________________________________________");
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");

        // Input loop
        while (true) {
            String userInput = scanner.nextLine().trim(); 

            // Echo user input
            System.out.println("__________________________________________");
            System.out.println(" " + userInput);
            System.out.println("__________________________________________");

            // Check for the bye command to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("__________________________________________");
                System.out.println(" Byeeee. Hope to see you again soon!");
                System.out.println("__________________________________________");
                break; // Exit the loop
            }
        }


        scanner.close();
    }
}
