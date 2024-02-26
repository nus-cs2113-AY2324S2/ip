package brad.tasks;

public class Tasks {
    private String description;
    private boolean isDone;

    protected Tasks(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    protected void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected boolean getIsDone() {
        return isDone;
    }

    protected String getTaskDescription() {
        return description;
    }

    protected String getFullDescription() {
        String output = "[";
        output += (getIsDone() ? "X] " : " ] ");
        output += getTaskDescription();
        return output;
    }

    protected String getTime() {
        return "";
    }
}