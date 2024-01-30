import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}

public class Duke {
    public static void main(String[] args) {
        // Display a greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100]; // Array to store tasks
        int taskCount = 0;

        // Continue reading user input until "bye" is entered
        while (true) {
            userInput = scanner.nextLine();

            // Echo the user's command
            System.out.println("____________________________________________________________");

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(" " + (i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                    }
                }

            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());

            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex].getStatusIcon() + " " + tasks[taskIndex].getDescription());

            } else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("added: " + userInput);
            }
            System.out.println("____________________________________________________________");
        }
        // Close the Scanner
        scanner.close();
    }
}
