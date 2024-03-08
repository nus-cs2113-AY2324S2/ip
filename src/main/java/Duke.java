import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>(); // Using ArrayList to store tasks

        // Load tasks from file
        try {
            tasks = Storage.loadTasksFromFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
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
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks added yet.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: delete <task_number>");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    Task removedTask = tasks.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("Sorry! The description of a todo can't be empty");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split("/by", 2);
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: deadline <description> /by <date>");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new DukeException("OOPS!!! Both description and date cannot be empty.");
                    }
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    String[] parts = input.split("/from", 2);
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
                    }
                    String description = parts[0].substring(5).trim();
                    String[] eventParts = parts[1].split("/to", 2);
                    if (eventParts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: event <description> /from <start> /to <end>");
                    }
                    String from = eventParts[0].trim();
                    String to = eventParts[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new DukeException("OOPS!!! All fields (description, start, end) must be provided.");
                    }
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: mark <task_number>");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    tasks.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark")) {
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        throw new DukeException("Invalid command format. Usage: unmark <task_number>");
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new DukeException("Invalid task number.");
                    }
                    tasks.get(index).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(index));
                    System.out.println("____________________________________________________________");
                } else {
                    throw new DukeException("Invalid command");
                }

                // Save tasks to file after each change
                Storage.saveTasksToFile(tasks, FILE_PATH);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please enter a valid number.");
                System.out.println("____________________________________________________________");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Sorry! The description of a todo can't be empty");
                System.out.println("____________________________________________________________");
            } catch (IOException e) {
                System.out.println("Error saving tasks to file: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
