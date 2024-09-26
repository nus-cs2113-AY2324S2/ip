import java.lang.String;
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     * If the task is marked as done, it returns "X"; otherwise, it returns a space.
     *
     * @return A string "X" if the task is done, or a space " " if not.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Marks the task as done by setting the {@code isDone} flag to true.
     */
    public void setIsDone() {

        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the {@code isDone} flag to false.
     */
    public void setIsNotDone() {

        this.isDone = false;
    }

    public String toFileString() {

        return null;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     * The completion status is represented by an "X" for done tasks, and a space for tasks that are not done.
     *
     * @return A string that combines the task's completion status and its description.
     */
    public String toString() {

        return "[" + getStatusIcon() + "]" + description;
    }

}
