package task;

import ui.Ui;

import java.util.List;
import java.util.ArrayList;

public class TaskList{

    private final List<Task> tasks;
    private final Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
        ui = new Ui("aoliba");
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        ui.showSize(tasks.size());
    }

    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        System.out.println("Noted. I've removed this task: " + tasks.get(taskNumber-1).getDescription());
        tasks.remove(taskNumber - 1);
    }

    public void listTasks() {
        ui.showLine();
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        ui.showLine();
    }

    public void markTaskAsDone(int taskNumber, boolean markDone) {
        // boolean found = false;
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.invalidIndex();
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        // task is UNmarked, and we want to mark it
        if (!task.isDone() && markDone) {
            task.markAsDone();
        } else if (task.isDone() && !markDone) { // task is marked, and we want to UNmark it
            task.markAsNotDone();
        }
        else {
            System.out.println("Task already marked as " + (markDone ? "done": "not done yet"));
            return;
        }
        System.out.println("Nice! I've marked this task as " + (markDone ? "done": "not done yet"));

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void findTasks(String keywords) {
        // TaskList results = new TaskList();
        int matches = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keywords)) {
                // results.addTask(tasks.get(i));
                if (matches == 0) {
                    ui.showLine();
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println((matches + 1) + "." + tasks.get(i));
                matches++;
            }
        }
        if (matches == 0) {
            System.out.println("No matching tasks found.");
        }
        ui.showLine();
        // if (results.tasks.isEmpty()) {
        //     System.out.println("No matching tasks found.");
            // return;
        // }
        // results.listTasks();
    }

}
