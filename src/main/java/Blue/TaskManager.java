package Blue;

public class TaskManager extends Blue {
    // assume no more than 100 tasks for now
    public static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
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
        case todo:
        case deadline:
        case event:
            addTask(request.getTaskToAdd());
            break;
        default:
        }
    }

    private void listTasks() {
        for (int i = 0; i < numTasks; i += 1) {
            talk((i + 1) + ". " + tasks[i]);
        }
    }

    private void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        tasks[taskIndex].setDone();
        talk("Task " + tasks[taskIndex].getDescription() + " marked as done.");
    }

    private void addTask(Task task) {
        tasks[numTasks] = task;
        numTasks += 1;
        talk("added: " + task.getDescription());
    }
}
