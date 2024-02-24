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

public class MyChatBot {
    public static void main(String[] args) {
        String chatBotName = "Rose";
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        printLine();
        System.out.println("Hii! I'm " + chatBotName :);
        System.out.println("What can I do for you today?");
        printLine();

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Byeeee. Hope to see you again soon!");
                printLine();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printLine();
                if (taskCount == 0) {
                    System.out.println("Your task list is empty.");
                } else {
                    System.out.println("Here are the tasks in your list for now:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                }
                printLine();
            } else if (userInput.toLowerCase().startsWith("deadline")) {
                String[] parts = userInput.split("/by", 2);
                if (parts.length > 1) {
                    tasks[taskCount] = new Deadline(parts[0].substring(9).trim(), parts[1].trim());
                    taskCount++;
                    printAddedTask(tasks, taskCount);
                } else {
                    System.out.println("Invalid deadline format. Please use 'deadline [task] /by [time]'.");
                }
            } else if (userInput.toLowerCase().startsWith("event")) {
                String[] parts = userInput.substring(6).trim().split(" ", 3); // Assuming format "event description start end"
                if (parts.length >= 3) {
                    String description = parts[0];
                    String start = parts[1];
                    String end = parts[2];
                    tasks[taskCount] = new Event(description, start, end);
                    taskCount++;
                    printAddedTask(tasks, taskCount);
                } else {
                    System.out.println("Invalid event format. Please use 'event [description] [start date/time] to [end date/time]'.");
                }
            } else if (userInput.toLowerCase().startsWith("todo")) {
                String task = userInput.substring(5).trim();
                tasks[taskCount] = new Task(task);
                taskCount++;
                printAddedTask(tasks, taskCount);
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    printLine();
                    System.out.println("Good Job! I've marked this task as done :)):");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsNotDone();
                    printLine();
                    System.out.println("Oh no ! I've marked this task as not done yet :((:");
                    System.out.println("  " + tasks[index]);
                    printLine();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                printLine();
                System.out.println("I didn't understand that. Please try again.");
                printLine();
            }
        }
        scanner.close();
    }

    private static void printAddedTask(Task[] tasks, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

