import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// Task class to represent a task
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

public class Duke {
    private static final int MAX_TASKS = 100;
    private static List<Task> tasks = new ArrayList<>();

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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index));
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(index));
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println("added: " + input);
            }

            System.out.println(line);
        }

        // Farewell message
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();
    }
}
