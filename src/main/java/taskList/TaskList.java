package taskList;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
    }

    public void markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTasks(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.showTask((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public String getFormattedTasks() {
        if(tasks.isEmpty()) {
            return "OPPS! Your task list is empty :<";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return sb.toString().trim();
        }
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task: tasks) {
            if(task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
