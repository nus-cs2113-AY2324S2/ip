import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "]" + this.description);
    }

    public String getDescription() {
        return description;
    }

    public String getTaskTypeIcon() { //default is a todo task
        return "T";
    }

}