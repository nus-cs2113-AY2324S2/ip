import java.util.Scanner;

public class ControlPanel {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("How may I assist you?");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("list")) {
                taskManager.listTasks();
                continue;
            }
            if (input.startsWith("todo")) {
                String description = input.substring(5);
                taskManager.addTask(taskManager.new Todo("Todo", description));
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split(" /by ");
                taskManager.addTask(taskManager.new Deadline("Deadline", parts[0].substring(9), parts[1]));
            } else if (input.startsWith("event")) {
                String[] parts = input.split(" /from | /to ");
                taskManager.addTask(taskManager.new Event("Event", parts[0].substring(6), parts[1], parts[2]));
            } else if (input.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1; // Adjust for zero-based index
                    taskManager.markTaskAsDone(taskNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                }
            } else if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Unknown command. Please try again.");
            }
            System.out.println("Enter next command:");
        }
        scanner.close();
    }
}
