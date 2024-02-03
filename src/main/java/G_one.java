import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class G_one {
    private List<Task> tasks;

    public G_one() {
        this.tasks = new ArrayList<>();
    }

    public void start() {
        System.out.println("Hello! I'm G.one");
        System.out.println("--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.print("Whats up? ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                flag = false;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTaskList();
            } else if (userInput.startsWith("mark")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
                System.out.println("Well...." + userInput);
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }

    private void displayTaskList() {
        System.out.println("Your Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task.getStatusIcon() + " " + task.getDescription());
        }
    }

    private void markTask(String userInput) {
        // Extract the task number from the user input
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // Mark the task as done
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(String userInput) {
        // Extract the task number from the user input
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // Unmark the task
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        G_one g_one = new G_one();
        g_one.start();
    }
}
