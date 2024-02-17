package oley.tasks;
import java.util.Scanner;

public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        setTaskName(taskName);
        this.taskName = taskName;
        isDone = false;
    }

    public boolean checkDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String toString() {
        if (isDone) {
            return ("[🆗] " + taskName);
        } else {
            return ("[  ] " + taskName);
        }
    }
}
