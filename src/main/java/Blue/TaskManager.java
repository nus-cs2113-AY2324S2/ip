package Blue;

import java.util.ArrayList;

public class TaskManager extends Blue {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTasks = 0;
    private Input request;

    public TaskManager(Input request) {
        this.request = request;
    }

    //@Override
    public void performRequest() {
        switch (request.getCommand()) {
        case list:
            listTasks();
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
        }
    }

    private void listTasks() {
        int i = 0;
        for (Task task : tasks) {
            talk((i + 1) + ". " + task);
            i += 1;
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.setDone();
        talk("Task " + taskToMark.getDescription() + " marked as done.");
    }

    private void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        talk("Task " + tasks.get(taskIndex).getDescription() + " deleted.");
        tasks.remove(taskIndex);
        numTasks -= 1;
    }

    private void addTask(Task task) {
        tasks.add(task);
        numTasks += 1;
        talk("added: " + task.getDescription());
    }
}
