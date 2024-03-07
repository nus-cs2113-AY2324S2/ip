package helpy.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the default status of not done.
     */
    public Task() {
        setDone(false);
    }

    /**
     * Constructs a new Task object with the given task name and the default status of not done.
     *
     * @param taskName The description of the task.
     */
    public Task(String taskName) {
        setTaskName(taskName.trim());
        setDone(false);
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the description of the task.
     *
     * @param taskName The new task description.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Retrieves the status of the task, indicating whether it is done or not.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of the task as done or not done.
     *
     * @param isDone true to mark the task as done, false to mark it as not done.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Retrieves a string representation of the task's status icon.
     *
     * @return "[X]" if the task is done, or "[ ]" if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskName();
    }

    /**
     * Saves the task to the specified file.
     *
     * @param filePath The path of the file to save the task to.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveToFile(String filePath) throws IOException {
        FileWriter helpyWriter = new FileWriter(filePath, true);
        String isDone = isDone() ? "1" : "0";
        helpyWriter.write("T | " + isDone + " | " + this.taskName + "\n");
        helpyWriter.close();
    }
}
