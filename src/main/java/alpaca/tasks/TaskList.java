package alpaca.tasks;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();


    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        System.out.println("I've removed this task: ");
        System.out.println(" " + tasks.get(taskIndex));
        this.tasks.remove(taskIndex);
    }

    public String saveTask() {
        String result = "";
        for (Task task : tasks) {
            result += task.save() + "\n";
        }
        return result;
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

    public void markLastTask() {
        tasks.get(tasks.size() - 1).mark();
    }

    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + tasks.get(taskIndex));
    }

    public String listTasks() {
        String taskList = "";
        for (int i = 0; i < tasks.size(); i++) {
            taskList += "     " + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return taskList;
    }
}