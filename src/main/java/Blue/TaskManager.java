package Blue;

import java.util.ArrayList;

/**
 * A task manager that keeps track of all tasks.
 * Further, it directly acts on this list of tasks as requested by the user.
 */
public class TaskManager {
    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found.";
    private static ArrayList<Task> tasks;
    private static Ui taskManagerUi;

    /**
     * Public constructor for task manager.
     *
     * @param taskManagerUi The UI with which the task manager responds to requests.
     */
    public TaskManager(Ui taskManagerUi) {
        tasks = new StorageHandler().restoreTasks();
        this.taskManagerUi = taskManagerUi;
    }

    /**
     * Performs the request; saving the new task list to disk after doing so.
     */
    public void performRequest(Input request) {
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
            return;
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd());
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
    private void addTask(Task task) {
        tasks.add(task);
        taskManagerUi.talk("added: " + task.getDescription());
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
            taskManagerUi.talk(TASK_NOT_FOUND_MESSAGE);
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            taskManagerUi.talk(TASK_NOT_FOUND_MESSAGE);
            return;
        }
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.setDone();
        taskManagerUi.talk("Task " + taskToMark.getDescription() + " marked as done.");
    }

    private void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            taskManagerUi.talk(TASK_NOT_FOUND_MESSAGE);
            return;
        }
        taskManagerUi.talk("Task " + tasks.get(taskIndex).getDescription() + " deleted.");
        tasks.remove(taskIndex);
    }
}
