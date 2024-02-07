import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();
    public static void addTask(Task userTask) {
        list.add(userTask);
        Davvy.printLine();
        System.out.println(" added: " + userTask.description);
        Davvy.printLine();
    }
    public static Task getTask(int taskNumber) {
        return list.get(taskNumber);
    }
    public static int listLength() {
        return list.size();
    }
    public static void printList() {
        Davvy.printLine();
        System.out.println(" Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(task);
        }
        Davvy.printLine();
    }
}
