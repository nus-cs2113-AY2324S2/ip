import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100; // Maximum number of tasks
    private static String[] tasks = new String[MAX_TASKS]; // Array to store tasks
    private static int taskCount = 0; // Counter for the number of tasks

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        
        // Start-up message
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
        
        // Processing commands
        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);
            
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                if (taskCount < MAX_TASKS) {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Task list is full!");
                }
            }
            
            System.out.println(line);
        }
        
        // Farewell message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        
        scanner.close();
    }
}
