import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void addTask(Task userTask) {
        list.add(userTask);
    }
    public static Task getTask(int taskNumber) {
        return list.get(taskNumber);
    }
    public static int listLength() {
        return list.size();
    }
}
