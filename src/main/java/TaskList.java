import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasksList;

    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    private static void markTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static void unmarkTaskAsDone(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            tasksList.get(taskIndex - 1).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasksList.get(taskIndex - 1));
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasksList.size();
    }
    private static void deleteTask(String command) {
        int taskIndex = extractTaskIndex(command);
        if (isValidTaskIndex(taskIndex)) {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasksList.get(taskIndex - 1));
            tasksList.remove(taskIndex - 1);
            printTaskCountMessage();
        } else {
            printInvalidTaskIndexMessage();
        }
        System.out.println(" ");
    }

    public static void printTaskList() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + ". " + tasksList.get(i));
        }
        System.out.println(" ");
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    // Other task-related methods
}