package tasks;

import java.nio.file.Path;

public class Task{

    protected String description;
    protected boolean isDone;
    public static final Path FILE_PATH = Path.of("./data/duck.txt");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
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