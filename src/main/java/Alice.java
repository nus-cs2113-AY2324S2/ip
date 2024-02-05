import java.util.Scanner;

public class Alice {
    public static void main(String[] args) {

        // Create scanner object to read input
        Scanner scanner = new Scanner(System.in);
        // Create an array to store the tasks entered by the user
        String[] tasks = new String[100];
        // Create a variable to store the number of tasks so far
        int taskNum = 0;
        // Create string to store line separating responses
        String line = "____________________________________________________________";

        // Add starting statement
        System.out.println(line);
        System.out.println("Hello! I'm Alice");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Use a condition that directly checks input
        while (true) {
            // Read the user input here
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")){
                System.out.println(line);

                // Print out the whole list of tasks numbered
                for (int i = 0; i < taskNum; i++){
                    System.out.println((i + 1) + ". "+ tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[taskNum] = input;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
                taskNum++;
            }
        }
        // Close the scanner after exiting the loop
        scanner.close();
    }
}
