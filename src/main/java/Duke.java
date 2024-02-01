import java.util.Scanner;

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100] ;// This is a fixed size array to store the tasks
        boolean[] taskStatus = new boolean[100] ;
        int taskCount = 0 ;

        System.out.println("__________________________________________");
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you today?");
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
            if (userInput.equalsIgnoreCase("list")){
                System.out.println("__________________________________________");
                for (int i = 0 ; i < taskCount ; i ++){
                    System.out.println(" ") + ( i + 1) +". " + tasks[i];
                }
                System.out.println("__________________________________________");
                continue ;
            }

            int taskIndex = Integer.parseInt(userInput.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                taskStatus[taskIndex] = true;
                System.out.println("__________________________________________");
                System.out.println(" Nice! I've marked this task as done:)) :");
                System.out.println("   [X] " + tasks[taskIndex]);
                System.out.println("__________________________________________");
            } else {
                System.out.println("Invalid task index.");
            }
            continue;


        // Check for the unmark command
        if (userInput.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(userInput.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                taskStatus[taskIndex] = false;
                System.out.println("__________________________________________");
                System.out.println(" Oh no, I've marked this task as not done yet:(( :");
                System.out.println("   [ ] " + tasks[taskIndex]);
                System.out.println("__________________________________________");
            } else {
                System.out.println("Task index is invalid! ");
            }
            continue;
        }

            tasks[taskCount] = "added: " + userInput ;
            taskStatus[taskCount] = false ;
            taskCount++ ;
        }
        scanner.close();
    }
}
