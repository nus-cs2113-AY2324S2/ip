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

    public static void findAndPrint(String keyword) {
        boolean hasMatch = false;
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getContent().contains(keyword)) {
                hasMatch = true;
                tempTaskList.add(task);
            }
        }
        if (hasMatch) {
            String taskPlural = tempTaskList.size() == 1 ? "task" : "tasks";
            System.out.println("Here are the matching " + taskPlural + " in your list:");
            int iterator = 0;
            for (Task task : tempTaskList) {
                iterator += 1;
                System.out.println(iterator + ". " + task);
            }
        }
        else {
            System.out.println("No matches found");
        }
    }
}
