import java.util.Scanner;

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

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + start + " to: " + end + ")";
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you today?");
        printLine();

        while (true) {
            String userInput = scanner.nextLine().trim();

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    printLine();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    break;
                } else if (userInput.startsWith("todo")) {
                    handleToDo(userInput, tasks, taskCount);
                    taskCount++;
                } else if (userInput.startsWith("deadline")) {
                    handleDeadline(userInput, tasks, taskCount);
                    taskCount++;
                } else if (userInput.startsWith("event")) {
                    handleEvent(userInput, tasks, taskCount);
                    taskCount++;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
        scanner.close();
    }

    private static void handleToDo(String userInput, Task[] tasks, int taskCount) throws DukeException {
        String description = userInput.stripLeading().replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo can't be empty.");
        }
        tasks[taskCount] = new Task(description);
        printAddedTask(tasks, taskCount);
    }

    private static void handleDeadline(String userInput, Task[] tasks, int taskCount) throws DukeException {
        String[] parts = userInput.split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("The timing for a deadline can't be empty.");
        }
        tasks[taskCount] = new Deadline(parts[0].replaceFirst("deadline", "").trim(), parts[1].trim());
        printAddedTask(tasks, taskCount);
    }

    private static void handleEvent(String userInput, Task[] tasks, int taskCount) throws DukeException {
        String[] parts = userInput.split("/at", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("The timing for an event can't be empty.");
        }
        String description = parts[0].replaceFirst("event", "").trim();
        String[] times = parts[1].trim().split("to", 2);
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new DukeException("Both start and end times for an event must be given.");
        }
        tasks[taskCount] = new Event(description, times[0].trim(), times[1].trim());
        printAddedTask(tasks, taskCount);
    }
    private static void printAddedTask(Task[] tasks, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount]);
        System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
