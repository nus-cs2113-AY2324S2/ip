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

    public static void handleTasks(ArrayList<Task> tasks) {
        if (List.getTotal(tasks) == Constant.ARRAY_START_INDEX) {
            throw new CustomException(Reply.EMPTY_LIST);
        }

        int taskIndex = 1;
        Reply.printReply("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(taskIndex + ". "  + task.toString());
            taskIndex++;
        }
    }

}
