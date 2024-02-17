package alpaca.tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();


    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    public boolean isCountValid(int taskIndex) {
        if (taskIndex <= getTotalTaskNumber() - 1) {
            return true;
        }
        return false;
    }

    public void markTask(int taskIndex) {
        tasks.get(taskIndex).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    public void listTasks() {
        int taskIndex = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            taskIndex++;
            System.out.println(taskIndex + "." + task);
        }
    }
}