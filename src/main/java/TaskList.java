import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    // List of tasks
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        Storage.loadTasksFromFile(tasks);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a todo task to the list.
     *
     * @param description The description of the todo task.
     * @throws DukeException If the description is empty.
     */
    public void addTodoTask(String description) throws DukeException {
        tasks.add(new Todo(description));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param description The description of the deadline task.
     * @throws DukeException If the description or deadline is empty, or if the format is incorrect.
     */
    public void addDeadlineTask(String description) throws DukeException {
        String[] split = description.split(" /by ");
        if (split.length != 2) {
            throw new DukeException("The description of a deadline task should be followed by '/by <deadline>'.");
        }
        tasks.add(new Deadline(split[0], split[1]));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    /**
     * Adds an event task to the list.
     *
     * @param description The description of the event task.
     * @throws DukeException If the description or timing is empty, or if the format is incorrect.
     */
    public void addEventTask(String description) throws DukeException {
        String[] split = description.split(" /from | /to ");
        if (split.length != 3) {
            throw new DukeException("The description of an event task should be followed by '/from <start> /to <end>'.");
        }
        tasks.add(new Event(split[0], split[1], split[2]));
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    /**
     * Displays all tasks in the list.
     *
     * @throws DukeException If there are no tasks in the list.
     */
    public void displayTasks() throws DukeException {
        if (tasks.isEmpty()) {
            ui.showLine();
            System.out.println("Ehhh, you got no tasks to do leh. Try adding some! :(");
            ui.showLine();
            return;
        }
        ui.showLine();
        System.out.println("LaiLaiLai, Here are the tasks in your list:");
        // Display all tasks in the list
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        ui.showLine();
    }

    /**
     * Marks a task as done.
     *
     * @param userInput The user input specifying the task number.
     * @throws DukeException If the task number is invalid.
     */
    public void markTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                // Mark the task as done
                tasks.get(taskIndex).markAsDone();
                ui.showLine();
                System.out.println("Nice one lah! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
                ui.showLine();
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param userInput The user input specifying the task number.
     * @throws DukeException If the task number is invalid.
     */
    public void unmarkTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                // Mark the task as not done
                tasks.get(taskIndex).markAsNotDone();
                ui.showLine();
                System.out.println("OK can, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
                ui.showLine();
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param userInput The user input specifying the task number.
     * @throws DukeException If the task number is invalid.
     */
    public void deleteTask(String userInput) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                ui.showLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + tasks.get(taskIndex));
                // Remove the task from the list
                tasks.remove(taskIndex);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
                Storage.saveTasksToFile(tasks);
            } else {
                throw new DukeException("Invalid task number provided.");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    /**
     * Finds tasks matching a keyword in the description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        // Search for matching tasks
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showLine();
            System.out.println("No tasks found matching the keyword.");
            ui.showLine();
        } else {
            ui.showLine();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
            ui.showLine();
        }
    }
}
