import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Marks done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm [Sparky]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");

            // Handles the "bye" command
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soooon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }

            // Handles the "list" command
            if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                    }
                }
                System.out.println("____________________________________________________________");
                continue; // Skip the rest of the loop and start over
            }

            // Handles the "mark" command
            if (input.startsWith("mark")) {
                String[] parts = input.split(" ");

                if (parts.length != 2) {
                    System.out.println("Invalid command format.");
                } else {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                }

                System.out.println("____________________________________________________________");
                continue;
            }

            // Handles the "unmark" command
            if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");

                if (parts.length != 2) {
                    System.out.println("Invalid command format.");
                } else {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                }

                System.out.println("____________________________________________________________");
                continue;
            }

            // Default case for adding tasks
            if (taskCount < tasks.length) { // Checks if there's space for new tasks
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            } else {
                System.out.println("Task limit reached. Can't add more tasks.");
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}

    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm [Sparky]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");

            // Handle the "bye" command
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soooon!");
                System.out.println("____________________________________________________________");
                break; // Exit the loop
            }

            // Handle the "list" command
            if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                    }
                }
                System.out.println("____________________________________________________________");
                continue; // Skip the rest of the loop and start over
            }

            // Handle the "mark" command
            if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    System.out.println("Invalid command format.");
                }
                System.out.println("____________________________________________________________");
                continue; // Skip the rest of the loop and start over
            }

            // Handle the "unmark" command
            if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < taskCount) {
                        tasks[index].markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    System.out.println("Invalid command format.");
                }
                System.out.println("____________________________________________________________");
                continue; // Skip the rest of the loop and start over
            }

            // Default case for adding tasks
            if (taskCount < tasks.length) { // Check if there's space for new tasks
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            } else {
                System.out.println("Task limit reached. Can't add more tasks.");
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
