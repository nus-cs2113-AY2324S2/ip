public class Task {
    protected static String[] tasks = new String[100];
    protected static boolean[] tasksCompleted = new boolean[100];
    protected static int taskNumber;

    public Task() {
        taskNumber = 0;
    }

    public static void addTask(String userInput) {
        tasks[taskNumber] = userInput;
        tasksCompleted[taskNumber] = false;
        taskNumber++;
    }
    public static void markDone(int taskNumber) {
        tasksCompleted[taskNumber] = true;
    }

    public static void markUndone(int taskNumber) {
        tasksCompleted[taskNumber] = false;
    }
    public static String getStatusIcon(int taskNumber) {
        return (tasksCompleted[taskNumber] ? "X" : " "); // mark done task with X
    }

    public static void printList() {
        for(int i = 0; i < taskNumber; i++) {
            System.out.println(i+1 + ". [" + getStatusIcon(i) + "] " + tasks[i]);
        }
        Duke.printLine();
    }
}
