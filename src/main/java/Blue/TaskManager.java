package Blue;

import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;
    private static Ui taskManagerUi;
    private Input request;

    public TaskManager() {
    }

    public TaskManager(Input request) {
        this.request = request;
    }

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

    public void addTask(Task task, boolean isNew) {
        tasks.add(task);
        numTasks += 1;
        if (isNew) {
            taskManagerUi.talk("added: " + task.getDescription());
        }
    }
}
