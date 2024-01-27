public class TaskManager {
    protected Task[] tasks;
    protected int numberOfTasks;

    public TaskManager() {
        tasks = new Task[100];
        numberOfTasks = 0;
    }

    public void addTask(String taskName) {
        if (numberOfTasks >= 100) {
            return;
        }
        Task newTask = new Task(taskName);
        tasks[numberOfTasks] = newTask;
        numberOfTasks++;
    }

    public void listTasks() {
        System.out.print(Joe.H_LINE);
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getTaskName());
        }
        System.out.println(Joe.H_LINE);
    }
}
