package util;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask (Task task) {
        System.out.println("A task I see. I have added it.");
        System.out.println("  " + task);
        tasks.add(task);
    }

    public void deleteTask(int index) {
        try {
            System.out.println("Should all acquaintance be forgot..." );
            System.out.println("  " + tasks.get(index - 1));
            tasks.remove(index - 1);
            System.out.println(tasks.size() + " task(s) remain.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You are trying to access forbidden territory. Tread carefully.");
        }

    }

    public void listTasks () {
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty. Try again, when you have become more productive.");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.get(i));
        }
    }

    public void markTask (int index) {
        try {
            tasks.get(index - 1).markAsDone();
            System.out.println("Congratulations. I have marked this task as finished:");
            System.out.println("  " + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You are trying to access forbidden territory. Tread carefully.");
        }

    }

    public void unmarkTask (int index) {
        try {
            tasks.get(index - 1).markAsUndone();
            System.out.println("Do not neglect your duties. I have marked this task as unfinished:");
            System.out.println("  " + tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You are trying to access forbidden territory. Tread carefully.");

        }
    }
}