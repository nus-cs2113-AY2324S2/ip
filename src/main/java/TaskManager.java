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
        int currentIndex = nextTaskId - 1;
        Task newTask = new Task(taskName, nextTaskId);
        tasks[currentIndex] = newTask;
        nextTaskId++;
        taskCount++;

        return String.format(" added: %s", taskName);
    }

    String listTasks() {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks[i];
            if (currentTask.isCompleted()) {
                output.append(String.format(" %d.[X] %s\n", currentTask.getTaskId(), currentTask.getTaskName()));
            } else {
                output.append(String.format(" %d.[ ] %s\n", currentTask.getTaskId(), currentTask.getTaskName()));
            }
        }

        return output.toString().stripTrailing();
    }

    String updateTaskProgress(int taskId, String command) {
        String output;
        int currentTaskIndex = taskId - 1; // Index in array is ID - 1
        Task currentTask = tasks[currentTaskIndex];
        String currentTaskName = currentTask.getTaskName();

        if (command.equals("mark")) {
            output = " Nice! I've marked this task as done:\n";
            currentTask = currentTask.completeTask();
            output += String.format("   [X] %s", currentTaskName);
        } else {
            output = " OK, I've marked this task as not done yet:\n";
            currentTask = currentTask.uncompleteTask();
            output += String.format("   [ ] %s", currentTaskName);
        }

        tasks[currentTaskIndex] = currentTask;
        return output;
    }
}
