import java.util.Scanner;

public class Dul {
    public static final int maximumTASKS = 100;
    public static Task[] tasks = new Task[maximumTASKS];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| |\n"
                + "| | | | | | | |\n"
                + "| |_| | |_| | |\n"
                + "|____/ \\__,_|_|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        String guyInput = "";
        Scanner in = new Scanner(System.in);

        while (!guyInput.equals("bye")) {
            guyInput = in.nextLine();
            listInput(guyInput);
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    public static void listInput(String input) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
            case "list":
                listTasks();
                break;
            case "mark":
                markTaskDone(Integer.parseInt(command[1]) - 1);
                break;
            case "unmark":
                markTaskNotDone(Integer.parseInt(command[1]) - 1);
                break;
            case "todo":
                addTodoTask(command[1]);
                break;
            case "deadline":
                addDeadlineTask(command[1]);
                break;
            case "event":
                addEventTask(command[1]);
                break;
            case "bye":
                break;
            default:
                addTask(command[0]);
                break;
        }
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public static void addTask(String taskType) {
        tasks[taskCount] = new Task(taskType);
        taskCount++;
        System.out.println("added: " + taskType);
    }

    public static void addTodoTask(String taskType) {
        tasks[taskCount] = new TodoTask(taskType);
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void addDeadlineTask(String taskType) {
        String[] parts = taskType.split(" /by ", 2);
        String description = parts[0];
        String by = parts[1];
        tasks[taskCount] = new DeadlineTask(description, by);
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void addEventTask(String taskType) {
        String[] parts = taskType.split(" /from ", 2);
        String[] eventParts = parts[1].split(" /to ", 2);
        String description = parts[0];
        String from = eventParts[0];
        String to = eventParts[1];
        tasks[taskCount] = new EventTask(description, from, to);
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void markTaskDone(int index) {
        tasks[index].markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index].toString());
    }

    public static void markTaskNotDone(int index) {
        tasks[index].markNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index].toString());
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
