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

public class Duke {
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
                } else if (userInput.equalsIgnoreCase("list")) {
                    printTaskList(tasks, taskCount);
                } else if (userInput.toLowerCase().startsWith("todo")) {
                    addTask(userInput, tasks, taskCount, "todo");
                    taskCount++;
                } else if (userInput.toLowerCase().startsWith("deadline")) {
                    addTask(userInput, tasks, taskCount, "deadline");
                    taskCount++;
                } else if (userInput.toLowerCase().startsWith("event")) {
                    addTask(userInput, tasks, taskCount, "event");
                    taskCount++;
                } else if (userInput.toLowerCase().startsWith("mark")) {
                    markTask(userInput, tasks, taskCount, true);
                } else if (userInput.toLowerCase().startsWith("unmark")) {
                    markTask(userInput, tasks, taskCount, false);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }
    }

    private static void addTask(String userInput, Task[] tasks, int taskCount, String type) throws DukeException {
        String taskInfo = userInput.substring(type.length()).trim();
        if (taskInfo.isEmpty()) {
            throw new DukeException("The description of a " + type + " cannot be empty.");
        }
        switch (type) {
            case "todo":
                tasks[taskCount] = new Task(taskInfo);
                break;
            case "deadline":
                String[] partsDeadline = taskInfo.split("/by", 2);
                if (partsDeadline.length < 2 || partsDeadline[1].trim().isEmpty()) {
                    throw new DukeException("The timing for a deadline cannot be empty.");
                }
                tasks[taskCount] = new Deadline(partsDeadline[0].trim(), partsDeadline[1].trim());
                break;
            case "event":
                String[] partsEvent = taskInfo.split("/at", 2);
                if (partsEvent.length < 2 || partsEvent[1].trim().isEmpty()) {
                    throw new DukeException("The timing for an event cannot be empty.");
                }
                tasks[taskCount] = new Event(partsEvent[0].trim(), partsEvent[1].trim().split("to")[0].trim(), partsEvent[1].trim().split("to")[1].trim());
                break;
            default:
                throw new DukeException("Task type " + type + " is not recognized.");
        }
        printAddedTask(tasks, taskCount);
    }

    private static void markTask(String userInput, Task[] tasks, int taskCount, boolean isDone) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber < 0 || taskNumber >= taskCount) {
                throw new DukeException("Task number is out of bounds.");
            }
            if (isDone) {
                tasks[taskNumber].markAsDone();
            } else {
                tasks[taskNumber].markAsNotDone();
            }
            printLine();
            System.out.println("OK, I've marked this task as " + (isDone ? "done" : "not done yet") + ":");
            System.out.println(tasks[taskNumber]);
            printLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number format.");
        }
    }

    private static void printTaskList(Task[] tasks, int taskCount) {
        printLine();
        if (taskCount == 0) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        printLine();
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
