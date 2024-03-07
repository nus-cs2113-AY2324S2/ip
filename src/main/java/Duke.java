import java.util.Scanner;

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

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soooon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (taskCount == 0) {
                        System.out.println("No tasks added yet.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + "." + tasks[i]);
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: mark <task_number>");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new DukeException("Invalid task number.");
                    }
                    tasks[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: unmark <task_number>");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new DukeException("Invalid task number.");
                    }
                    tasks[index].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("Sorry! The description of a todo can't be empty");
                    }
                    tasks[taskCount] = new Todo(description);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split("/by");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: deadline <description> /by <date>");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new DukeException("OOPS!!! Both description and date cannot be empty.");
                    }
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    String[] parts = input.split("/from");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
                    }
                    String[] eventParts = parts[1].split("/to");
                    if (eventParts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
                    }
                    String description = parts[0].substring(5).trim();
                    String from = eventParts[0].trim();
                    String to = eventParts[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("OOPS!!! All fields (description, start, end) must be provided.");
                    }
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new DukeException("Invalid command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please enter a valid number.");
                System.out.println("____________________________________________________________");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Sorry! The description of a todo can't be empty");
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
