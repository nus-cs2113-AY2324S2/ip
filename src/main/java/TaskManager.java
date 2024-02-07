// TaskManager.java
public class TaskManager {
    private static final String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount++] = task;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task list is full. Cannot add more tasks.");
        }
    }

    public static void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static boolean processCommand(String command) {
        if ("list".equalsIgnoreCase(command)) {
            listTasks();
            return true;
        } else if ("bye".equalsIgnoreCase(command)) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            return false;
        } else {
            addTask(command);
            return true;
        }
    }
}
