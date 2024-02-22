import java.util.ArrayList;
public class List {

    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
    }

    public static int getTotal (ArrayList<Task> tasks) {
        return tasks.size();
    }

    public static void removeTask(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        int taskIndex = 1;
        Reply.printReply("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(taskIndex + ". "  + task.toString());
            taskIndex++;
        }
    }
}
