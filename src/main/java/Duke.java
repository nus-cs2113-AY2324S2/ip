import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        
        // Start-up message
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        
        // Command echo loop
        while (true) {
            String input = scanner.nextLine();
            
            // Check for the 'bye' command to terminate the program
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            
            // Echo the input back to the user
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        
        // Farewell message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        
        scanner.close();
    }
}
