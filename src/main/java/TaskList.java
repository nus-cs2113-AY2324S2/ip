import java.util.ArrayList;

public class TaskList {
    static final ArrayList<Task> tasks = Storage.load();
    public static void deleteTask(int indexToDelete) {
        tasks.remove(indexToDelete);
        Storage.save(tasks);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void printList() {
        int count = 0;
        for(Task t:tasks) {
            if(t == null) {
                return;
            }
            count++;
            System.out.print(count + ".");
            System.out.println(t);
        }
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

}
