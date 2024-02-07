public class TaskManager {
    // assume no more than 100 tasks for now
    public static final int MAX_TASKS = 100;
    private Task[] tasks;
    private int numTasks;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        numTasks = 0;
    }

    public void listTasks() {
        for (int i = 0; i < this.numTasks; i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks[i]);
        }
    }
    public void markTask(int taskIndex) {
        tasks[taskIndex].setDone();
        System.out.println("Task " + tasks[taskIndex].getDescription() + " marked as done.");
    }
    public void addTask(String description) {
        tasks[numTasks] = new Task(description);
        numTasks += 1;
        System.out.println("added: " + description);
    }
    public void addTask(String description, String by) {
        tasks[numTasks] = new Deadline(description, by);
        numTasks += 1;
        System.out.println("added: " + description);
    }
    public void addTask(String description, String from, String to) {
        tasks[numTasks] = new Event(description, from, to);
        numTasks += 1;
        System.out.println("added: " + description);
    }
}