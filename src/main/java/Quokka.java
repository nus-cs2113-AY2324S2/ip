import Quokka.exceptions.QuokkaException;
import Quokka.tasks.Deadline;
import Quokka.tasks.Event;
import Quokka.tasks.Task;
import Quokka.tasks.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quokka {
    private static final int MAX_TASKS = 100;
    private static List<Task> tasks = new ArrayList<>();

    private static void addTask(Task newTask) {
        try {
            if (tasks.size() >= MAX_TASKS) {
                throw new QuokkaException("Sorry, the task list is full. You cannot add more tasks.");
            }
            tasks.add(newTask);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + newTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }

    private static void deleteTask(int taskIndex) {
        try {
            if (taskIndex < 1 || taskIndex > tasks.size()) {
                throw new QuokkaException("Invalid task index. Please provide a valid task index to delete");
            }

            Task deletedTask = tasks.remove(taskIndex - 1);

            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + deletedTask);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list");
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
        }
    }

    private static Todo parseTodoTask(String userInput) {
        try {
            String description = userInput.substring("todo".length()).trim();
            if (description.isEmpty()) {
                throw new QuokkaException("Please provide a description for the todo task.");
            }
            return new Todo(description);
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }

    private static Deadline parseDeadlineTask(String userInput) {
        try {
            String[] parts = userInput.split("/by", 2);
            if (parts.length == 2) {
                String description = parts[0].substring("deadline".length()).trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new QuokkaException("Please provide both description and deadline for the task.");
                }
                return new Deadline(description, by);
            } else {
                throw new QuokkaException("Invalid deadline format. Please use: deadline [description] /by [date/time]");
            }
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
            return null;
        }
    }

    private static Event parseEventTask(String userInput) {
        try {
            String[] parts = userInput.split("/from", 2);
            if (parts.length == 2) {
                String description = parts[0].substring("event".length()).trim();
                String[] dateTimes = parts[1].split("/to", 2);
                if (dateTimes.length == 2) {
                    String from = dateTimes[0].trim();
                    String to = dateTimes[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new QuokkaException("Please provide description, start time, and end time for the event task.");
                    }
                    return new Event(description, from, to);
                } else {
                    throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
                }
            } else {
                throw new QuokkaException("Invalid event format. Please use: event [description] /from [start] /to [end]");
            }
        } catch (QuokkaException e) {
            System.out.println("    Error: " + e.getMessage());
            return null;
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks added yet.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void markTaskAsDone(String userInput) {
        try {
            updateTaskStatus(userInput, true, "Nice! I've marked this task as done:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

    private static void markTaskAsNotDone(String userInput) {
        try {
            updateTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
        } catch (QuokkaException e) {
            System.out.println("     Error: " + e.getMessage());
        }
    }

    private static void updateTaskStatus(String userInput, boolean newStatus, String statusMessage) {
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).setStatus(newStatus);
                    System.out.println("    " + statusMessage);
                    System.out.println("      " + tasks.get(taskIndex));
                } else {
                    throw new QuokkaException("Invalid task index.");
                }
            } else {
                throw new QuokkaException("Please provide a valid task index to mark as done or not done.");
            }
        } catch (NumberFormatException e) {
            System.out.println("     Error: Invalid task index format.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");

        while (true) {
            // Read user input
            String userInput = scanner.nextLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }

            // Check if the user wants to list tasks
            if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                // Handle different types of tasks
                if (userInput.toLowerCase().startsWith("mark ")) {
                    markTaskAsDone(userInput);
                } else if (userInput.toLowerCase().startsWith("unmark ")) {
                    markTaskAsNotDone(userInput);
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    addTask(parseTodoTask(userInput));
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    addTask(parseDeadlineTask(userInput));
                } else if (userInput.toLowerCase().startsWith("event")) {
                    addTask(parseEventTask(userInput));
                } else if (userInput.toLowerCase().startsWith("delete ")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length == 2) {
                        try {
                            int taskIndex = Integer.parseInt(parts[1]);
                            deleteTask(taskIndex);
                        } catch (NumberFormatException e) {
                            System.out.println("    Invaid task index format");
                        }
                    } else {
                        System.out.println("    Please provide a valid task index to delete");
                    }
                } else {
                    System.out.println("    I'm sorry, I don't understand that command.");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
