package Blue;

public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this("");
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void setDone() {
        this.isDone = true;
    }

    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "T|" + done + "|" + description + System.lineSeparator();
    }

    @Override
    public String toString() {
        return this.getDescription() + " [" + this.getStatusIcon() + "]";
    }
}
