import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine().trim();

            if ("bye".equalsIgnoreCase(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks(tasks);
            } else if (input.startsWith("todo")) {
                addTask(tasks, input.substring(5).trim());
            } else if (input.startsWith("deadline")) {
                addTaskWithDateTime(tasks, input.substring(9).trim(), "D", " /by ");
            } else if (input.startsWith("event")) {
                addTaskWithDateTime(tasks, input.substring(6).trim(), "E", " /from ");
            } else {
                System.out.println("Invalid command.");
            }
        }

        scanner.close();
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf(" %d.%s%n", i + 1, tasks.get(i));
            }
        }
    }

    private static void addTask(List<Task> tasks, String description) {
        TaskFactory factory = new TaskFactory(); // Instantiate TaskFactory
        tasks.add(factory.createTask("T", description));
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s%n", tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addTaskWithDateTime(List<Task> tasks, String input, String type, String delimiter) {
        String[] parts = input.split(delimiter, 2);
        if (parts.length == 2) {
            TaskFactory factory = new TaskFactory(); // Instantiate TaskFactory
            tasks.add(factory.createTaskWithDateTime(type, parts[0].trim(), parts[1].trim(), ""));
            System.out.println("Got it. I've added this task:");
            System.out.printf("  %s%n", tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid command format.");
        }
    }
}

