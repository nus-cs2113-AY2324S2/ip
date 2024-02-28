package tasks;

import java.nio.file.Path;

public class Task{

    protected static String description;
    protected boolean isDone;
    public static final Path FILE_PATH = Path.of("./data/duck.txt");

    public Task(String description) {
        Task.description = description;
        this.isDone = false;
    }

    public static String getDescription() {
        return description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String toString() {
        String done = "[ ] ";
        if (isDone) {
            done = "[X] ";
        }
        return done + description;
    }

}