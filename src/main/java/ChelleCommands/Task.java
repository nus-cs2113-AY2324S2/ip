package ChelleCommands;

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

    public static void addMessage(Task[] tasks, int taskCount) {
        System.out.println("Chelle: Got it. I've added this task:\n        " +
                tasks[taskCount - 1].toString() +
                "\n        Now you have " + taskCount + " tasks in the list.");
    }

    public static void delMessage(Task[] tasks, int taskCount, int taskIndex) {
        System.out.println("Chelle: Got it. I've deleted this task:\n        " +
                tasks[taskIndex].toString() +
                "\n        Now you have " + (taskCount-1) + " tasks in the list.");
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + taskName;
    }
}
