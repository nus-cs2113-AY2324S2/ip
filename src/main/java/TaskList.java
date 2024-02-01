import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasklist = new ArrayList<Task>();

    public void listTasks() {
        int i = 1;
        for (Task task:tasklist) {
            System.out.println(" " + i + ". " + task.taskName);
            i++;
        }
    }

    public void addTask(String taskName) {
        tasklist.add(new Task(taskName));
        System.out.println(" added: " + taskName);
    }
}

