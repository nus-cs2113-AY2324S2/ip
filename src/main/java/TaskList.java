import java.util.ArrayList;

/**
 * Represents a list of tasks in the application. This class manages the tasks,
 * including adding, deleting, marking tasks as done or not done, listing all tasks,
 * and finding tasks by keywords.
 */
public class TaskList {
    private final ArrayList<Task> tasks; // A list of tasks.

    /**
     * Constructs a TaskList with a specified list of tasks.
     *
     * @param tasks An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list based on the user input.
     *
     * @param userInput The full command input from the user for creating a task.
     * @throws HandleException If the task cannot be created due to invalid input.
     */
    public void addTask(String userInput) throws HandleException {
        Task task = TaskFactory.createTask(userInput);
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done based on its index in the task list.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        } else {
            System.out.println("Task with given index does not exist.");
        }
    }

    /**
     * Marks a task as not done based on its index in the task list.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + task);
        } else {
            System.out.println("Task with given index does not exist.");
        }
    }

    /**
     * Deletes a task from the task list based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws HandleException If the specified index is invalid.
     */
    public void deleteTask(int taskIndex) throws HandleException {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new HandleException("OOPS!!! The task number is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex - 1);
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Lists all tasks in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Finds and lists all tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void findTask(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                System.out.println((i + 1) + "." + task);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}
