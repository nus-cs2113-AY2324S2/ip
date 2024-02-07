import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Mark done task with X
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

        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(line);

            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                Task task = tasks.get(taskIndex);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                Task task = tasks.get(taskIndex);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            } else {
                addTask(input);
            }

            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();
    }

    private static void addTask(String description) {
        if (tasks.size() < MAX_TASKS) {
            Task newTask = new Task(description);
            tasks.add(newTask);
            System.out.println("added: " + description);
        } else {
            System.out.println("Task list is full!");
        }
    }
}
