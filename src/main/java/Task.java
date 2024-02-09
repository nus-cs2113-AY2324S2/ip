public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getTaskDescription() {
        return description;
    }

    public String getFullDescription() {
        String output = "[";
        output += (getIsDone() ? "X] " : " ] ");
        output += getTaskDescription();
        return output;
    }
}