public class TaskManager {
    private final Task[] tasks;
    private int nextTaskId;
    private int taskCount;

    public TaskManager() {
        this.tasks = new Task[100]; // Assumption that there will never be more than 100 tasks
        this.nextTaskId = 1;
        this.taskCount = 0;
    }

    String addTask(String taskName) {
        int currentIdx = nextTaskId - 1;
        Task newTask = new Task(taskName, nextTaskId);
        tasks[currentIdx] = newTask;
        nextTaskId++;
        taskCount++;

        return String.format("added: %s", taskName);
    }

    String listTasks() {
        String output = "";
        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks[i];
            output += String.format("%d. %s\n", currentTask.getTaskId(), currentTask.getTaskName());
        }

        return output.stripTrailing();
    }
}
