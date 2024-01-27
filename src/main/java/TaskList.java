import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final String sep = "____________________________________________________________";
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        if (!task.isBlank()) {
            Task newTask = new Task(task);
            tasks.add(newTask);
        }
    }

    public void printTasks() {
        System.out.println("Todo List:");
        for (int i = 0; i < tasks.size(); i++) {
            boolean isMark = tasks.get(i).getMark();
            String isMarkSymbol = isMark ? "[x] " : "[ ] ";
            System.out.println((i + 1) + ". " + isMarkSymbol + tasks.get(i).getTask());
        }
        System.out.println(sep);
    }

    public void markTask(int num)  {
        if (num > 0 && num <= tasks.size()) {
            this.tasks.get(num - 1).setMark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[x] " + tasks.get(num - 1).getTask() + "\n" + sep);
        } else {
            System.out.println("Selected index out of range" + "\n" + sep);
        }
    }

    public void unmarkTask(int num) {
        if (num > 0 && num <= tasks.size()) {
            this.tasks.get(num - 1).setUnmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks.get(num - 1).getTask() + "\n" + sep);
        } else {
            System.out.println("Selected index out of range" + "\n" + sep);
        }
    }
}


