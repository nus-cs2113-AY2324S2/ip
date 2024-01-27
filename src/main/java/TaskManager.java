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
            System.out.println((i + 1) + "." + tasks[i].getTaskStatus());
        }
        System.out.println(Joe.H_LINE);
    }

    public void toggleTaskMarkedStatus(int taskNumber, String marker) {
        if (taskNumber > numberOfTasks || taskNumber <= 0) {
            System.out.println(Joe.H_LINE + "PLEASE ENTER A VALID TASK NUMBER :(\n" + Joe.H_LINE);
            return;
        }
        boolean isDone = marker.equals("mark");
        tasks[taskNumber - 1].setDone(isDone);
        if (isDone) {
            System.out.println(Joe.H_LINE + "GOOD JOB BRO. I'VE MARKED IT AS DONE:");
        } else {
            System.out.println(Joe.H_LINE + "OKAY I WILL MARK IT UNDONE:");
        }
        System.out.println("  " + tasks[taskNumber - 1].getTaskStatus() + "\n" + Joe.H_LINE);
    }
}
