import java.util.Scanner;

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getType() {
        return "";
    }

    public String toString() {
        return getType() + " " + getStatusIcon() + " " + description;
    }
}

//class Todo extends Task {
//    public Todo(String description) {
//        super(description);
//    }
//
//    public String getType() {
//        return "[T]";
//    }
//}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "[T]";
    }
}


//class Deadline extends Task {
//    private String by;
//
//    public Deadline(String description, String by) {
//        super(description);
//        this.by = by;
//    }
//
//    public String getType() {
//        return "[D]";
//    }
//
//    public String toString() {
//        return super.toString() + " (by: " + by + ")";
//    }
//}

class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getType() {
        return "[D]";
    }
}


//class Event extends Task {
//    private String from;
//    private String to;
//
//    public Event(String description, String from, String to) {
//        super(description);
//        this.from = from;
//        this.to = to;
//    }
//
//    public String getType() {
//        return "[E]";
//    }
//
//    public String toString() {
//        return getType() + getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
//    }
//}

class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getType() {
        return "[E]";
    }

    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


public class Joey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Joey");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String userCommand = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (userCommand.equalsIgnoreCase("bye")) {
                System.out.println(" bye bye, take care:)!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userCommand.equalsIgnoreCase("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    Task task = tasks[i];
                    System.out.println(" " + (i + 1) + "." + task.getType() + "[" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                }
                System.out.println("____________________________________________________________");
            } else if (userCommand.startsWith("todo")) {
                String description = userCommand.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userCommand.startsWith("deadline")) {
                String[] parts = userCommand.substring(9).split("/by");
                String description = parts[0].trim();
                String by = parts[1].trim();
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userCommand.startsWith("event")) {
                String[] parts = userCommand.substring(6).split("/from|/to");
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = tasks[taskIndex];
                    task.markDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                    System.out.println("____________________________________________________________");
                }
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = tasks[taskIndex];
                    task.markNotDone();
                    System.out.println(" okay, I have marked this task as not done yet:");
                    System.out.println("   [" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println(" Task not found. Please enter a valid task number OR enter 'list' to view your current list:)");
                    System.out.println("____________________________________________________________");
                }
            } else {
                tasks[taskCount] = new Task(userCommand);
                taskCount++;
                System.out.println(" added: " + userCommand);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
