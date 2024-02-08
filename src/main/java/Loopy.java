import java.util.ArrayList;
import java.util.Scanner;

public class Loopy {
    private static ArrayList<Task> tasks = new ArrayList<>(); // Create task list array

    public static void main(String[] args) {
        String logo = " __                \n"
                + "| |    ____ ____ ____ \n"
                + "| |   | |-| ||-| | _ \\\n"
                + "| |___| |_| ||_| | __/\n"
                + "|____/ \\__/ \\__/_| | \n";
        System.out.println("Hello! I'm Loopy!\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner taskScanner = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            String task = taskScanner.nextLine();
            processTask(task);
            if (task.equals("bye")) {
                System.out.println("Bye! Hope to see you again.\n");
                break;
            }
        }
    }

    private static void processTask(String task) {
        if (task.equals("list")) {
            displayTaskList();
        } else if (task.startsWith("mark ")) {
            markTaskAsDone(task);
        } else if (task.startsWith("unmark ")) {
            markTaskAsUndone(task);
        } else {
            addTask(new Task(task)); // Create Task object and add it to the list
        }
    }

    private static void addTask(Task task) {
        if (!task.getDescription().equals("bye")) {
            tasks.add(task);
            System.out.println("added: " + task.getDescription());
        }
    }
//NOT DISPLAYING LIST PROPERLY
    private static void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1) + ". " + currentTask);
        }
    }

    private static void markTaskAsDone(String task) {
        int taskIndex = Integer.parseInt(task.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + currentTask);
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
        }
    }

    private static void markTaskAsUndone(String task) {
        int taskIndex = Integer.parseInt(task.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task currentTask = tasks.get(taskIndex);
            currentTask.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + currentTask);
        } else {
            System.out.println("Invalid task index. Please provide a valid index.");
        }
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
