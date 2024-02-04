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
                    System.out.println(" " + (i + 1) + ".[" + (task.isDone() ? "X" : " ") + "] " + task.getDescription());
                }
                System.out.println("____________________________________________________________");
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = tasks[taskIndex];
                    task.markDone();
                    System.out.println("____________________________________________________________");
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
                    System.out.println("____________________________________________________________");
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
