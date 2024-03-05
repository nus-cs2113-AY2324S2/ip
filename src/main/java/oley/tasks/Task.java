package oley.tasks;
import java.time.LocalDateTime;

/**
 * Represents a task regardless of the type. A Task object corresponds to a task with its name and state of done
 * being specified.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructor of Task, initialising isDone to false, and setting the taskName.
     *
     * @param taskName The name of the task.
     */
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

    /**
     * @return The year, month, date and time of now.
     */
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

    /**
     * Returns the state and name of task being printed and shown under a specified format to the users.
     * e.g. [#] Sleep
     *
     * @return The done state and name of task under a specified format.
     */
    public String toString() {
        if (isDone) {
            return ("[#] " + taskName);
        } else {
            return ("[ ] " + taskName);
        }
    }

    /**
     * Returns the state and name of task being recorded under a specified format in the data file.
     * e.g. 1task Sleep
     *
     * @return The done state and name of task under a specified format.
     */
    public String format() {
        if (isDone) {
            return "1task " + taskName;
        } else {
            return "0task " + taskName;
        }
    }
}
