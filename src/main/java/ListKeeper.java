import java.util.Scanner;

public class ListKeeper {
    private Task[] tasks;
    private int numTaskAdded = 0;
    private static final int MAX_NUM_OF_TASKS = 100;

    public ListKeeper() {
        this.tasks = new Task[ListKeeper.MAX_NUM_OF_TASKS];
    }

    public void addToList(String taskName) {
        if (this.numTaskAdded == tasks.length) {
            System.out.println("No more tasks can be added");
            return;
        }
        tasks[this.numTaskAdded] = new Task(taskName);
        this.numTaskAdded++;
        // Provide feedback to user
        System.out.println("added: " + taskName);
    }

    public void printList() {
        for (int i = 0; i < this.numTaskAdded; i++) {
            System.out.println(i + 1 + ". " + this.tasks[i]);
        }
    }

    private boolean isOutOfBoundIndex(int inputIndex) {
        return inputIndex <= 0 || inputIndex > this.numTaskAdded;
    }
    private void printInvalidTaskIndex() {
        System.out.println("Task specified does not exist");
    }

    public void processMark(int inputIndex) {
        if (isOutOfBoundIndex(inputIndex)) {
            printInvalidTaskIndex();
            return;
        }
        tasks[inputIndex - 1].mark();
    }

    public void processUnmark(int inputIndex) {
        if (isOutOfBoundIndex(inputIndex)) {
            printInvalidTaskIndex();
            return;
        }
        tasks[inputIndex - 1].unmark();
    }
}
