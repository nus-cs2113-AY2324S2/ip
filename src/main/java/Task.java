public class Task {
    private static int taskNumber;
    private static final String[] tasks = new String[100];
    private String taskName;

    public Task(String taskName) {
        setTaskName(taskName);
        tasks[taskNumber] = this.taskName;
        taskNumber++;
    }

    public static int getTaskNumber() {
        return taskNumber;
    }

    public static String[] getTasks() {
        return tasks;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public static void printTask(){
        int i = 0;
        while (i < taskNumber) {
            System.out.println((i + 1) + ". " + tasks[i]);
            i++;
        }
    }
}
