import java.util.ArrayList;
import java.util.List;


/**
 * The TaskManager class manages tasks including adding, marking, unmarking, deleting, listing, and finding tasks.
 */
public class TaskManager {
    private List<Task> tasks;

    /**
     * Constructs a TaskManager object and initializes a list of tasks.
     * Loads tasks from a file if needed.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile(); // Load tasks from file if needed
    }

    /**
     * Marks the task based on the serial number of the task provided by the user.
     * @param taskNumberStr The serial number of the task entered by the user
     */
    public void markTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
            saveTasksToFile(); // Call saveTasksToFile after marking a task as done
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Unmarks the task based on the serial number of the task provided by the user.
     * @param taskNumberStr The serial number of the task entered by the user
     */
    public void unmarkTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task task = tasks.get(taskNumber);
            task.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
            saveTasksToFile(); // Call saveTasksToFile after marking a task as undone
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Adds a todo task with the provided description and saves the task.
     * @param description The description of the todo task
     */
    public void addTodoTask(String description) {
        Task task = new TodoTask(description);
        tasks.add(task);
        System.out.println("Alright buddy, Added that:");
        System.out.println("  " + task);
        printTaskCount();
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
    }

    /**
     * Adds a deadline task with the provided description and deadline, then saves the task.
     * @param descriptionAndBy The description and deadline of the deadline task
     */
    public void addDeadlineTask(String descriptionAndBy) {
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
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
    }

    /**
     * Adds an event task with the provided description and timings, then saves the task.
     * @param descriptionAndTime The description and timings of the event task
     */
    public void addEventTask(String descriptionAndTime) {
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
        saveTasksToFile(); // Call saveTasksToFile after adding a new task
    }

    /**
     * Deletes the task based on its serial number in the list.
     * @param taskNumberStr The serial number of the task to be deleted
     */
    public void deleteTask(String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            printTaskCount();
            saveTasksToFile(); // Call saveTasksToFile after deleting a task
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void displayTaskList() {
        System.out.println("Your Task List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    private void printTaskCount() {
        System.out.println("Ok busy guy, Now you have " + tasks.size() + " task(s) in the list");
    }

    private void loadTasksFromFile() {
        List<Task> loadedTasks = Storage.loadTasksFromFile();
        tasks.addAll(loadedTasks);
    }

    private void saveTasksToFile() {
        Storage.saveTasksToFile(tasks);
    }
    /**
     * Searches relevant tasks based on the keyword provided.
     * @param keyword The keyword for searching tasks
     */
    public void findTaskByKeyword(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 0;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((++count) + ". " + task);
            }
        }
        if (count == 0) {
            System.out.println("No matching tasks found.");
        }
    }
}
