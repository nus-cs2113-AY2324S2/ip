import java.util.Scanner;
import java.util.ArrayList;

public class Orion {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            if ("bye".equalsIgnoreCase(input)) {
                printGoodbyeMessage();
                break;
            }
            try {
                processInput(input);
            } catch (OrionException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void processInput(String input) throws OrionException {
        if (input.startsWith("todo")) {
            handleTodo(input);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input);
        } else if (input.startsWith("event")) {
            handleEvent(input);
        } else if ("list".equals(input)) {
            listTasks();
        } else {
            throw new OrionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleTodo(String input) throws OrionException {
        if (input.trim().equals("todo")) {
            throw new OrionException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = input.substring(5);
        Todo newTodo = new Todo(taskDescription);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:\n  " + newTodo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input) throws OrionException {
        String[] parts = input.substring(9).split(" /by ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OrionException("OOPS!!! The deadline time cannot be empty.");
        }
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:\n  " + newDeadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input) throws OrionException {
        String[] parts = input.substring(6).split(" /at ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OrionException("OOPS!!! The event time cannot be empty.");
        }
        Event newEvent = new Event(parts[0], parts[1]);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:\n  " + newEvent);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void printWelcomeMessage() {
        System.out.println(Constants.LOGO);
        System.out.println("Hello! I'm Orion\nWhat can I do for you?");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
