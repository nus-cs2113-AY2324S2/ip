import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> tasklist = new ArrayList<Task>();

    public void listTasks() {
        int i = 1;
        for (Task task:tasklist) {
            if (task.completed) {
                System.out.println(" " + i + ".[X] " + task.taskName);
            } else {
                System.out.println(" " + i + ".[ ] " + task.taskName);
            }
            i++;
        }
    }

    public void addTask(String taskName) {
        tasklist.add(new Task(taskName));
        System.out.println(" added: " + taskName);
    }

    public void markDone(int index) {
        tasklist.get(index).completed = true;
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("  [X] " + tasklist.get(index).taskName);
    }

    public void unmarkDone(int index) {
        tasklist.get(index).completed = false;
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + tasklist.get(index).taskName);
    }
}

