package huan.main;

import huan.task.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    public static List<Task> tasks = new ArrayList<>();

    public static Boolean isIndexValid(int index) {
        return index >= 1 && index <= tasks.size();
    }
    public static void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public static void clearTasks() {
        tasks.clear();
    }

}
