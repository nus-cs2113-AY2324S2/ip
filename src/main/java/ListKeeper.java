import java.util.Scanner;

public class ListKeeper {
    private Task[] tasks;
    private int numTaskAdded = 0;
    private static final int MAX_NUM_OF_TASKS = 100;

    public ListKeeper(int numOfTasks) {
        this.tasks = new Task[numOfTasks];
    }
    public ListKeeper() {
        this(MAX_NUM_OF_TASKS);
    }

    public void addToList(Task task) {
        if (this.numTaskAdded == this.tasks.length) {
            System.out.println("No more tasks can be added");
            return;
        }
        this.tasks[this.numTaskAdded] = task;
        this.numTaskAdded++;
        // Provide feedback to user
        System.out.println("I have added this task:");
        System.out.println(task);
    }

    public void printList() {
        for (int i = 0; i < this.numTaskAdded; i++) {
            System.out.println(i + 1 + ". " + this.tasks[i]);
        }
    }

    public boolean isValidTaskIndex(int inputIndex) {
        return inputIndex >= 1 && inputIndex <= this.numTaskAdded;
    }

    public void processMark(int inputIndex, boolean isCompleted) {
        tasks[inputIndex - 1].mark(isCompleted);
    }
}
