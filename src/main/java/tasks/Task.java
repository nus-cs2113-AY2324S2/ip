package tasks;
import java.util.Scanner;
import interactions.Ui;

public class Task {
    protected String taskDescription;
    private String taskType = "U";
    private boolean isMarked;
    public String getTaskDescription() {
        return taskDescription;
    }
    public boolean isMarked() {
        return isMarked;
    }
    public void setMarked(boolean marked) {
        isMarked = marked;
    }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskType() {
        return taskType;
    }
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isMarked = false;
    }

    /** Prints information about type and if it's marked, as indicated by X,
     *  when task is presented by UI.
     */
    public void print() {
        System.out.print('[' + taskType + ']');
        System.out.print(isMarked() ? "[X] " : "[ ] ");
    }

}