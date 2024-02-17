import java.util.ArrayList;
public class List {
    public static final ArrayList<Task> tasks = new ArrayList<>();
    private static int totalTasks;

    public static void addTask(Task task) {
        tasks.add(task);
        totalTasks++;
    }

    public static int getTotal () {
        return tasks.size();
    }

    public static void removeTask(Task task) {
        int indexOfTaskToRemove = tasks.indexOf(task);

        if (indexOfTaskToRemove != -1) {
            tasks.remove(indexOfTaskToRemove);
            totalTasks--;
        }
    }

    public static void printTasks() {
        int taskIndex = 1;
        Reply.printReply("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(taskIndex + ". "  + task.toString());
            taskIndex++;
        }
    }
}
