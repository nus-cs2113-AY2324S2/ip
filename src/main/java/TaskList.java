import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The TaskList class represents an ArrayList of tasks and its methods.
 */
public class TaskList {
    private static final String LINE = "____________________________________________________________";
    private static Ui ui = new Ui();
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList using ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a task to the TaskList.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList based on its index.
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }


    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    /**
     * Retrieves a task from the TaskList based on its index.
     * @param index Index of the task to be retrieved.
     * @return Task based on its index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task based on the provided command.
     * @param command String input representing the task to add.
     * @throws IllegalArgumentException if description is empty.
     * @throws NoSuchElementException if invalid format.
     */
    public void addNewTask(String command) {
        String[] commandParts = command.split(" ");
        String tasking = commandParts[0];

        switch (tasking) {
            case "todo":
                try {
                    tasks.add(new Todo(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a todo cannot be empty.\n" + LINE);
                    return;
                }
                break;
            case "deadline":
                try {
                    tasks.add(new Deadline(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a deadline cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: deadline <task> /by <time>.\n" + LINE);
                    return;
                }
                break;
            case "event":
                try {
                    tasks.add(new Event(command));
                } catch (IllegalArgumentException e) {
                    ui.println(LINE + "\nOOPS!!! The description of a event cannot be empty.\n" + LINE);
                    return;
                } catch (NoSuchElementException e) {
                    ui.println(LINE + "\nOOPS!!! Try this format: event <task> /from <start_time> /to <end_time>.\n" + LINE);
                    return;
                }
                break;
            default:
                ui.println(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
                return;
        }
        tasks.get(tasks.size() - 1).printTask(tasks.size());
    }

    /**
     * Marks or unmarks a task.
     * @param command String input representing the task to mark or unmark.
     * @param mark Boolean indicating whether the task should be marked (true) or unmarked (false).
     */
    public void markTask(String command, boolean mark) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            if (mark) {
                task.markAsDone();
                ui.println(LINE + "\nNice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                ui.println(LINE + "\nOK, I've marked this task as not done yet:");
            }
            ui.println("   " + task.getTaskType() + task.getTaskMark() +
                        " " + task.description + "\n" + LINE );
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    /**
     * Lists all current tasks.
     */
    public void listTasks() {
        ui.println(LINE + "\nHere are the tasks in your list:");
        //Edge case: If list empty
        if (tasks.isEmpty()) {
            ui.println("Nothing added here....");
        }
        for (int i = 0; i < tasks.size(); i++) {
            ui.println("  "+ (i + 1) + "." + tasks.get(i).toString());
        }
        ui.println(LINE);
    }

    /**
     * Deletes a task.
     * @param command String input representing the task to delete.
     */
    public void deleteTask(String command) {
        int indexToDelete = Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        if (indexToDelete >= 0 && indexToDelete < tasks.size()) {
            ui.println(LINE + "\nNoted. I've removed this task:");
            ui.println("   "+ tasks.get(indexToDelete).toString());
            tasks.remove(indexToDelete);
            ui.println("Now you have " + tasks.size() + " tasks in the list.\n" + LINE);
        } else {
            ui.println(LINE + "\nInvalid task number.\n" + LINE);
        }
    }

    /**
     * Finds tasks containing a specified keyword in their description.
     * Searches through the tasks list and adds tasks that contain the keyword to a new list.
     * Prints the matching tasks or a message indicating no matches found.
     * @param command String containing the keyword to search for
     */
    public void findTasks(String command) {
        String keyword = command.substring(5).trim().toLowerCase();

        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.println(LINE + "\nNo matching tasks found for keyword: " + keyword + "\n" + LINE);
        } else {
            ui.println(LINE + "\nHere are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.println("   " + (i + 1) + ". " + matchingTasks.get(i).toString());
            }
            ui.println(LINE);
        }
    }
}
