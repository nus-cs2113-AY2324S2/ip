package ChelleCommands;

import java.util.ArrayList;

public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public static void addMessage(ArrayList<Task> tasks) {
        System.out.println("Chelle: Got it. I've added this task:\n        " +
                tasks.get(tasks.size() - 1).toString() +
                "\n        Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void delMessage(ArrayList<Task> tasks, int taskIndex) {
        System.out.println("Chelle: Got it. I've deleted this task:\n        " +
                tasks.get(taskIndex).toString() +
                "\n        Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + taskName;
    }
}
