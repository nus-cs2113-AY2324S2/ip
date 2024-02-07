import java.lang.String;
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public void setIsNotDone() {

        this.isDone = false;
    }

    public int taskIndex(String description) {
        String indexToMark = description.substring(description.lastIndexOf(" ") + 1);
        return Integer.parseInt(indexToMark) - 1;
    }
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
