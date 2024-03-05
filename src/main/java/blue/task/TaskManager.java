package blue.task;

import blue.command.Input;
import blue.task.Task;
import blue.ui.Ui;

import java.util.ArrayList;

/**
 * A task manager that keeps track of all tasks, modifying this list as directed by the user.
 */
public class TaskManager {
    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found.";
    private static ArrayList<Task> tasks;
    private static Ui taskManagerUi;

    /**
     * Public constructor for task manager.
     * Note that it need only be called once as it suffices to have one task manager per program.
     *
     * @param taskManagerUi The UI with which the task manager responds to requests.
     */
    public TaskManager(Ui taskManagerUi) {
        tasks = new ArrayList<>();
        this.taskManagerUi = taskManagerUi;
    }

    /**
     * Getter for tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Performs the request contained in the Ui.
     */
    public void performRequest() {
        Input request = taskManagerUi.getRequest();
        switch (request.getCommand()) {
        case list:
        case find:
            listTasks(request.getTaskQuery());
            break;
        case mark:
            markTask(request.getTaskIndex());
            break;
        case delete:
            deleteTask(request.getTaskIndex());
            break;
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd());
            break;
        default:
            return;
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    private void addTask(Task task) {
        tasks.add(task);
        taskManagerUi.talk("added: " + task.getDescription());
    }

    /**
     * Graphically lists all tasks that match a given query.
     *
     * @param query The string query with which to match tasks. If empty string "", simply list all tasks.
     */
    private void listTasks(String query) {
        String[] matchingTasks = tasks.stream()
            .map(Object::toString)
            .filter((t) -> t.contains(query))
            .toArray(String[]::new);
        if (matchingTasks.length == 0) {
            taskManagerUi.talk(TASK_NOT_FOUND_MESSAGE);
            return;
        }
        taskManagerUi.talk(matchingTasks, true);
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
