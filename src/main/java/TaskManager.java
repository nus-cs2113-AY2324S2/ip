public class TaskManager {
    private final Task[] tasks;
    private int nextTaskId;
    private int taskCount;
    private static final int MAX_TASK_COUNT = 100;

    public TaskManager() {
        this.tasks = new Task[MAX_TASK_COUNT]; // Assumption that there will never be more than 100 tasks
        this.nextTaskId = 1;
        this.taskCount = 0;
    }

    private String updateTaskList(Task newTask, int taskId) {
        String output = " Got it. I've added this task:\n";
        int currentIndex = taskId - 1;

        tasks[currentIndex] = newTask;
        nextTaskId++;
        taskCount++;

        output += String.format("   %s\n", newTask);
        output += String.format(" Now you have %d tasks in the list.", taskCount);

        return output;
    }

    public String addTodo(String taskName) {
        Task newTask = new Todo(taskName, nextTaskId);
        return updateTaskList(newTask, nextTaskId);
    }

    public String addDeadline(String taskName, String endDate) {
        Task newTask = new Deadline(taskName, nextTaskId, endDate);
        return updateTaskList(newTask, nextTaskId);
    }

    public String addEvent(String taskName, String startDate, String endDate) {
        Task newTask = new Event(taskName, nextTaskId, startDate, endDate);
        return updateTaskList(newTask, nextTaskId);
    }

    /**
     * List out tasks (completed/uncompleted) in Task array
     */
    public String listTasks() {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:\n");

        for (int i = 0; i < taskCount; i++) {
            Task currentTask = tasks[i];
            output.append(String.format(" %d.%s\n", currentTask.getTaskId(), currentTask));
        }

        return output.toString().stripTrailing();
    }

    /**
     * Update progress of Task in Task array
     */
    public String updateTaskProgress(int taskId, String command) {
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

        tasks[currentTaskIndex] = currentTask; // Update array

        return output;
    }
}
