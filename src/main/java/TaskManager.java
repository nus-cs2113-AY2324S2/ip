public class TaskManager {
    public static final int MAX_TASKS = 100;
    private int currIndex;
    private Task[] Tasks;

    public TaskManager() {
        this.currIndex = 0;
        this.Tasks = new Task[MAX_TASKS];
    }

    public void addTask(String nameOfTaskToAdd) {
        Tasks[currIndex] = new Task(nameOfTaskToAdd);
        currIndex++;
        System.out.println("added: " + nameOfTaskToAdd);
    }

    public void markTask(int taskIndex, boolean isDone) {
        Task targetTask = Tasks[taskIndex];
        if (isDone) {
            targetTask.markDone();
            System.out.println("Nice! I've marked this Task as done:");
            System.out.println("[X] " + targetTask.getName());
        } else {
            targetTask.markNotDone();
            System.out.println("Ok, I've marked this Task as not done yet:");
            System.out.println("[ ] " + targetTask.getName());
        }
    }

    public void listTasks() {
        for (int i = 0; i < currIndex; i++) {
            System.out.println((i + 1) + ".[" + Tasks[i].getDoneStatus() + "] " + Tasks[i].getName());
        }
    }
}
