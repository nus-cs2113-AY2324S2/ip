public class TaskManager extends Blue {
    // assume no more than 100 tasks for now
    public static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int numTasks = 0;

    public void listTasks() {
        for (int i = 0; i < numTasks; i += 1) {
            talk(Integer.toString(i + 1) + ". " + tasks[i]);
        }
    }
    public void markTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= numTasks) {
            talk("Task not found.");
            return;
        }
        tasks[taskIndex].setDone();
        talk("Task " + tasks[taskIndex].getDescription() + " marked as done.");
    }
    public void addTask(Task task) {
        tasks[numTasks] = task;
        numTasks += 1;
        talk("added: " + task.getDescription());
    }
    public void addTask(String description) {
        tasks[numTasks] = new Task(description);
        numTasks += 1;
        talk("added: " + description);
    }
    public void addTask(String description, String by) {
        tasks[numTasks] = new Deadline(description, by);
        numTasks += 1;
        talk("added: " + description);
    }
    public void addTask(String description, String from, String to) {
        tasks[numTasks] = new Event(description, from, to);
        numTasks += 1;
        talk("added: " + description);
    }
}