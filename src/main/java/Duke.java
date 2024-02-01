import java.util.Scanner;

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100] ; // This is a fixed size array to store the tasks
        int taskCount = 0 ;

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

            //Check for the list command
            if (userInput("list")){
                System.out.println("__________________________________________");
                for (int i - 0 ; i < taskCount ; i ++){
                    System.out.println(" ") + ( i + 1) +". " + tasks[i];
                }
                System.out.println("__________________________________________");
                continue ;
            }

            tasks[taskCount] = "added: " + userInput ;
            taskCount++ ;
        }
        scanner.close();
    }
}
