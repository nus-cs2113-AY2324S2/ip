package bobby;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description,Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getBy() {
        return "";
    }

    public String getFrom() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void printDescription() {
        System.out.println(description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
