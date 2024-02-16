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
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks added yet.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(" " + (i + 1) + "." + task.getStatusIcon() + " " + task.getDescription());
                    }
                }
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                if (parts.length == 2 && isNumeric(parts[1])) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task task = tasks.get(index);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    System.out.println("Invalid command format.");
                }
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                if (parts.length == 2 && isNumeric(parts[1])) {
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task task = tasks.get(index);
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    System.out.println("Invalid command format.");
                }
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }

        scanner.close();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}


