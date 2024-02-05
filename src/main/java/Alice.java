import java.util.Scanner;
import java.util.ArrayList;

public class Alice {
    public static void main(String[] args) {

        // Create scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Create an array to store the tasks entered by the user
        ArrayList<Task> tasks = new ArrayList<>();

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
                for (int i = 0; i < tasks.size(); i++){
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println(line);
            }
            else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                Task task = tasks.get(taskIndex);
                task.markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
                System.out.println(line);
            } else if (input.startsWith("unmark ")){
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                Task task = tasks.get(taskIndex);
                task.markAsUndone();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task);
                System.out.println(line);
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
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
