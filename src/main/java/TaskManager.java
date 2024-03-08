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
     * This function marks the task based on the serial number of task provided by the user
     * @param taskNumberStr the serial number of the task entered by user
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
     * This method adds a task of type Todo along with the description provided, and then saves the task
     * @param description
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
     * This method adds a task of Deadline type along with its description and deadline, then saves the task
     * @param descriptionAndBy the string containing description and deadline
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
     * This method adds a task of event type along with its description and timings, then saves the task
     * @param descriptionAndTime string containing description and duration
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
     * This method deletes the task based on its serial number in the list
     * @param taskNumberStr The serial number of task to be deleted
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

    /**
     * This method displays the task list for the user
     */
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
     * This method searches relevant tasks based on the keyword provided
     * @param keyword the keyword based on which the search is initated
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
