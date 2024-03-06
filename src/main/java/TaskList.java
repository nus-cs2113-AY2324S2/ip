import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks in the application, providing functionalities to add, delete,
 * display, mark, unmark, and find tasks based on user inputs.
 */
public class TaskList {
    private static ArrayList<Task> tasks; // The list of tasks.

    /**
     * Initializes a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list based on the specified input.
     * The input determines the type of task to be added (ToDo, Deadline, or Event).
     *
     * @param input The user input specifying the task to add.
     * @throws PhoebeException If there is an error parsing the task information.
     */
    public static void addTask(String input) throws PhoebeException {
        input = input.trim();
        Task newTask;
        if (input.toLowerCase().startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                Ui.printTodoNotSpecified();
                return;
            }
            newTask = new ToDo(description);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                Ui.printDeadlineNotSpecified();
            }
            try {
                newTask = new Deadline(parts[0].trim(), parts[1].trim());
            } catch (DateTimeParseException e) {
                Ui.printDeadlineFormat();
                throw new PhoebeException("Invalid date format. Please use yyyy-MM-dd HHmm, e.g., 2019-12-02 1800.");

            }
        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.substring(5).split("/from", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                Ui.printEventNotSpecified();
                return;
            }
            String[] timeParts = parts[1].trim().split("/to", 2);
            if (timeParts.length < 2) {
                Ui.printEventUnclear();
                return;
            }
            newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        } else {
            Ui.printUserIsStupid();
            return;
        }

        tasks.add(newTask);
        Ui.printTaskAdded(newTask, tasks.size());
    }

    /**
     * Deletes a task from the task list based on the specified input index.
     *
     * @param input The user input specifying the index of the task to delete.
     * @throws NumberFormatException If the input index is not a valid integer.
     */
    public static void deleteTask(String input) throws NumberFormatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printDeleteTaskNotFound();
                return;
            }
            Task removedTask = tasks.remove(taskIndex);
            Ui.printTaskDeleted(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            Ui.printDeleteTaskError();
        }
    }

    /**
     * Displays all tasks currently in the task list.
     */
    public static void displayTasks() {
        if (tasks.isEmpty()) {
            Ui.printDisplayEmptyTasks();
        } else {
            Ui.printDisplayTasks();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Adds a task directly to the tasks list. This method can be used for testing or initializing the task list.
     *
     * @param task The task to add to the task list.
     */
    public static void addTasktotasks(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a specified task as done based on the user input index.
     *
     * @param input The user input specifying the index of the task to mark as done.
     * @throws NumberFormatException If the input index is not a valid integer.
     */
    public static void markTask(String input) throws NumberFormatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printMarkUnmarkMissingTask();
                return;
            }
            tasks.get(taskIndex).markAsDone();
            Ui.printMarkDoneTask(tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            Ui.printMarkUnmarkMissingTask();
        }
    }

    /**
     * Marks a specified task as not done (undone) based on the user input index.
     *
     * @param input The user input specifying the index of the task to unmark.
     * @throws NumberFormatException If the input index is not a valid integer.
     */
    public static void unmarkTask(String input) throws NumberFormatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                Ui.printMarkUnmarkMissingTask();
                return;
            }
            tasks.get(taskIndex).markAsUndone();
            Ui.printMarkUndoneTask(tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            Ui.printMarkUnmarkMissingTask();
        }
    }

    /**
     * Finds and displays tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public static void findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            Ui.printNoMatchingTasks();
        } else {
            Ui.printMatchingTasks(matchingTasks);
        }
    }

//    private static boolean isValidIndex(int index) {
//        return index >= 0 && index < tasks.size();
//    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

//    public int getSize() {
//        return tasks.size();
//    }
}
