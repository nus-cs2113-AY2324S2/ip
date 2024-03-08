import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hailey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        TaskFactory factory = new TaskFactory(); // Instantiate TaskFactory

        System.out.println("Hello! I'm Hailey");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine().trim();

            try {
                if ("bye".equalsIgnoreCase(input)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if ("list".equalsIgnoreCase(input)) {
                    listTasks(tasks);
                } else if (input.startsWith("todo")) {
                    validateInput(input.substring(5).trim());
                    addTask(tasks, input.substring(5).trim(), "T", factory);
                } else if (input.startsWith("deadline")) {
                    validateInput(input.substring(9).trim());
                    addTaskWithDateTime(tasks, input.substring(9).trim(), "D", " /by ", "", factory);
                } else if (input.startsWith("event")) {
                    validateInput(input.substring(6).trim());
                    addTaskWithDateTime(tasks, input.substring(6).trim(), "E", " /from ", " /to ", factory);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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

    private static void addTask(List<Task> tasks, String description, String type, TaskFactory factory) {
        tasks.add(factory.createTask(type, description));
        printAddedTask(tasks);
    }

    private static void addTaskWithDateTime(List<Task> tasks, String input, String type, String delimiter1, String delimiter2, TaskFactory factory) throws DukeException {
        String[] parts = input.split(delimiter1, 2);
        if (parts.length == 2) {
            tasks.add(factory.createTaskWithDateTime(type, parts[0].trim(), parts[1].trim(), delimiter2));
            printAddedTask(tasks);
        } else {
            throw new DukeException("OOPS!!! Invalid command format.");
        }
    }

    private static void validateInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    private static void printAddedTask(List<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s%n", tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}