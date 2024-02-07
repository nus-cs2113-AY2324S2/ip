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
            } else {
                processUserInput(userInput);
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private void processUserInput(String userInput) {
        if (userInput.startsWith("todo")) {
            addTodoTask(userInput.substring(5).trim());
        } else if (userInput.startsWith("deadline")) {
            addDeadlineTask(userInput.substring(9).trim());
        } else if (userInput.startsWith("event")) {
            addEventTask(userInput.substring(6).trim());
        } else if (userInput.startsWith("mark")) {
            markTask(userInput.substring(5).trim());
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(userInput.substring(7).trim());
        } else if (userInput.equalsIgnoreCase("list")) {
            displayTaskList();
        } else {
            System.out.println("Invalid command.");
        }
    }
    private void markTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void addTodoTask(String description) {
        Task task = new TodoTask(description);
        tasks.add(task);
        System.out.println("Alright buddy, Added that::");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void addDeadlineTask(String descriptionAndBy) {
        String[] parts = descriptionAndBy.split(" /by ");
        if (parts.length != 2) {
            System.out.println("Invalid deadline format.");
            return;
        }
        Task task = new DeadlineTask(parts[0], parts[1]);
        tasks.add(task);
        System.out.println("Alright buddy, Added this:");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void addEventTask(String descriptionAndTime) {
        String[] parts = descriptionAndTime.split(" /from ");
        if (parts.length != 2) {
            System.out.println("Invalid event format.");
            return;
        }
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            System.out.println("Invalid event format.");
            return;
        }
        Task task = new EventTask(parts[0], timeParts[0], timeParts[1]);
        tasks.add(task);
        System.out.println("Alright buddy, Added this:");
        System.out.println("  " + task);
        printTaskCount();
    }

    private void displayTaskList() {
        System.out.println("Your Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    private void printTaskCount() {
        System.out.println("Ok busy guy, Now you have " + tasks.size() + " tasks in the list");
    }

    public static void main(String[] args) {
        G_one g_one = new G_one();
        g_one.start();
    }
}
