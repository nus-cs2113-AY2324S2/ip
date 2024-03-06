package Tasking;

import java.io.IOException;

import static Storage.Storage.rewriteFile;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark tasks in the ArrayList as done
     * @param isPrint Passes in a boolean which enables the printing of message onto console
     * @throws IOException if there was an issue writing data into the file
     */
    public void markDone(boolean isPrint) throws IOException {
        isDone = true;
        if (isPrint) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + this);
            rewriteFile();
        }
    }

    /**
     * Unmark tasks in the ArrayList
     * @param isPrint Passes in a boolean which enables the printing of message onto console
     * @throws IOException if there was an issue writing data into the file
     */
    public void markNotDone(boolean isPrint) throws IOException {
        isDone = false;
        if (isPrint) {
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println(" " + this);
            rewriteFile();
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
