import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArrayList;
    public TaskList(ArrayList<Task> taskList) {
        TaskList.taskArrayList = taskList;
    }

    public static void add(Task task) {
        taskArrayList.add(task);
    }

    public static Task get(int itemIndex) {
        return taskArrayList.get(itemIndex);
    }

    public static void setDone(int itemIndex, boolean isDone) {
        taskArrayList.get(itemIndex).setDone(isDone);
    }

    public static void delete(int itemIndex) {
        taskArrayList.remove(itemIndex);
    }

    public static void clearList() {
        taskArrayList.clear();
    }
}
