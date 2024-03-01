package Blue;

import java.util.ArrayList;

/**
 * A task manager that keeps track of all tasks.
 * Further, it directly acts on this list of tasks as requested by the user.
 */
public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;
    private static Ui taskManagerUi;
    private Input request;

    public TaskManager() {
    }

    /**
     * Instantiates a task manager to perform a request.
     *
     * @param request The request this particular instance of task manager is to perform.
     */
    public TaskManager(Input request) {
        this.request = request;
    }

    /**
     * Performs the request; saving the new task list to disk after doing so.
     */
    public void performRequest() {
        switch (request.getCommand()) {
        case list:
            listTasks();
            return;
        case mark:
            markTask(request.getTaskIndex());
            break;
        case delete:
            deleteTask(request.getTaskIndex());
            break;
        case find:
            listTasks(request.getTaskQuery());
            break;
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd(), true);
            break;
        default:
        }
        boolean isSaved = new StorageHandler().hasSavedTasks(tasks);
        if (!isSaved) {
            taskManagerUi.warn("Failed to save tasks.");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     * @param isNew Indicates whether this task is one newly received from the user, or restored from disk.
     */
    public void addTask(Task task, boolean isNew) {
        tasks.add(task);
        numTasks += 1;
        if (isNew) {
            taskManagerUi.talk("added: " + task.getDescription());
        }
    }

    private void listTasks() {
        int count = 0;
        for (Task task : tasks) {
            taskManagerUi.talk((count + 1) + ". " + task);
            count += 1;
        }
    }

    private void listTasks(String query) {
        int count = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                taskManagerUi.talk((count + 1) + ". " + task);
                count += 1;
            }
        }
        if (count == 0) {
            taskManagerUi.talk("No tasks found.");
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            taskManagerUi.talk("Task not found.");
            return;
        }
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.setDone();
        taskManagerUi.talk("Task " + taskToMark.getDescription() + " marked as done.");
    }

    private void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            taskManagerUi.talk("Task not found.");
            return;
        }
        taskManagerUi.talk("Task " + tasks.get(taskIndex).getDescription() + " deleted.");
        tasks.remove(taskIndex);
        numTasks -= 1;
    }
}
